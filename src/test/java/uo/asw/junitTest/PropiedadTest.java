package uo.asw.junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciManagerE3aApplication;
import uo.asw.dbManagement.model.Propiedad;
import uo.asw.dbManagement.tipos.PropiedadTipos;
import uo.asw.inciManager.repository.PropiedadRepository;

/**
 * Prueba la creación de una propiedad, guardado y posterior borrado en la BD
 * 
 * @version abril 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciManagerE3aApplication.class)
@WebAppConfiguration
public class PropiedadTest {
	@Autowired
	private PropiedadRepository propiedadRepository;
	
	public Propiedad pro1, pro2, pro3, pro4, pro5, pro6, pro7;
	
	@Before
	public void setUp() {
		pro1 = new Propiedad("temperatura", 20.0);
		/*pro2 = new Propiedad("presion", 50.0);
		pro3 = new Propiedad("humedad", 70.0);
		pro4 = new Propiedad("velocidad_viento", 100.0);
		pro5 = new Propiedad("velocidad_circulacion", 120.0);
		pro6 = new Propiedad("nivel_polucion", 200.0);
		pro7 = new Propiedad("calidad_aire", 70.0);*/
	}

	@Test
	public void test() {
		propiedadRepository.save(pro1);
		assertEquals(PropiedadTipos.TEMPERATURA, pro1.getPropiedad());
		assertEquals(20.0, pro1.getValor(), 0.01);
		
		/*assertEquals(PropiedadTipos.PRESION, pro2.getPropiedad());
		assertEquals(50.0, pro2.getValor(), 0.01);
		
		assertEquals(PropiedadTipos.HUMEDAD, pro3.getPropiedad());
		assertEquals(70.0, pro3.getValor(), 0.01);
		
		assertEquals(PropiedadTipos.VELOCIDAD_VIENTO, pro4.getPropiedad());
		assertEquals(100.0, pro4.getValor(), 0.01);
		
		assertEquals(PropiedadTipos.VELOCIDAD_CIRCULACION, pro5.getPropiedad());
		assertEquals(120.0, pro5.getValor(), 0.01);
		
		assertEquals(PropiedadTipos.NIVEL_POLUCION, pro6.getPropiedad());
		assertEquals(200.0, pro6.getValor(), 0.01);
		
		assertEquals(PropiedadTipos.CALIDAD_AIRE, pro7.getPropiedad());
		assertEquals(70.0, pro7.getValor(), 0.01);
		*/
		
		propiedadRepository.delete(pro1);
	}

}
