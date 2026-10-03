package singleton;

import modelo.Livro;

import java.util.*;

public class Biblioteca {

    private static Biblioteca instancia;

    private final List<Livro> acervo = new ArrayList<>();

    private Biblioteca() {
    }

    public static synchronized Biblioteca getInstance() {
        if (instancia == null) {
            instancia = new Biblioteca();
        }
        return instancia;
    }

    public Livro cadastrarLivro(String titulo) {
        Livro livro = new Livro(titulo);
        acervo.add(livro);
        return livro;
    }

    public List<Livro> listarAcervo() {
        return Collections.unmodifiableList(acervo);
    }
}