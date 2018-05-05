package uo.asw.junitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciManagerE3aApplication;
import uo.asw.dbManagement.model.Categoria;
import uo.asw.dbManagement.tipos.CategoriaTipos;
import uo.asw.inciManager.repository.CategoriaRepository;


/**
 * Prueba la creación de una categoria, guardado y posterior borrado en la BD
 * 
 * @version abril 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciManagerE3aApplication.class)
@WebAppConfiguration
public class CategoriaTest {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8;
	
	@Before
	public void setUp() {
		cat1 = new Categoria("accidente_carretera");
		cat2 = new Categoria("inundacion");
		cat3 = new Categoria("fuego");
		cat4 = new Categoria("accidente_aereo");
		cat5 = new Categoria("meteorologica");
		cat6 = new Categoria(CategoriaTipos.AMBIENTE);
		cat7 = new Categoria(CategoriaTipos.AUTOMATICO);
		cat8 = new Categoria(CategoriaTipos.CONTAMINACION);
		
	}

	@Test
	public void categoriaTest() {
		assertEquals(CategoriaTipos.ACCIDENTE_CARRETERA, cat1.getCategoria());
		
		cat1.setCategoria(CategoriaTipos.ACCIDENTE_AEREO);
		categoriaRepository.save(cat1);
		assertEquals(CategoriaTipos.ACCIDENTE_AEREO, cat1.getCategoria());
		
		assertEquals(CategoriaTipos.INUNDACION, cat2.getCategoria());
		assertEquals(CategoriaTipos.FUEGO, cat3.getCategoria());
		assertEquals(CategoriaTipos.ACCIDENTE_AEREO, cat4.getCategoria());
		
		String s1 = cat1.toString();
		String s2 = cat4.toString();
		assertTrue(s1.equals(s2));
		
		assertEquals(CategoriaTipos.METEOROLOGICA, cat5.getCategoria());
		assertEquals(CategoriaTipos.AMBIENTE, cat6.getCategoria());
		assertEquals(CategoriaTipos.AUTOMATICO, cat7.getCategoria());
		assertEquals(CategoriaTipos.CONTAMINACION, cat8.getCategoria());
		
		categoriaRepository.delete(cat1);
	}
	
}
