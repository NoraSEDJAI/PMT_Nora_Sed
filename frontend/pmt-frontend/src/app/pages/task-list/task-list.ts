import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskService } from '../../services/task';
import { Task } from '../../models/task.model';
import { TaskHistoryComponent } from '../task-history/task-history';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, TaskHistoryComponent],
  template: `
    <h2>Tasks du projet</h2>

    <ul>
      <li *ngFor="let task of tasks">
        <strong>{{ task.title }}</strong>
        — {{ task.status }}
        — {{ task.priority }}

        <button (click)="selectedTaskId = task.id">
          Voir historique
        </button>

        <app-task-history
          *ngIf="selectedTaskId === task.id"
          [taskId]="task.id">
        </app-task-history>
      </li>
    </ul>
  `
})
export class TaskListComponent implements OnInit {

  tasks: Task[] = [];
  selectedTaskId?: number;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.taskService.getTasksByProject(1).subscribe({
      next: data => {
        this.tasks = data;
        console.log('Tasks chargées', data);
      },
      error: err => console.error(err)
    });
  }

  loadTasks() {
    this.taskService.getTasksByProject(1).subscribe(tasks => {
      this.tasks = tasks;
    });
  }

  changeStatus(task: any, newStatus: string) {
    const actorId = 1;

    this.taskService.updateTaskStatus(task.id, newStatus, actorId)
      .subscribe({
        next: () => {
          this.loadTasks();
        },
        error: err => console.error(err)
      });
  }
}
