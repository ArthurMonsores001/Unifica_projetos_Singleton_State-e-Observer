package estado;

import modelo.Livro;

public class Emprestado implements EstadoLivro {

    @Override
    public String getNome() {
        return "Emprestado";
    }

    @Override
    public void emprestar(Livro livro) {
        // transicao invalida - ignora
    }

    @Override
    public void devolver(Livro livro) {
        livro.mudarEstado(new Disponivel());
    }

    @Override
    public void reservar(Livro livro) {
        // transicao invalida - ignora
    }

}