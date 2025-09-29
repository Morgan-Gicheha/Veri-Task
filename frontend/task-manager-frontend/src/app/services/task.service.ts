import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Task, TaskRequest } from '../models/task.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private readonly API_URL = `${environment.apiUrl}/api/tasks`;
  
  private tasksSubject = new BehaviorSubject<Task[]>([]);
  public tasks$ = this.tasksSubject.asObservable();

  constructor(private http: HttpClient) { }

  /**
   * Get all tasks for the current user
   */
  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.API_URL)
      .pipe(
        tap(tasks => this.tasksSubject.next(tasks))
      );
  }

  /**
   * Get a specific task by ID
   */
  getTask(id: string): Observable<Task> {
    return this.http.get<Task>(`${this.API_URL}/${id}`);
  }

  /**
   * Create a new task
   */
  createTask(request: TaskRequest): Observable<Task> {
    return this.http.post<Task>(this.API_URL, request)
      .pipe(
        tap(() => this.refreshTasks())
      );
  }

  /**
   * Update an existing task
   */
  updateTask(id: string, request: TaskRequest): Observable<Task> {
    return this.http.put<Task>(`${this.API_URL}/${id}`, request)
      .pipe(
        tap(() => this.refreshTasks())
      );
  }

  /**
   * Delete a task
   */
  deleteTask(id: string): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`)
      .pipe(
        tap(() => this.refreshTasks())
      );
  }

  /**
   * Refresh the tasks list
   */
  private refreshTasks(): void {
    this.getTasks().subscribe();
  }

  /**
   * Clear tasks (on logout)
   */
  clearTasks(): void {
    this.tasksSubject.next([]);
  }
}
