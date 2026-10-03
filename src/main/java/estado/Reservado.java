package estado;

import modelo.Livro;

public class Reservado implements EstadoLivro {

    @Override
    public String getNome() {
        return "Reservado";
    }

    @Override
    public void emprestar(Livro livro) {
        livro.mudarEstado(new Emprestado());
    }

    @Override
    public void devolver(Livro livro) {
    }

    @Override
    public void reservar(Livro livro) {
    }

}
