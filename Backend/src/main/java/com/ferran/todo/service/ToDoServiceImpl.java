package com.ferran.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ferran.todo.entity.ToDo;
import com.ferran.todo.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService{

	private final ToDoRepository toDoRepository;
	
	public ToDoServiceImpl(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}
	
	@Override
	public List<ToDo> getToDos() {
		return toDoRepository.findAll();
	}
	
	@Override
	public void createToDo(ToDo todo) {
		toDoRepository.save(todo);
	}
	
	@Override
	public void updateIsFinished(Long id) {
		Optional<ToDo> todo = toDoRepository.findById(id);		
		todo.get().setIsComplete(!todo.get().getIsComplete());
		toDoRepository.save(todo.get());
		
	}
	
	@Override
	public void deleteToDo(Long id) {
		toDoRepository.deleteById(id);
	}
}
