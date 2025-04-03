package com.example.GetItDone.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GetItDone.domain.Task;
import com.example.GetItDone.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> findTaskByUserId(Long userId){
		return taskRepository.findByUserId(userId);
	}
	
	public List<Task> findTaskByCompleted(boolean complete){
		return taskRepository.findByCompleted(complete);
	}
	
	public List<Task> findTaskByDueDate(LocalDate dueDate){
		return taskRepository.findByDueDate(dueDate);
	}
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
    public void deleteTask(Long taskId) {
	        taskRepository.deleteById(taskId);
	    }
    
 // 할 일 수정 (기존 Task 업데이트)
    public Optional<Task> updateTask(Long taskId, Task updatedTask) {
        return taskRepository.findById(taskId).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setComplete(updatedTask.isComplete());
            task.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(task);
        });
    }
}
