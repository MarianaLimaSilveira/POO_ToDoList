package br.edu.ifpr.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.todolist.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
