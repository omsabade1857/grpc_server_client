package com.client.dtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
	private int id;

	@NotBlank
	@NotEmpty(message = "Name cannot be empty")
	@Pattern(regexp = "^[A-Za-z\\s-_]+$", message = "Enter correct format Name")
	private String name;

	@NotBlank
	@NotEmpty(message = "Designation cannot be empty")
	@Pattern(regexp = "^[A-Za-z\\s-_]+$", message = "Enter correct String value")
	private String designation;

	@NotNull(message = "Date of birth cannot be empty")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Mobile number cannot be empty")
	@Pattern(regexp = "^\\d{10}$", message = "Enter 10 digit mobile number")
	private String mobileNumber;

	@Valid   //IMPORTANT step to validate Student Field
	private List<AddressDto> address;
	
	public StudentDto(int id,String name, String designation, LocalDate dateOfBirth, String mobileNumber, List<AddressDto> address) {
		this.id=id;
		this.name = name;
        this.designation = designation;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", designation=" + designation + ", dateOfBirth="
				+ dateOfBirth + ", mobileNumber=" + mobileNumber + "]";
	}
}