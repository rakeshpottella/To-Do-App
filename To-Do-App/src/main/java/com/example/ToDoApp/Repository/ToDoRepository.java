package com.example.ToDoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDoApp.Entity.ToDo;
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
