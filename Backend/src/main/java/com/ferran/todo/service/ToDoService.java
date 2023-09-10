package com.ferran.todo.service;

import java.util.List;

import com.ferran.todo.entity.ToDo;

public interface ToDoService {

	void createToDo(ToDo todo);

	List<ToDo> getToDos();

	void updateIsFinished(Long id);

	void deleteToDo(Long id);
	
}
