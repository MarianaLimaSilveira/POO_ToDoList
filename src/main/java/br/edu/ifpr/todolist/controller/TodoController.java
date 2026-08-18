package br.edu.ifpr.todolist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.todolist.exception.TarefaNaoEncontradaException;
import br.edu.ifpr.todolist.model.Todo;
import br.edu.ifpr.todolist.repository.TodoRepository;

@Controller
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // cria uma visao do modelo, como quero mostrar a saída
    @GetMapping("/")
    public ModelAndView list() {
        return new ModelAndView(
                "index", Map.of("todos", todoRepository.findAll()));
    }

    // exibe a tela principal com o formulário já preenchido para edição
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(
                        "Não foi possível editar: a tarefa de id " + id + " não existe."));

        return new ModelAndView("index", Map.of(
                "todos", todoRepository.findAll(),
                "editando", todo));
    }

    // cria uma nova tarefa OU atualiza uma existente
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }



    @PostMapping("/{id}/concluir")
    public String concluir(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(
                        "Não foi possível concluir: a tarefa de id " + id + " não existe."));

        if (todo.isFinished()) {
            todo.markAsUnfinished();
        } else {
            todo.markAsFinished();
        }
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(
                        "Não foi possível excluir: a tarefa de id " + id + " não existe."));

        todoRepository.delete(todo);
        return "redirect:/";
    }

    
    @GetMapping("/teste/todos")
    @ResponseBody
    public List<Todo> listJson() {
        return todoRepository.findAll();
    }

}
