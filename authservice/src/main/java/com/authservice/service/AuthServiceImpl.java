package com.authservice.service;

import com.authservice.dto.LoginDto;
import com.authservice.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservice.dto.APIResponse;
import com.authservice.dto.RegistrationRequestDto;
import com.authservice.dto.RegistrationResponseDto;
import com.authservice.entity.Role;
import com.authservice.entity.User;
import com.authservice.repository.RoleRepository;
import com.authservice.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

	@Override
    public APIResponse<?> register(RegistrationRequestDto dto) {

        // 1. Confirm password check
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return new APIResponse<>(400, "Registration Failed", "Passwords do not match");
        }

        // 2. Uniqueness checks
        if (userRepository.existsByUsername(dto.getUsername())) {
            return new APIResponse<>(400, "Registration Failed",
                    "Patient already exists with this username. Please enter a different username");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            return new APIResponse<>(400, "Registration Failed",
                    "Patient already exists with this email. Please enter a different email");
        }
        if (userRepository.existsByContact(dto.getContact())) {
            return new APIResponse<>(400, "Registration Failed",
                    "Patient already exists with this contact. Please enter a different contact");
        }
        
        

        // Assign Role
        Optional<Role> existingRole=roleRepository.findByName("ROLE_" + dto.getRole().getName());
        Role role;
        if (existingRole.isPresent()) {
            role = existingRole.get(); // reuse existing role
        } else {
            role = new Role();
            role.setName("ROLE_" + dto.getRole().getName());
            role = roleRepository.save(role); // save new role
        }





        // 3. Save user
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        // 4. Prepare response DTO
        RegistrationResponseDto responseDto = new RegistrationResponseDto();
        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setContact(user.getContact());

        return new APIResponse<>(200, "Registration Successful", responseDto);
    }



	@Override
	public APIResponse<String> login(LoginDto dto ) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        try{
            Authentication authentication=authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if(authentication.isAuthenticated()){
                String token=jwtService.generateToken(usernamePasswordAuthenticationToken.getName(), authentication.getAuthorities().iterator().next().getAuthority());
                return new APIResponse<>(200, "Login Successful", token);
            }

        }catch(Exception e ){
            e.printStackTrace();
        }


        return new APIResponse<>(404, "Login Unsuccessful", "Wrong User Credentials");

    }

}
