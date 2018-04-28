package uo.asw.junitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciManagerE3aApplication;
import uo.asw.chatbot.Chat;

/**
 * Prueba la clase Chat
 * 
 * @version abril 2018
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciManagerE3aApplication.class)
@WebAppConfiguration
public class ChatTest {
	private Chat chat;
	
	@Before
	public void setUp() {
		chat = new Chat();
	}

	@Test
	public void inicializarTest() {
		assertTrue (chat.getMensajes().size() > 0);
	}
	
	@Test
	public void calcularRespuestaTest1() {
		//crear incidencia
		int num_mensajes = chat.getMensajes().size();
		chat.calcularRespuesta("CREAR");
		assertNotNull(chat.getInci());
		assertTrue(num_mensajes < chat.getMensajes().size());
	}

	@Test
	public void calcularRespuestaTest2() {
		//opcion listar
		int num_mensajes = chat.getMensajes().size();
		chat.calcularRespuesta("LISTA");
		assertNull(chat.getInci());
		assertTrue(num_mensajes < chat.getMensajes().size());
	}
	
	@Test
	public void calcularRespuestaTest3() {
		//saludo
		int num_mensajes = chat.getMensajes().size();
		chat.calcularRespuesta("HOLA");
		assertNull(chat.getInci());
		assertEquals(num_mensajes, chat.getMensajes().size());
	}
	
	@Test
	public void calcularRespuestaTest4() {
		//saludo
		int num_mensajes = chat.getMensajes().size();
		chat.calcularRespuesta("dfgsfgsg");
		assertNull(chat.getInci());
		assertTrue(num_mensajes < chat.getMensajes().size());
		assertEquals("Lo siento, no le he entendido", 
				chat.getMensajes().get(num_mensajes).getContenido());
	}
	
	

}
