package observer;

import modelo.Livro;

public interface IObservador {

    void atualizar(Livro livro, String estadoAnterior);
}
