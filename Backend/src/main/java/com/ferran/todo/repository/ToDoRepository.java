package com.ferran.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferran.todo.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}
