package com.techdavid.tagprocessorproject.procesador.implementacion;

import java.util.List;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagAmpersand implements ProcesadorTag {

	private Tag tagAmpersand;
	
	public ProcesadorTagAmpersand(Tag tagAmpersand) {
		this.tagAmpersand = tagAmpersand;
	}

	@Override
	public List<String> procesarTag(String datosTag, List<String> listaPlantilla) throws ExcepcionProcesadorTag {
		int indiceElementoInicio = 0;
		int indiceElementoFinal = 1;
		String inicioTag = tagAmpersand.getInicioTag();
		String finalTag = tagAmpersand.getFinalTag();
		listaPlantilla.add(indiceElementoInicio, inicioTag);
		listaPlantilla.add(indiceElementoFinal, finalTag);
		return listaPlantilla;
	}
	

	@Override
	public Tag getTag() {
		return tagAmpersand;
	}

}
