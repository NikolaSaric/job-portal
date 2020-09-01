package com.example.jobportal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.jobportal.converter.JobDtoConverter;
import com.example.jobportal.dto.JobDto;
import com.example.jobportal.repository.JobRepository;

@Service
public class JobService {
	private final JobRepository jobRepository;
	private final JobDtoConverter jobConverter;

	public JobService(JobRepository jobRepository, JobDtoConverter jobConverter) {
		this.jobRepository = jobRepository;
		this.jobConverter = jobConverter;
	}

	public List<JobDto> getAllJobs() {
		return jobRepository.findAll().stream().map(job -> jobConverter.toDto(job)).collect(Collectors.toList());
	}

	public JobDto saveJob(JobDto dto) {
		return jobConverter.toDto(jobRepository.save(jobConverter.toEntity(dto)));
	}

}
