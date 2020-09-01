package com.example.jobportal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class JobDto implements AbstractDto {
	private Long id;

	@NotBlank(message = "Title must not be blank.")
	@Size(min = 3, message = "Title must be equal or greater than 3.")
	private String title;

	@NotBlank(message = "Description must not be blank.")
	@Size(min = 3, message = "Description must be equal or greater than 3.")
	private String description;

	@PositiveOrZero(message = "Salary must be positive number.")
	private Integer salary;

	@NotBlank(message = "Contact must not be blank.")
	@Size(min = 3, message = "Contact must be equal or greater than 3.")
	private String contact;

	public JobDto() {
	}

	public JobDto(JobDtoBuilder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.description = builder.description;
		this.salary = builder.salary;
		this.contact = builder.contact;
	}

	public static JobDtoBuilder builder() {
		return new JobDtoBuilder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public static class JobDtoBuilder {
		private Long id;
		private String title;
		private String description;
		private Integer salary;
		private String contact;

		public JobDtoBuilder() {
		}

		public JobDto build() {
			return new JobDto(this);
		}

		public JobDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public JobDtoBuilder title(String title) {
			this.title = title;
			return this;
		}

		public JobDtoBuilder description(String description) {
			this.description = description;
			return this;
		}

		public JobDtoBuilder salary(Integer salary) {
			this.salary = salary;
			return this;
		}

		public JobDtoBuilder contact(String contact) {
			this.contact = contact;
			return this;
		}

	}

}
