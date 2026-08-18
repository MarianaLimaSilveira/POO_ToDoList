package br.edu.ifpr.todolist.exception;

/**
 * Lançada quando o usuário tenta editar, concluir ou excluir uma
 * tarefa cujo id não existe no banco de dados.
 */
public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(String message) {
        super(message);
    }

}
