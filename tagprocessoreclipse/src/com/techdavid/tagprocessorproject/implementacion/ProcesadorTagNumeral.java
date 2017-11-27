package com.techdavid.tagprocessorproject.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagNumeral implements ProcesadorTag {

	private Tag tagNumeral;
	private static final String separador = "\\s";
	private static final String strBody = "</body>";
	private static final String validador = ".+[a-zA-Z]+";
	
	public ProcesadorTagNumeral(Tag tagNumeral) {
		this.tagNumeral = tagNumeral;
	}

	@Override
	public List<String> procesarTag(String pageData, List<String> listaPlantilla, Integer indice) throws ExcepcionProcesadorTag {
		String[] arrayPageData = pageData.split(separador);
		this.validarArrayPageData(arrayPageData);
		List<String> listaArrayPageData = this.producirListaArrayPageData(arrayPageData);
		String texto = this.obtenerTexto(listaArrayPageData);
		int cantidadTags = this.evaluarCantidadTags(pageData);
		int indiceAperturaTag = this.obtenerIndiceElementoBody(listaPlantilla);
		int indiceTexto = indiceAperturaTag + 1;
		int indiceCierreTag = indiceAperturaTag + 2;
		String descripcionTag = String.format(tagNumeral.getDescripcion(), cantidadTags);
		String cierreTag = String.format(tagNumeral.getCierre(), cantidadTags);
		listaPlantilla.add(indiceAperturaTag, descripcionTag);
		listaPlantilla.add(indiceTexto, texto);
		listaPlantilla.add(indiceCierreTag, cierreTag);
		return listaPlantilla;
	}
	
	private int obtenerIndiceElementoBody(List<String> listaPlantilla) {
		Integer indiceElementoBody = null;
		for(int indice = 0; indice < listaPlantilla.size(); indice++) {
			String elemento = listaPlantilla.get(indice);
			if(elemento.equals(strBody)) {
				indiceElementoBody = indice;
			}
		}
		return indiceElementoBody;
	}

	private int evaluarCantidadTags(String pageData) throws ExcepcionProcesadorTag {
		int condicionIndice = 0;
		int cantidadEncuentros = 0; 
		int fromPosition = 0;
		do {
			condicionIndice = pageData.indexOf(tagNumeral.getName(), fromPosition);
			if(condicionIndice != -1) {
				++cantidadEncuentros;
				if(cantidadEncuentros > 6) {
					String mensaje = "ERROR: La cantidad de tags # excede el limite fijado.";
					throw new ExcepcionProcesadorTag(mensaje);
				}
				fromPosition += 1;
			}
		} while(condicionIndice != -1);
		return cantidadEncuentros;
	}

	private void validarArrayPageData(String[] arrayPageData) throws ExcepcionProcesadorTag {
		String strTags = arrayPageData[0];
		if(strTags.matches(validador)) {
			String message = "ERROR: Mala sintaxis en el string dado.";
			throw new ExcepcionProcesadorTag(message);
		}
	}
	
	private List<String> producirListaArrayPageData(String[] arrayPageData) {
		List<String> listaArrayPageData = Arrays.asList(arrayPageData);
		List<String> listaArrayPageDataModificable = new ArrayList<>();
		listaArrayPageDataModificable.addAll(listaArrayPageData);
		return listaArrayPageData;
	}
	
	private String obtenerTexto(List<String> listaArrayPageData) {
		String elementoTexto = "";
		for(int i = 1; i < listaArrayPageData.size();i++) {
			elementoTexto += listaArrayPageData.get(i) + " ";
		}
		return elementoTexto;
	}

	@Override
	public Tag getTag() {
		return tagNumeral;
	}

}
