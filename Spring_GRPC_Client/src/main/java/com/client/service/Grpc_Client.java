package com.client.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.AllStudentResponse;
import com.client.StudentResponse;
import com.client.StudentServiceGrpc;
import com.client.StudentServiceGrpc.StudentServiceBlockingStub;
import com.client.dtos.StudentDto;
import com.client.mapper.Mapper;

import io.grpc.ManagedChannel;

import java.util.List;

import javax.annotation.PreDestroy;

@Service
public class Grpc_Client {

    @Autowired
    private ModelMapper modelMapper;

    private final ManagedChannel channel;
    private final StudentServiceBlockingStub stub;

    public Grpc_Client(ManagedChannel channel) {
        this.channel = channel;
        this.stub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public StudentDto addStudent(StudentDto studentDto) {
        StudentResponse student = stub.createStudent(Mapper.DtoToRequest(studentDto));
        return Mapper.responseToDto(student);
    }
    
    public StudentDto getStudentById(int id) {
    	StudentResponse student = stub.getStudentById(Mapper.DtoToRequest(id));
    	return Mapper.responseToDto(student);
    }
    
    public List<StudentDto> getAllStudents() {
        AllStudentResponse allStudents = stub.getAllStudents(null);
        return Mapper.responseToDto(allStudents);
    }
    
    

    @PreDestroy
    public void shutdown() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
            try {
                if (!channel.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
                    channel.shutdownNow();
                }
            } catch (InterruptedException e) {
                channel.shutdownNow();
            }
        }
    }
}
