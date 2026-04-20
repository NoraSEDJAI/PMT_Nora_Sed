export interface TaskHistory {
  id: number;
  fieldName: string;
  oldValue: string;
  newValue: string;
  createdAt: string;

  performedBy: {
    id: number;
    username: string;
    email: string;
  };
}
