package observer;
import modelo.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Aluno implements IObservador {

    private final String nome;
    private final List<String> notificacoes = new ArrayList<>();

    public Aluno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getNotificacoes() {
        return Collections.unmodifiableList(notificacoes);
    }

    @Override
    public void atualizar(Livro livro, String estadoAnterior) {
        String estadoAtual = livro.getEstado().getNome();
        if (estadoAtual.equals("Disponível")) {
            notificacoes.add("[Aluno " + nome + "] O livro \"" + livro.getTitulo() + "\" que você está esperando já está disponível!");
        }
    }}
