package com.techdavid.tagprocessorproject.procesador.implementacion;

import java.util.List;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagArroba implements ProcesadorTag {

	private Tag tagArroba;
	
	public ProcesadorTagArroba(Tag tagArroba) {
		this.tagArroba = tagArroba;
	}

	@Override
	public List<String> procesarTag(String datosTag, List<String> listaPlantilla) throws ExcepcionProcesadorTag {
		int indiceElementoInicio = 0;
		String inicioTag = tagArroba.getInicioTag();
		String finalTag = tagArroba.getFinalTag();
		listaPlantilla.add(indiceElementoInicio, inicioTag);
		listaPlantilla.add(finalTag);
		return listaPlantilla;
	}

	@Override
	public Tag getTag() {
		return tagArroba;
	}

}