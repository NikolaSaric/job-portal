package com.example.jobportal.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jobportal.dto.JobDto;
import com.example.jobportal.model.Job;

@ExtendWith(MockitoExtension.class)
public class JobDtoConverterUnitTest {
	private JobDtoConverter jobDtoConverter;
	
	Job job;
	JobDto dto;
	
	@BeforeEach
	public void setUp() {
		jobDtoConverter = new JobDtoConverter();
		
		job = Job.builder()
				.id(1L)
				.title("Test")
				.description("Test Description")
				.salary(100)
				.contact("+381 111 111 11")
				.build();
		
		dto = JobDto.builder()
				.id(1L)
				.title("Test")
				.description("Test Description")
				.salary(100)
				.contact("+381 111 111 11")
				.build();
	}
	
	@Test
	public void toEntity_returnJob() {
		Job jobEntity = jobDtoConverter.toEntity(dto);
		
		assertEquals(jobEntity.getId(), dto.getId(), "Assert job id: ");
		assertEquals(jobEntity.getTitle(), dto.getTitle(), "Assert job title: ");
		assertEquals(jobEntity.getDescription(), dto.getDescription(), "Assert job description: ");
		assertEquals(jobEntity.getSalary(), dto.getSalary(), "Assert job salary: ");
		assertEquals(jobEntity.getContact(), dto.getContact(), "Assert job contact: ");
	}
	
	@Test
	public void toDto_returnJobDto() {
		JobDto jobDto = jobDtoConverter.toDto(job);
		
		assertEquals(jobDto.getId(), job.getId(), "Assert dto id: ");
		assertEquals(jobDto.getTitle(), job.getTitle(), "Assert dto title: ");
		assertEquals(jobDto.getDescription(), job.getDescription(), "Assert dto description: ");
		assertEquals(jobDto.getSalary(), job.getSalary(), "Assert dto salary: ");
		assertEquals(jobDto.getContact(), job.getContact(), "Assert dto contact: ");
	}
}
