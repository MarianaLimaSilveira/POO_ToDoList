package br.com.ifpr.todolist.controller;

import br.com.ifpr.todolist.model.Todo;
import br.com.ifpr.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tarefas")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tarefas", todoService.listarTodas());
        return "list"; 
    }

  
    @GetMapping("/nova")
    public String novaTarefaForm(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("modoEdicao", false);
        return "form"; 
    }

  
    @PostMapping
    public String salvar(@Valid @ModelAttribute("todo") Todo todo,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            
            model.addAttribute("modoEdicao", todo.getId() != null);
            return "form";
        }
        todoService.salvar(todo);
        return "redirect:/tarefas";
    }

  
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.buscarPorId(id));
        model.addAttribute("modoEdicao", true);
        return "form";
    }

    @PostMapping("/{id}/concluir")
    public String alternarConclusao(@PathVariable Long id) {
        todoService.alternarConclusao(id);
        return "redirect:/tarefas";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        todoService.excluir(id);
        return "redirect:/tarefas";
    }
}
