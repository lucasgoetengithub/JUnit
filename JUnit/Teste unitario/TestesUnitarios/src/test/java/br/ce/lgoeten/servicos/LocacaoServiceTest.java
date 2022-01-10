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
		Assert.assertEquals("Erro comparação valor da locacao",5.0 , locacao.getValor(), 0.1);
		Assert.assertTrue(DataUtils.isMesmaData(new Date(), locacao.getDataLocacao()));
		Assert.assertTrue(DataUtils.isMesmaData(DataUtils.obterDataComDiferencaDias(1), locacao.getDataRetorno()));
	}
}
