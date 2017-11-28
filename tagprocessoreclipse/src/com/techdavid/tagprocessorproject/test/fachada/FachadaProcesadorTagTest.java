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
	}

}

