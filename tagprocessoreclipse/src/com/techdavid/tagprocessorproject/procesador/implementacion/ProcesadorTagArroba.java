package com.techdavid.tagprocessorproject.procesador.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagArroba implements ProcesadorTag {

	private Tag tagArroba;

	private static final String separador = "\\s";
	private static final String strBodyInicio = "<body>";
	private static final String strBodyFinal = "</body>";
	private static final String validador = ".+[a-zA-Z]+";

	public ProcesadorTagArroba(Tag tagArroba) {
		this.tagArroba = tagArroba;
	}

	@Override
	public List<String> procesarTag(String datosTag, List<String> listaPlantilla) throws ExcepcionProcesadorTag {

		int indiceElementoBodyInicio = this.obtenerIndiceElementoBodyInicio(listaPlantilla);
		int indiceElementoBodyFinal = this.obtenerIndiceElementoBodyFinal(listaPlantilla);
		indiceElementoBodyFinal += 1;

		int indiceAperturaTag = indiceElementoBodyInicio + 1;
		String inicioTag = tagArroba.getInicioTag();
		String finalTag = tagArroba.getFinalTag();
		listaPlantilla.add(indiceAperturaTag, inicioTag);
		listaPlantilla.add(indiceElementoBodyFinal, finalTag);

		return listaPlantilla;
	}

	private int obtenerIndiceElementoBodyInicio(List<String> listaPlantilla) {
		Integer indiceElementoBodyInicio = null;
		for(int indice = 0; indice < listaPlantilla.size(); indice++) {
			String elemento = listaPlantilla.get(indice);
			if(elemento.equals(strBodyInicio)) {
				indiceElementoBodyInicio = indice;
			}
		}
		return indiceElementoBodyInicio;
	}

	private int obtenerIndiceElementoBodyFinal(List<String> listaPlantilla) {
		Integer indiceElementoBodyFinal = null;
		for(int indice = 0; indice < listaPlantilla.size(); indice++) {
			String elemento = listaPlantilla.get(indice);
			if(elemento.equals(strBodyFinal)) {
				indiceElementoBodyFinal = indice;
			}
		}
		return indiceElementoBodyFinal;
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

	@Override
	public Tag getTag() {
		return tagArroba;
	}

}
