package com.ferran.todo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferran.todo.entity.ToDo;
import com.ferran.todo.service.ToDoService;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin("*")
public class ToDoController {
	
	private final ToDoService toDoService;
	
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}
	
	@GetMapping("/")
	public List<ToDo> getToDos(){
		return toDoService.getToDos();
	}
	

	@PostMapping("/create")
	public String createTodo(@RequestBody ToDo toDo) {
		toDoService.createToDo(toDo);
		return "saved";
	}
	
	@PatchMapping("/isfinished/{id}")
	public String updateIsFinished(@PathVariable Long id) {
		toDoService.updateIsFinished(id);
		return "Todo with id " + id + " has been updated";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteToDo(@PathVariable Long id) {
		toDoService.deleteToDo(id);
		return "Todo with id " + id + " has beem removed";
	}
}
