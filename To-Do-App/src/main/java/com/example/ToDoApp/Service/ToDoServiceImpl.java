package com.example.ToDoApp.Service;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.Entity.ToDo;
import com.example.ToDoApp.Repository.ToDoRepository;


@Service
public class ToDoServiceImpl implements ToDoService  {
	
    @Autowired
	  private ToDoRepository todoRepository;
    @Override

    public ToDo createToDo(ToDo todo) {

    return todoRepository.save(todo);

    }
	  
    public List<ToDo> getAllToDoItems() {
		ArrayList<ToDo> todoList = new ArrayList<>();
		todoRepository.findAll().forEach(todo -> todoList.add(todo));
		
		return todoList;
	}
	
	public ToDo getToDoItemById(Long id) {
		return todoRepository.findById(id).get();
	}
	
	public boolean updateStatus(Long id) {
		ToDo todo = getToDoItemById(id);
		todo.setStatus("Completed");
		
		return saveOrUpdateToDoItem(todo);
	}
	
	public boolean saveOrUpdateToDoItem(ToDo todo) {
		ToDo updatedObj = todoRepository.save(todo);
		
		if (getToDoItemById(updatedObj.getId()) != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean deleteToDoItem(Long id) {
		todoRepository.deleteById(id);
		
		if (todoRepository.findById(id).isEmpty()) {
			return true;
		}
		
		return false;
	}



}