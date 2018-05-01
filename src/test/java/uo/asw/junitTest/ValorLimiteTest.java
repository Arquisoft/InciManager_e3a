package uo.asw.junitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciManagerE3aApplication;
import uo.asw.dbManagement.model.ValorLimite;

/**
 * Prueba la clase ValorLimite de los umbrales permitidos en las
 * propiedades de las incidencias
 * 
 * @version abril 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciManagerE3aApplication.class)
@WebAppConfiguration
public class ValorLimiteTest {
	private ValorLimite vlTemp;
	private ValorLimite vl2;
	
	@Before
	public void setUp() {
		vlTemp = new ValorLimite(100, 0, "temperatura", true,
				true);
		vl2 = new ValorLimite("presion", 100, 40);
		
	}

	@Test
	public void valorLimiteTest() {
		assertEquals("temperatura", vlTemp.getPropiedad());
		assertEquals(100.0, vlTemp.getValorMax(), 0.01);
		assertEquals(0.0, vlTemp.getValorMin(), 0.01);
		assertTrue(vlTemp.isMaxCritico());
		assertTrue(vlTemp.isMinCritico());
		
		vl2.setPropiedad("humedad");
		assertEquals("humedad", vl2.getPropiedad());
		vl2.setValorMax(100);
		assertEquals(100.0, vl2.getValorMax(), 0.01);
		vl2.setValorMin(0);
		assertEquals(0.0, vl2.getValorMin(), 0.01);
		
		assertFalse(vlTemp.equals(vl2));
		
	}

}
