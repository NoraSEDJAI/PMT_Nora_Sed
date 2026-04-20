import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DragDropModule, CdkDragDrop, transferArrayItem } from '@angular/cdk/drag-drop';
import { TaskService } from '../../services/task';
import { Task, TaskStatus } from '../../models/task.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, DragDropModule],
  templateUrl: './dashboard.html',
})
export class DashboardComponent implements OnInit {

  projectId = 1;
  actorId = 1; // user connecté (temporaire)

  todo: Task[] = [];
  inProgress: Task[] = [];
  done: Task[] = [];

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadDashboard();
  }

  loadDashboard() {
    this.taskService.getDashboard(this.projectId).subscribe(data => {
      this.todo = data.todo;
      this.inProgress = data.inProgress;
      this.done = data.done;
    });
  }

  drop(event: CdkDragDrop<Task[]>, newStatus: TaskStatus) {

    if (event.previousContainer === event.container) {
      return;
    }

    const task = event.previousContainer.data[event.previousIndex];

    transferArrayItem(
      event.previousContainer.data,
      event.container.data,
      event.previousIndex,
      event.currentIndex
    );

    this.taskService.updateTaskStatus(task.id, newStatus, this.actorId)
      .subscribe({
        next: () => console.log('Status mis à jour'),
        error: err => console.error('Erreur update', err)
      });
  }

  status = TaskStatus;
}
