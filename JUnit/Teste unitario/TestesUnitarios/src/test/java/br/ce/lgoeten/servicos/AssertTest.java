package br.ce.lgoeten.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.lgoeten.entidades.Usuario;

public class AssertTest {

	
	@Test
	public void teste() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparação",1, 1);
		Assert.assertEquals(0.51, 0.51, 0.01);
		Assert.assertEquals("bola", "bola");
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = null;
		
		Assert.assertEquals(u1, u2);
		Assert.assertNotEquals(u1, u3);
		
		Assert.assertNull(u3);
		Assert.assertNotNull(u1);
	}
}
