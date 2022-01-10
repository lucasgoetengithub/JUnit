package br.ce.lgoeten.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.lgoeten.entidades.Filme;
import br.ce.lgoeten.entidades.Locacao;
import br.ce.lgoeten.entidades.Usuario;
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

	@Test(expected = Exception.class)
	public void testLocacalTestandoFilmeSemEstoque() throws Exception {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// Acao
		locacaoService.alugarFilme(usuario, filme);
	}

	@Test
	public void testLocacalTestandoFilmeSemEstoque2() {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// Acao
		try {
			locacaoService.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado alguma excessão");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}

	@Test
	public void testLocacalTestandoFilmeSemEstoque3() throws Exception {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");

		// Acao
		locacaoService.alugarFilme(usuario, filme);
	}
}
