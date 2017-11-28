package com.techdavid.tagprocessorproject.procesador.implementacion;

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
	public List<String> procesarTag(String datosTag, List<String> listaPlantilla, Integer indice) throws ExcepcionProcesadorTag {
		String[] arrayDatosTag = datosTag.split(separador);
		this.validarArrayDatosTag(arrayDatosTag);
		List<String> listaArrayDatosTag = this.producirListaArrayPageData(arrayDatosTag);
		String texto = this.obtenerTexto(listaArrayDatosTag);
		int cantidadTags = this.evaluarCantidadTags(datosTag);
		int indiceAperturaTag = this.obtenerIndiceElementoBody(listaPlantilla);
		int indiceTexto = indiceAperturaTag + 1;
		int indiceCierreTag = indiceAperturaTag + 2;
		String inicioTag = String.format(tagNumeral.getInicioTag(), cantidadTags);
		String finalTag = String.format(tagNumeral.getFinalTag(), cantidadTags);
		listaPlantilla.add(indiceAperturaTag, inicioTag);
		listaPlantilla.add(indiceTexto, texto);
		listaPlantilla.add(indiceCierreTag, finalTag);
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
			condicionIndice = pageData.indexOf(tagNumeral.getNombre(), fromPosition);
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

	private void validarArrayDatosTag(String[] arrayPageData) throws ExcepcionProcesadorTag {
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
		for(int indice = 1; indice < listaArrayPageData.size(); indice++) {
			elementoTexto += listaArrayPageData.get(indice) + " ";
		}
		return elementoTexto;
	}

	@Override
	public Tag getTag() {
		return tagNumeral;
	}

}
