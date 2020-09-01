import { Component, OnInit, Input } from '@angular/core';
import { Job } from '../model/job';

@Component({
  selector: 'app-job-card',
  templateUrl: './job-card.component.html',
  styleUrls: ['./job-card.component.css']
})
export class JobCardComponent implements OnInit {

  constructor() { }

  @Input() job: Job;

  ngOnInit() {
  }

}
