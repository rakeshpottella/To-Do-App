package com.example.ToDoApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDoApp.Entity.ToDo;
import com.example.ToDoApp.Service.ToDoServiceImpl;

@Controller

public class ToDoController {
	
	
	private ToDoServiceImpl todoserviceimpl;
	 @Autowired
	    public ToDoController(ToDoServiceImpl todoserviceimpl) {
	        this.todoserviceimpl = todoserviceimpl;
	    }
		
			
			@GetMapping({"/", "ViewToDoList"})
			public String viewAllToDoItem(Model model , @ModelAttribute("message") String message) {
				model.addAttribute("list", todoserviceimpl.getAllToDoItems());
				model.addAttribute("message", message);
				return "ViewToDoList";
			}
			@GetMapping("/updateToDoStatus/{id}")
			public String updateToDoStatus(@PathVariable Long id,RedirectAttributes redirectAttributes) {
				if (todoserviceimpl.updateStatus(id)){
					redirectAttributes.addFlashAttribute("message", "Update Success");
					return "redirect:/ViewToDoList";
					
				}
				redirectAttributes.addFlashAttribute("message", "Update Failure");
				return "redirect:/ViewToDoList";
			}
			@GetMapping("/addToDoItem")
			public String addToDoItem(Model model) {
				model.addAttribute("todo", new ToDo());
				return "AddToDoItem";		
			}
			@PostMapping("/saveToDoItems")
			public String saveToDoItem(ToDo todo,RedirectAttributes redirectAttributes) {
				if (todoserviceimpl.saveOrUpdateToDoItem(todo)) {
					redirectAttributes.addFlashAttribute("message", "Save Success");
					return "redirect:/ViewToDoList";
				}
				redirectAttributes.addFlashAttribute("message", "Save Failure");
				return "redirect:/addToDoItem";
				
			}
			@GetMapping("/editToDoItem/{id}")
			public String editToDoItem(@PathVariable Long id, Model model) {
				model.addAttribute("todo", todoserviceimpl.getToDoItemById(id));
				return "EditToDoItem";
			}
			@PostMapping("/editsaveToDoItem")
			public String editsaveToDOItem(ToDo todo,RedirectAttributes redirectAttributes) {
				if (todoserviceimpl.saveOrUpdateToDoItem(todo)) {
					redirectAttributes.addFlashAttribute("message", "Edit Success");
					return "redirect:/ViewToDoList";
				}
				redirectAttributes.addFlashAttribute("message", "Edit Failure");
				return "redirect:/editToDoItem"+ todo.getId();
				
			}
			@GetMapping("/deleteToDoItem/{id}")
			public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
					if(todoserviceimpl.deleteToDoItem(id))	{
						redirectAttributes.addFlashAttribute("message", "Delete Success");
						return "redirect:/ViewToDoList";
					}
					redirectAttributes.addFlashAttribute("message", "Delete Failure");
					return "redirect:/ViewToDoList";
			}

}
