package com.example.GetItDone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GetItDone.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findByUserId(Long userId);  // 특정 사용자의 할 일 목록 조회
    List<Task> findByCompleted(boolean completed);  // 완료 여부 기준 조회
    List<Task> findByDueDate(LocalDate dueDate);  // 특정 날짜의 할 일 조회
}
