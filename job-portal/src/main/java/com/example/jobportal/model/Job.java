package com.example.jobportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Job extends AbstractEntity {
	@NotBlank(message = "Title must not be blank.")
	@Column(nullable = false)
	private String title;

	@NotBlank(message = "Description must not be blank.")
	@Column(nullable = false)
	private String description;

	@NotNull(message = "Salary must not be null.")
	@PositiveOrZero(message = "Salary must be positive number.")
	@Column(nullable = false)
	private Integer salary;

	@NotBlank(message = "Contact must not be blank.")
	@Column(nullable = false)
	private String contact;

	public static JobBuilder builder() {
		return new JobBuilder();
	}

	public Job() {
	}

	public Job(JobBuilder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.description = builder.description;
		this.salary = builder.salary;
		this.contact = builder.contact;
	}

	@Override
	public String toString() {
		return "Job [title=" + title + ", description=" + description + ", salary=" + salary + ", contact=" + contact
				+ "]";
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

	public static class JobBuilder {
		private Long id;
		private String title;
		private String description;
		private Integer salary;
		private String contact;

		public JobBuilder() {
		}

		public Job build() {
			return new Job(this);
		}

		public JobBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public JobBuilder title(String title) {
			this.title = title;
			return this;
		}

		public JobBuilder description(String description) {
			this.description = description;
			return this;
		}

		public JobBuilder salary(Integer salary) {
			this.salary = salary;
			return this;
		}

		public JobBuilder contact(String contact) {
			this.contact = contact;
			return this;
		}

	}

}
