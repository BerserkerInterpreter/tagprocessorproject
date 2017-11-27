package com.techdavid.tagprocessorproject.test.fachada;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techdavid.tagprocessorproject.fachada.FachadaProcesadorTag;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FachadaProcesadorTagTest {
	@Test
	public void testProcesarTag() {
		// String testString = "#Mala sintaxis"
		String testString = "# Aprendamos HTML\n"
				+ "### Conceptos basicos";
		FachadaProcesadorTag fachadaProcesadorTag =
				new FachadaProcesadorTag();
		fachadaProcesadorTag.procesarTag(testString);
	}
	
	public static void main(String[] args) {
		FachadaProcesadorTagTest fachadaProcesadorTagTest =
				new FachadaProcesadorTagTest();
		fachadaProcesadorTagTest.testProcesarTag();
	}
}
