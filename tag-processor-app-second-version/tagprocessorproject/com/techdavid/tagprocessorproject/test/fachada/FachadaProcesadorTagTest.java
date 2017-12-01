package com.techdavid.tagprocessorproject.test.fachada;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.techdavid.tagprocessorproject.fachada.FachadaProcesadorTag;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FachadaProcesadorTagTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void testProcesarTag() {
		String testString = "#​ Aprendamos​ HTML\n"
				+ "### Conceptos basicos";
		FachadaProcesadorTag fachadaProcesadorTag =
				new FachadaProcesadorTag();
		ResponseEntity responseEntity = fachadaProcesadorTag.procesarTag(testString);
		String respuestaResponseEntity = (String) responseEntity.getBody();
		Assert.assertNotNull(respuestaResponseEntity);
		Assert.assertNotSame("ERROR: La respuesta esta vacia.", "", respuestaResponseEntity);
		String resultadoEsperado = 
				"<!DOCTYPE​ ​ html><html><head><meta charset=\"utf-8\">"
				+ "<title></title></head><body><h1>Aprendamos HTML </h1>"
				+ "<h3>Conceptos basicos </h3></body></html>";
		Assert.assertEquals(resultadoEsperado, respuestaResponseEntity);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testProcesarTagFallo() {
		String stringPrueba = "#​Aprendamos​ HTML";
		FachadaProcesadorTag fachadaProcesadorTag =
				new FachadaProcesadorTag();
		ResponseEntity responseEntity = fachadaProcesadorTag.procesarTag(stringPrueba);
		String respuesta = (String) responseEntity.getBody();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(stringPrueba, respuesta);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testProcesarAnidadoArroba() {
		String stringPrueba = "@# ​Aprendamos​ HTML";
		FachadaProcesadorTag fachadaProcesadorTag =
				new FachadaProcesadorTag();
		ResponseEntity responseEntity = fachadaProcesadorTag.procesarTag(stringPrueba);
		String respuesta = (String) responseEntity.getBody();
		Assert.assertNotNull(respuesta);
		String resultadoEsperado = 
				"<!DOCTYPE​ ​ html><html><head><meta charset=\"utf-8\">"
				+ "<title></title></head><body><div><h1>Aprendamos HTML </h1>"
				+ "</div></body></html>";
		Assert.assertEquals(resultadoEsperado, respuesta);
	}

}
