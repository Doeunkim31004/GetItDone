package com.example.GetItDone.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.GetItDone.Service.TaskService;
import com.example.GetItDone.domain.Task;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	 @GetMapping
	    public String showTasks(Model model) {
	        model.addAttribute("tasks", taskService.findAllTasks());  // 전체 할 일 목록 전달
	        return "task"; // templates/tasks.html 반환
	    }
	
	//특정 사용자의 투두리스트 조회
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>>getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.findTaskByUserId(userId);
        return ResponseEntity.ok(tasks);
    }
	
	//완료 한 것 여부로 할 일 조회
	@GetMapping("/completed/{status}")
	public ResponseEntity<List<Task>>getTaskByCompleted(@PathVariable boolean status){
		List<Task> tasks = taskService.findTaskByCompleted(status);
		return ResponseEntity.ok(tasks);
	}
	
	//특정날짜 조회로 할일 조회
	@GetMapping("/due/{dueDate}")
	public ResponseEntity<List<Task>>getTaskByDueDate(@PathVariable String dueDate){
		LocalDate date = LocalDate.parse(dueDate);
        List<Task> tasks = taskService.findTaskByDueDate(date);
        return ResponseEntity.ok(tasks);
	}
	
	//새로운 투두리스트 생성
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task){
	    Task newTask = taskService.createTask(task);
	    return ResponseEntity.ok(newTask);
	}

	
	 // 특정 ID의 할 일 삭제
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    // 특정 ID의 할 일 수정
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> task = taskService.updateTask(taskId, updatedTask);
        return task.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
