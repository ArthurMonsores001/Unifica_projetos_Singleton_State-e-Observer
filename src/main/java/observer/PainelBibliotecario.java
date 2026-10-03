package observer;

import modelo.Livro;

public class PainelBibliotecario implements IObservador {

    private String ultimaAtualizacao;

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    @Override
    public void atualizar(Livro livro, String estadoAnterior) {
        ultimaAtualizacao = "[Painel] \"" + livro.getTitulo() + "\": " + estadoAnterior + " -> " + livro.getEstado().getNome();
    }
}