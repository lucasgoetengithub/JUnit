package br.ce.lgoeten.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.lgoeten.entidades.Filme;
import br.ce.lgoeten.entidades.Locacao;
import br.ce.lgoeten.entidades.Usuario;
import br.ce.lgoeten.exception.FilmeSemEstoqueException;
import br.ce.lgoeten.exception.LocadoraException;
import br.ce.lgoeten.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testeLocacao() throws Exception {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		// Acao
		Locacao locacao;

		locacao = locacaoService.alugarFilme(usuario, filme);

		// verificacoes
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(new Date(), locacao.getDataLocacao()), is(true));
		error.checkThat(DataUtils.isMesmaData(DataUtils.obterDataComDiferencaDias(1), locacao.getDataRetorno()),
				is(true));
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacalTestandoFilmeSemEstoque() throws Exception {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// Acao
		locacaoService.alugarFilme(usuario, filme);
	}

	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Filme filme = new Filme("Filme 1", 10, 5.0);

		// Acao
		try {
			locacaoService.alugarFilme(null, filme);
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuário vazio"));
		}
	}

	@Test
	public void testLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");

		// Acao,
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		locacaoService.alugarFilme(usuario, null);
	}
}
