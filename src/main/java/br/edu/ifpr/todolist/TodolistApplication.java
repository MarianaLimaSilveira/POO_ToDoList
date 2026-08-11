package br.edu.ifpr.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpr.todolist.repository.TodoRepository;

@SpringBootApplication
public class TodolistApplication {

	private final TodoRepository todoRepository;

	TodolistApplication(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
