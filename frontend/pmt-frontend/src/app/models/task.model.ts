export enum TaskStatus {
  TODO = 'TODO',
  IN_PROGRESS = 'IN_PROGRESS',
  DONE = 'DONE'
}

export interface Task {
  id: number;
  title: string;
  description: string;
  status: TaskStatus;
  priority: string;
  dueDate: string;
  createdAt: string;
  completedAt?: string;

  assignedTo?: {
    id: number;
    username: string;
  };
}
