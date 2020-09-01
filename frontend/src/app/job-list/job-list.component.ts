import { Component, OnInit } from '@angular/core';
import { Job } from '../model/job';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JobService } from '../service/job.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private jobService: JobService, private snackBar: MatSnackBar) { }

  jobs = [];
  showJobForm = false;
  addJobForm: FormGroup;

  ngOnInit() {
    this.createForm();
    this.getJobs();
  }

  createForm() {
    this.addJobForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required, Validators.maxLength(250)]],
      salary: [0, [Validators.required, Validators.min(0)]],
      contact: ['', [Validators.required]]
    });
  }

  get title() { return this.addJobForm.controls.title.value as string; }
  get description() { return this.addJobForm.controls.description.value as string; }
  get salary() { return this.addJobForm.controls.salary.value as number; }
  get contact() { return this.addJobForm.controls.contact.value as string; }


  showJobFormBtnClick() {
    this.showJobForm = true;
  }

  hideJobFormBtnClick() {
    this.showJobForm = false;
  }

  onAddJobSubmit() {
    const newJob = new Job(null, this.title, this.description, this.salary, this.contact);

    this.jobService.addJob(newJob).subscribe(
      (response => {
        this.jobs.push(response);
        this.snackBar.open('Job successfully added');
        this.showJobForm = false;
        this.createForm();
      })
    );
  }

  getJobs() {
    this.jobService.getAllJobs().subscribe(
      (response => {
        if (response === null || response.length === 0) {
          return;
        }

        this.jobs = response;
      })
    );
  }

  avgSalary() {
    if (this.jobs.length === 0) {
      return 0;
    } else if (this.jobs.length === 1) {
      return this.jobs[0].salary;
    } else {
      return Math.round(this.jobs.reduce((sum, current) => sum + current.salary, 0) / this.jobs.length);
    }
  }

}
