package estado;

import modelo.Livro;

public class Disponivel implements EstadoLivro {

    @Override
    public String getNome() {
        return "Disponível";
    }

    @Override
    public void emprestar(Livro livro) {
        livro.mudarEstado(new Emprestado());
    }

    @Override
    public void devolver(Livro livro) {
        // transicao invalida - ignora
    }

    @Override
    public void reservar(Livro livro) {
        livro.mudarEstado(new Reservado());
    }

}