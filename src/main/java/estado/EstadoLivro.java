package estado;


import modelo.Livro;

public interface EstadoLivro {

    String getNome();

    void emprestar(Livro livro);

    void devolver(Livro livro);

    void reservar(Livro livro);

}
