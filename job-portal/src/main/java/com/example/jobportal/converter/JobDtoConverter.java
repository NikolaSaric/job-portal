package com.example.jobportal.converter;

import org.springframework.stereotype.Component;

import com.example.jobportal.dto.JobDto;
import com.example.jobportal.model.Job;

@Component
public class JobDtoConverter implements EntityToDtoConverter<Job, JobDto>{

	@Override
	public Job toEntity(JobDto dto) {
		Job job = Job.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.salary(dto.getSalary())
				.contact(dto.getContact())
				.build();
		
		return job;
	}

	@Override
	public JobDto toDto(Job entity) {
		JobDto jobDto = JobDto.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.description(entity.getDescription())
				.salary(entity.getSalary())
				.contact(entity.getContact())
				.build();
		
		return jobDto;
	}

}
