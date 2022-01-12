package br.ce.lgoeten.suites;

import org.junit.runners.Suite.SuiteClasses;

import br.ce.lgoeten.servicos.CalculadoraTest;
import br.ce.lgoeten.servicos.CalculoValorLocacaoTest;
import br.ce.lgoeten.servicos.LocacaoServiceTest;

@SuiteClasses({ CalculadoraTest.class, CalculoValorLocacaoTest.class, LocacaoServiceTest.class })

public class SuiteExecucao {
	// Remova se puder!
}
