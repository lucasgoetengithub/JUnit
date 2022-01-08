package br.ce.lgoeten.servicos;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.lgoeten.entidades.Filme;
import br.ce.lgoeten.entidades.Locacao;
import br.ce.lgoeten.entidades.Usuario;
import br.ce.lgoeten.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void teste() {
		// cenario
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		// Acao
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);

		// verificacoes
		Assert.assertTrue(locacao.getValor() == 5.0);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}
