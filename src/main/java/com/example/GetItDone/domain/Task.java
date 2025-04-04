package com.example.GetItDone.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "Text")
	private String description;
	
	private LocalDate dueDate;
	
	@Column(nullable = false)
	private boolean completed = false;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}


