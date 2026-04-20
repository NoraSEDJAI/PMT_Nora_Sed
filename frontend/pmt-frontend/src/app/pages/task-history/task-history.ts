import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskService } from '../../services/task';
import { TaskHistory } from '../../models/task-history.model';

@Component({
  selector: 'app-task-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-history.html'
})
export class TaskHistoryComponent implements OnInit {

  @Input() taskId!: number;
  history: TaskHistory[] = [];

  constructor(private taskService: TaskService) {}

  ngOnInit() {
    if (this.taskId) {
      this.taskService.getTaskHistory(this.taskId).subscribe(data => {
        this.history = data;
      });
    }
  }
}
