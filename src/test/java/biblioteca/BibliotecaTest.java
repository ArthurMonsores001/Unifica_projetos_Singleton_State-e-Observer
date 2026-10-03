package biblioteca;

import estado.*;
import modelo.Livro;
import observer.*;
import org.junit.jupiter.api.*;
import singleton.Biblioteca;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @Test
    void singletonDeveRetornarSempreAMesmaInstancia() {
        assertSame(Biblioteca.getInstance(), Biblioteca.getInstance());
    }

    @Test
    void singletonDeveCompartilharOAcervo() {
        Livro cadastrado = Biblioteca.getInstance().cadastrarLivro("Refactoring");

        assertEquals(Disponivel.class, cadastrado.getEstado().getClass());
    }

    @Test
    void estadoDeveSeguirOFluxoValido() {
        Livro livro = new Livro("Clean Code");

        livro.emprestar();
        assertEquals(Emprestado.class, livro.getEstado().getClass());

        livro.devolver();
        assertEquals(Disponivel.class, livro.getEstado().getClass());

        livro.reservar();
        assertEquals(Reservado.class, livro.getEstado().getClass());

        livro.emprestar();
        assertEquals(Emprestado.class, livro.getEstado().getClass());
    }

    @Test
    void estadoDeveIgnorarAcoesInvalidas() {
        Livro livro = new Livro("Clean Code");

        livro.devolver();
        assertEquals(Disponivel.class, livro.getEstado().getClass());

        livro.emprestar();
        livro.emprestar();
        livro.reservar();
        assertEquals(Emprestado.class, livro.getEstado().getClass());
    }

    @Test
    void historicoDeveRegistrarApenasTransicoesValidas() {
        Livro livro = new Livro("Clean Code");
        HistoricoLivro historico = new HistoricoLivro();
        livro.adicionarObservador(historico);

        livro.devolver();
        livro.emprestar();
        livro.devolver();

        assertEquals(List.of(
                "Clean Code: Disponível -> Emprestado",
                "Clean Code: Emprestado -> Disponível"), historico.getRegistros());
    }

    @Test
    void observadorRemovidoNaoDeveReceberNotificacao() {
        Livro livro = new Livro("Clean Code");
        HistoricoLivro historico = new HistoricoLivro();
        livro.adicionarObservador(historico);
        livro.emprestar();

        livro.removerObservador(historico);
        livro.devolver();

        assertEquals(1, historico.getRegistros().size());
    }

    @Test
    void alunoDeveSerAvisadoApenasQuandoLivroFicarDisponivel() {
        Livro livro = new Livro("Clean Code");
        Aluno maria = new Aluno("Maria");
        livro.adicionarObservador(maria);

        livro.emprestar();
        assertTrue(maria.getNotificacoes().isEmpty());

        livro.devolver();
        assertEquals(1, maria.getNotificacoes().size());
    }

    @Test
    void painelDeveRegistrarUltimaTransicao() {
        Livro livro = new Livro("Clean Code");
        PainelBibliotecario painel = new PainelBibliotecario();
        livro.adicionarObservador(painel);

        livro.emprestar();

        assertEquals("[Painel] \"Clean Code\": Disponível -> Emprestado", painel.getUltimaAtualizacao());
    }
}
