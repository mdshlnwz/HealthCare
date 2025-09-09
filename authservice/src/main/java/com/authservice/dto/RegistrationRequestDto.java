package com.authservice.dto;

import com.authservice.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationRequestDto {

    @NotNull(message="Patient must have a username")
    private String username;

    @NotNull(message="Patient must have a first name")
    private String firstName;

    @NotNull(message="Patient must have a first name")
    private String lastName;

    @Email(message="Kindly enter a valid email to get latest updates regarding your tests")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String contact;

    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = """
                    At least 8 digits
                    
                    At least one uppercase letter
                    
                    At least one lowercase letter
                    
                    At least one digit
                    
                    At least one special character (like @#$%!^&*""")
    private String password;

    @NotNull(message="Must be same as password")
    private String confirmPassword;

    private Role role;





}

