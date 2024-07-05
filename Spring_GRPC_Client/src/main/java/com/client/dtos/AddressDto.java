package com.client.dtos;

import java.util.List;

import jakarta.validation.Valid;
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
public class AddressDto {
	private int id;

	@NotEmpty(message = "State cannot be empty")
	@Pattern(regexp = "^[A-Za-z\\s-_]+$", message = "Enter only alphabets, spaces, '-'and '_' are allowed")
	private String state;

	@NotEmpty(message = "Street cannot be empty")
	@Pattern(regexp = "^[A-Za-z\\s-_]+$", message = "Enter only alphabets, spaces, '-'and '_' are allowed")
	private String street;

	@NotNull(message = "Pincode cannot be empty")
	// @Pattern(regexp = "^[0-9]+$", message = "Enter only numbers")//....not work
	// @Numeric(message = "Enter only numbers")
	private long pinCode;

	private int studentId;

	public AddressDto(int id,String state, String street, long pinCode, int studentId) {
		this.id=id;
		this.state = state;
		this.street = street;
		this.pinCode = pinCode;
		this.studentId=studentId;
	}

	@Override
	public String toString() {
		return "AddressDto [id=" + id + ", state=" + state + ", street=" + street + ", pinCode=" + pinCode
				+ ", studentId=" + studentId + "]";
	}

}
