package com.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
    
    



}

