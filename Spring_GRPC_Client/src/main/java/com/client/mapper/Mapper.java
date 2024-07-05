package com.client.mapper;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.client.AddressRequest;
import com.client.AddressResponse;
import com.client.AllStudentResponse;
import com.client.StudentId;
import com.client.StudentRequest;
import com.client.StudentResponse;
import com.client.dtos.AddressDto;
import com.client.dtos.StudentDto;

public class Mapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static StudentDto requestToDto(StudentRequest request) {
        List<AddressDto> addresses = request.getAddressList().stream()
                .map(Mapper::addressRequestToDto)
                .collect(Collectors.toList());

        LocalDate dateOfBirth = LocalDate.parse(request.getDateOfBirth(), DATE_FORMATTER);

        return new StudentDto(
        		request.getId(),
                request.getName(),
                request.getDesignation(),
                dateOfBirth,
                request.getMobileNumber(),
                addresses
        );
    }

    private static AddressDto addressRequestToDto(AddressRequest request) {
        return new AddressDto(
        		request.getId(),
        		request.getState(),
                request.getStreet(),
                request.getPinCode(),
                request.getStudentId()
        );
    }

    public static StudentResponse entityToResponse(StudentDto student) {
        List<AddressResponse> addressResponses = student.getAddress().stream()
                .map(addr -> AddressResponse.newBuilder()
                		.setId(addr.getId())
                        .setState(addr.getState())
                        .setStreet(addr.getStreet())
                        .setPinCode(addr.getPinCode())
                        .setStudentId(addr.getStudentId())
                        .build())
                .collect(Collectors.toList());
        LocalDate dateOfBirth = student.getDateOfBirth();

        return StudentResponse.newBuilder()
        		.setId(student.getId())
                .setName(student.getName())
                .setDesignation(student.getDesignation())
                .setMobileNumber(student.getMobileNumber())
                .setDateOfBirth(dateOfBirth.format(DATE_FORMATTER))
                .addAllAddress(addressResponses)
                .build();
    }


	public static int requestToDto(StudentId request) {
		return request.getId();
	}

	public static AllStudentResponse entityToResponse(List<StudentDto> allStudents) {
	    List<StudentResponse> studentResponses = allStudents.stream()
	            .map(Mapper::entityToResponse) 
	            .collect(Collectors.toList());

	    return AllStudentResponse.newBuilder()
	            .addAllStudents(studentResponses)
	            .build();
	}

	public static StudentRequest DtoToRequest(StudentDto studentDto) {
		return StudentRequest.newBuilder()
				.setId(studentDto.getId())
				.setName(studentDto.getName())
				.setMobileNumber(studentDto.getMobileNumber())
				.setDesignation(studentDto.getDesignation())
				.setDateOfBirth(String.valueOf(studentDto.getDateOfBirth()))
				.addAllAddress(studentDto.getAddress().stream()
                        .map(addr -> AddressRequest.newBuilder()
                        		.setId(addr.getId())
                                .setState(addr.getState())
                                .setStreet(addr.getStreet())
                                .setPinCode(addr.getPinCode())
                                .setStudentId(addr.getStudentId())
                                .build())
                        .collect(Collectors.toList()))
                .build();
	}

	public static StudentDto responseToDto(StudentResponse student) {
	    List<AddressDto> addresses = student.getAddressList().stream()
	            .map(addr -> new AddressDto(addr.getId(),addr.getState(), addr.getStreet(), addr.getPinCode(),addr.getStudentId()))
	            .collect(Collectors.toList());

	    LocalDate dateOfBirth = LocalDate.parse(student.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	    return new StudentDto(
	    		student.getId(),
	            student.getName(),
	            student.getMobileNumber(),
	            dateOfBirth,
	            student.getDesignation(),
	            addresses
	    );
	
	}

	public static StudentId DtoToRequest(int id) {
		return StudentId.newBuilder()
				.setId(id).build();
	}

	 public static List<StudentDto> responseToDto(AllStudentResponse allStudents) {
	        return allStudents.getStudentsList().stream()
	                .map(Mapper::responseToDto)
	                .collect(Collectors.toList());
	    }
	

}
