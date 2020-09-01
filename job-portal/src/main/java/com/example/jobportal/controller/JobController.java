package com.example.jobportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.dto.JobDto;
import com.example.jobportal.service.JobService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/job")
public class JobController {
	private final JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobDto>> getAllJobs() {
		return new ResponseEntity<List<JobDto>>(jobService.getAllJobs(), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JobDto> addJob(@RequestBody @Validated JobDto dto) {
		return new ResponseEntity<JobDto>(jobService.saveJob(dto), HttpStatus.CREATED);
	}

}
