package observer;

import modelo.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoLivro implements IObservador {

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Livro livro, String estadoAnterior) {
        registros.add(livro.getTitulo() + ": " + estadoAnterior + " -> " + livro.getEstado().getNome());
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}