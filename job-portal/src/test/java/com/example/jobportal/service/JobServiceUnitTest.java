package com.example.jobportal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jobportal.converter.JobDtoConverter;
import com.example.jobportal.dto.JobDto;
import com.example.jobportal.model.Job;
import com.example.jobportal.repository.JobRepository;

@ExtendWith(MockitoExtension.class)
public class JobServiceUnitTest {
	private JobService jobService;
	
	@Mock
	private JobRepository jobRepository;
	
	@Mock
	private JobDtoConverter jobDtoConverter;
	
	List<Job> jobs;
	Job job1;
	Job job2;
	JobDto dto1;
	JobDto dto2;
	
	@BeforeEach
	public void setUp() {

		jobService = new JobService(jobRepository, jobDtoConverter);
		
		job1 = Job.builder()
				.id(1L)
				.title("Test 1")
				.description("Test Description 1")
				.salary(100)
				.contact("+381 111 111 11")
				.build();
		
		job2 = Job.builder()
				.id(2L)
				.title("Test 2")
				.description("Test Description 2")
				.salary(100)
				.contact("+381 222 222 22")
				.build();
		
		dto1 = JobDto.builder()
				.id(1L)
				.title("Test 1")
				.description("Test Description 1")
				.salary(100)
				.contact("+381 111 111 11")
				.build();
		
		dto2 = JobDto.builder()
				.id(2L)
				.title("Test 2")
				.description("Test Description 2")
				.salary(100)
				.contact("+381 222 222 22")
				.build();
		
		jobs = new ArrayList<Job>();
		jobs.add(job1);
		jobs.add(job2);
	}
	
	@Test
	public void get_returnAllJobsAsDtos() {
		when(jobRepository.findAll()).thenReturn(jobs);
		when(jobDtoConverter.toDto(job1)).thenReturn(dto1);
		when(jobDtoConverter.toDto(job2)).thenReturn(dto2);
		
		List<JobDto> dtos = jobService.getAllJobs();
		
		assertEquals(dtos.size(), 2, "Assert number of jobs: ");
	}
	
	@Test
	public void post_returnNewJobDto() {
		when(jobDtoConverter.toEntity(dto1)).thenReturn(job1);
		when(jobRepository.save(job1)).thenReturn(job1);
		when(jobDtoConverter.toDto(job1)).thenReturn(dto1);
		
		JobDto newJob = jobService.saveJob(dto1);
		
		assertEquals(newJob.getId(), dto1.getId(), "Assert job id: ");
		assertEquals(newJob.getTitle(), dto1.getTitle(), "Assert job title: ");
		assertEquals(newJob.getDescription(), dto1.getDescription(), "Assert job description: ");
		assertEquals(newJob.getSalary(), dto1.getSalary(), "Assert job salary: ");
		assertEquals(newJob.getContact(), dto1.getContact(), "Assert job contact: ");
	}
}
