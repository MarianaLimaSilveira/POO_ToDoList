package br.com.ifpr.todolist.service;

import br.com.ifpr.todolist.model.Todo;
import br.com.ifpr.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

  
    public Todo salvar(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> listarTodas() {
        return todoRepository.findAllByOrderByDataCriacaoDesc();
    }

    public Todo buscarPorId(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada: id " + id));
    }

    public void excluir(Long id) {
        todoRepository.deleteById(id);
    }

    public void alternarConclusao(Long id) {
        Todo todo = buscarPorId(id);
        todo.setConcluida(!todo.isConcluida());
        todoRepository.save(todo);
    }
}
