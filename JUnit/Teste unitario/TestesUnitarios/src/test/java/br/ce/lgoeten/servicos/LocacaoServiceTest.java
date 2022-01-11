package br.ce.lgoeten.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import br.ce.lgoeten.entidades.Filme;
import br.ce.lgoeten.entidades.Locacao;
import br.ce.lgoeten.entidades.Usuario;
import br.ce.lgoeten.exception.FilmeSemEstoqueException;
import br.ce.lgoeten.exception.LocadoraException;
import br.ce.lgoeten.utils.DataUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocacaoServiceTest {

	private LocacaoService locacaoService = new LocacaoService();
	
	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void before() {
		locacaoService = new LocacaoService();
	}
	
	@After
	public void after() {
		
	}
	
	@BeforeClass
	public static void beforeClass() {
		
	}
	
	@AfterClass
	public static void afterClass() {
		
	}

	@Test
	public void t1_testeLocacao() throws Exception {
		// cenario
		
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
	public void t2_testLocacalTestandoFilmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// Acao
		locacaoService.alugarFilme(usuario, filme);
	}

	@Test
	public void t3_testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		// cenario
		Filme filme = new Filme("Filme 1", 10, 5.0);

		// Acao
		try {
			locacaoService.alugarFilme(null, filme);
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuário vazio"));
		}
	}

	@Test
	public void t4_testLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");

		// Acao,
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		locacaoService.alugarFilme(usuario, null);
	}
}
