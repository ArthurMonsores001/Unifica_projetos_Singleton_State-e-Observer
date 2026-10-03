package modelo;

import estado.Disponivel;
import estado.EstadoLivro;
import observer.IObservador;
import observer.ISujeito;

import java.util.ArrayList;
import java.util.List;

public class Livro implements ISujeito {

    private final String titulo;
    private EstadoLivro estado;
    private final List<IObservador> observadores = new ArrayList<>();

    public Livro(String titulo) {
        this.titulo = titulo;
        this.estado = new Disponivel();
    }

    public String getTitulo() {
        return titulo;
    }

    public EstadoLivro getEstado() {
        return estado;
    }

    public void emprestar() {
        estado.emprestar(this);
    }

    public void devolver() {
        estado.devolver(this);
    }

    public void reservar() {
        estado.reservar(this);
    }

    public void mudarEstado(EstadoLivro novoEstado) {
        String estadoAnterior = estado.getNome();
        this.estado = novoEstado;
        notificarObservadores(estadoAnterior);
    }

    @Override
    public void adicionarObservador(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String estadoAnterior) {
        for (IObservador observador : observadores) {
            observador.atualizar(this, estadoAnterior);
        }
    }
}