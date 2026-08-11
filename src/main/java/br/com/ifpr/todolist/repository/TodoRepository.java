package br.com.ifpr.todolist.repository;

import br.com.ifpr.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

  
    List<Todo> findByConcluidaOrderByDataCriacaoDesc(boolean concluida);

    List<Todo> findAllByOrderByDataCriacaoDesc();
}
