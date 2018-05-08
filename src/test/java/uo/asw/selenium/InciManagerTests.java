package uo.asw.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import uo.asw.InciManagerE3aApplication;
import uo.asw.selenium.pageobjects.PO_CreateIncidence;
import uo.asw.selenium.pageobjects.PO_LoginView;
import uo.asw.selenium.pageobjects.PO_NavView;
import uo.asw.selenium.pageobjects.PO_View;
import uo.asw.selenium.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		InciManagerE3aApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class InciManagerTests {

	private static final Logger LOGGER = Logger.getLogger(InciManagerTests.class);
	
	@Value("${local.server.port:8090}")
	private int port;
	static WebDriver driver = new HtmlUnitDriver();
	static String URL = "http://localhost:";

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		URL = "http://localhost:" + port;
		LOGGER.debug(URL);
		driver.navigate().to(URL);
	}
	
	// Después de cada prueba se borran las cookies del navegador y se cierra la
	// sesión
	@After
	public void tearDown() {
		PO_NavView.logout(driver);
		driver.manage().deleteAllCookies();
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	/**
	 * Comprobamos que al iniciar la aplicación entramos al login
	 */
	@Test
	public void P01_Inicio() {
		PO_LoginView.checkElement(driver, "id", "login");
	}

	/**
	 * Comprobamos que al introducir datos incorrectos no se realiza el login
	 */
	@Test
	public void P02_IncorrectLogin() {
		// Comprobamos que estamos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario incorrecto, contraseña correcta, kind correcta
		PO_LoginView.fillForm(driver, "Paco", "123456", "person");
		// Comprobamos que permanecemos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario correcto, contraseña incorrecta, kind correcta
		PO_LoginView.fillForm(driver, "Agente1", "1234567", "person");
		// Comprobamos que permanecemos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario incorrecto, contraseña correcta, kind correcta
		PO_LoginView.fillForm(driver, "Agente1", "123456", "sensor");
		// Comprobamos que permanecemos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
	}

	/**
	 * Comprobamos que al introducir datos correctos se realiza el login y se
	 * redirige a la página adecuada.
	 */
	@Test
	public void P03_CorrectLogin() {
		// Comprobamos que estamos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario correcto, contraseña correcta, kind correcta
		PO_LoginView.fillForm(driver, "Agente1", "123456", "person");
		// Comprobamos que accedemos a la página home
		PO_LoginView.checkElement(driver, "id", "home");
	}

	/**
	 * Accedemos a la página de listar las incidencias y comprobamos que hay.
	 */
	@Test
	public void P04_ViewIncidents() {
		// Comprobamos que estamos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario correcto, contraseña correcta, kind correcta
		PO_LoginView.fillForm(driver, "Agente1", "123456", "person");
		// Comprobamos que accedemos a la página home
		PO_LoginView.checkElement(driver, "id", "crearInci");
		// Accedemos a la página de listar las incidencias
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'incidencia/list')]");
		elementos.get(0).click();
		// Comprobamos que estamos en la página correcta
		PO_NavView.checkElement(driver, "id", "tableInci");
		// Comprobar tamaño de la tabla
		List<WebElement> incidencias = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(incidencias.size() > 0);
	}

	/**
	 * Creamos de forma válida una nueva incidencia
	 */
	 @Test
	public void P05_SendNewIncidence() {
		// Comprobamos que estamos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario correcto, contraseña correcta, kind correcta
		PO_LoginView.fillForm(driver, "Agente1", "123456", "person");
		// Comprobamos que accedemos a la página home
		PO_LoginView.checkElement(driver, "id", "crearInci");
		// Accedemos a la página de listar las incidencias
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'incidencia/list')]");
		elementos.get(0).click();
		// Comprobamos que estamos en la página correcta
		PO_NavView.checkElement(driver, "id", "tableInci");
		// Esperamos a que se muestren los enlaces de paginación la lista de usuarios
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(elementos.size()-1).click();
		// Almacenamos el tamaño de la tabla
		List<WebElement> incidencias = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		int tamaño = incidencias.size();
		// Accedemos a la página de crear las incidencias
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'incidencia/create')]");
		elementos.get(0).click();
		// Comprobamos que estamos en la página correcta
		PO_CreateIncidence.checkElement(driver, "id", "sendIncidence");
		// Rellenamos el formulario
		PO_CreateIncidence.fillForm(driver, "Test5", "Incidencia creada con el " + "test5", "ACCIDENTE_CARRETERA", "12",
				"15", "0", "0", "120");
		// Comprobamos que estamos en la página correcta
		PO_CreateIncidence.checkElement(driver, "id", "tableInci");
		// Esperamos a que se muestren los enlaces de paginación la lista de usuarios
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(elementos.size()-1).click();
		// Contamos el numero de incidencias
		incidencias = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		int tamaño2 = incidencias.size();
		// Comprobamos que ha aumentado en uno
		if (tamaño==5) { //Para cuando se cambia de pagina
			assertTrue(tamaño2==1);
		}else {
			assertTrue(tamaño + 1 == tamaño2);
		}
	}
	 
	/**
	 * Creamos de forma válida una nueva incidencia
	 */
	@Test
	public void P06_ViewIncidenceDetails() {
		// Comprobamos que estamos en el Login
		PO_LoginView.checkElement(driver, "id", "login");
		// Usuario correcto, contraseña correcta, kind correcta
		PO_LoginView.fillForm(driver, "Agente1", "123456", "person");
		// Comprobamos que accedemos a la página home
		PO_LoginView.checkElement(driver, "id", "crearInci");
		// Accedemos a la página de listar las incidencias
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'incidencia/list')]");
		elementos.get(0).click();
		// Comprobamos que estamos en la página correcta
		PO_NavView.checkElement(driver, "id", "tableInci");
		//Accedemos a la vista de detalles /incidencia/details/
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'incidencia/details/')]");
		elementos.get(0).click();
		//Comprobamos que estamos en la vista de detalles. inciTitle
		PO_LoginView.checkElement(driver, "id", "inciTitle");
	}

}
