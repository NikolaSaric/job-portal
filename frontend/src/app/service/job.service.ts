import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from '../model/job';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(private http: HttpClient) { }

  jobPath = 'http://localhost:8080/api/job';

  public getAllJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.jobPath);
  }

  public addJob(job: Job): Observable<Job> {
    return this.http.post<Job>(this.jobPath, job);
  }
}
