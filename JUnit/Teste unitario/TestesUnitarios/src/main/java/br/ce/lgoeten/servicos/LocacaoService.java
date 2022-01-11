package br.ce.lgoeten.servicos;

import static br.ce.lgoeten.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.lgoeten.entidades.Filme;
import br.ce.lgoeten.entidades.Locacao;
import br.ce.lgoeten.entidades.Usuario;
import br.ce.lgoeten.exception.FilmeSemEstoqueException;
import br.ce.lgoeten.exception.LocadoraException;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws LocadoraException, FilmeSemEstoqueException {
		if (usuario == null) {
			throw new LocadoraException("Usu�rio vazio");
		}
		
		if (filme== null) {
			throw new LocadoraException("Filme vazio");
		}
		
		if (filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException();
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar m�todo para salvar

		return locacao;
	}

}