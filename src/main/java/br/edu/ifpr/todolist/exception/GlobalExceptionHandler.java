package br.edu.ifpr.todolist.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Trata, de forma centralizada, as exceções lançadas pelos controllers
 * e devolve a mesma página de erro personalizada (templates/error.html)
 * usada pelo Spring Boot para os erros automáticos (ex.: 404 de rota
 * inexistente), em vez da Whitelabel Error Page.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ModelAndView tratarTarefaNaoEncontrada(TarefaNaoEncontradaException ex, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("status", 404);
        mv.addObject("error", "Tarefa não encontrada");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    // Rede de segurança: qualquer outra exceção não tratada também deve
    // cair na página personalizada, e não na Whitelabel Error Page.
    @ExceptionHandler(Exception.class)
    public ModelAndView tratarErroInesperado(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("status", 500);
        mv.addObject("error", "Erro interno");
        mv.addObject("message", "Ocorreu um erro inesperado ao processar a sua solicitação.");
        return mv;
    }

}
