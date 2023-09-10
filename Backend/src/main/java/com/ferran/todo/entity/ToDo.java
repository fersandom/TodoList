package com.ferran.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long todoId;
	
	private String todoItem;
	
	private Boolean isComplete = false;
	
}
