package com.springboot.firstwebapplication.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.firstwebapplication.model.Todo;
import com.springboot.firstwebapplication.service.TodoService;


@Controller
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	//To show todos in todo page
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model){
		String name = getLoggedUserName(model);
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}

	private String getLoggedUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	//To show added todos in todo page to GET
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String ShowAddTodos(ModelMap model){
		model.addAttribute("todo",new Todo(0,getLoggedUserName(model),"",new Date(),false));
		return "todo";
	}
	
	//To add todos in todo page to POST
		@RequestMapping(value="/add-todo", method = RequestMethod.POST)
		public String addTodo(ModelMap model,@Valid Todo todo,BindingResult result){
			if(result.hasErrors()) {
				return "todo";
			}
			service.addTodo(getLoggedUserName(model), todo.getDesc(),todo.getTargetDate(), false);
			return "redirect:/list-todos";
		}
	
	//To delete todos in todo page
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String DeleteTodo(@RequestParam int id){
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}

	
	//To Update todos in todo page to GET
		@RequestMapping(value="/update-todo", method = RequestMethod.GET)
		public String ShowUpdateTodo(@RequestParam int id,ModelMap model){
			Todo todo=service.retrieveTodo(id);
			model.put("todo", todo);
			return "todo";
		}
		
		//To Update todos in todo page to POST
		@RequestMapping(value="/update-todo", method = RequestMethod.POST)
		public String UpdateTodo(ModelMap model,@Valid Todo todo,BindingResult result){
			if(result.hasErrors()) {
				return "todo";
			}
			todo.setUser(getLoggedUserName(model));
			service.UpdateTodo(todo);
			return "redirect:/list-todos";
		}
}
