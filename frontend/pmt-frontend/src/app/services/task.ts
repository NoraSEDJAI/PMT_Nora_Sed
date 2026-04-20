import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task, TaskStatus } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl = 'http://localhost:8080/api/tasks';

  constructor(private http: HttpClient) {}

  // Liste des tâches
  getTasksByProject(projectId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}/project/${projectId}`);
  }

  // Dashboard (kanban)
  getDashboard(projectId: number): Observable<{
    todo: Task[];
    inProgress: Task[];
    done: Task[];
  }> {
    return this.http.get<any>(
      `${this.apiUrl}/dashboard?projectId=${projectId}`
    );
  }

  // Mise à jour du status (drag & drop)
  updateTaskStatus(taskId: number, status: TaskStatus, actorId: number) {
    return this.http.put(
      `${this.apiUrl}/${taskId}`,
      {
        status,
        actorId
      }
    );
  }

  getTaskHistory(taskId: number) {
    return this.http.get<any[]>(
      `${this.apiUrl}/${taskId}/history`
    );
  }

}
