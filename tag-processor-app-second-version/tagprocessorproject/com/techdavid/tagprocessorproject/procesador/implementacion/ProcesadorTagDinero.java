package com.techdavid.tagprocessorproject.procesador.implementacion;

import java.util.List;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagDinero implements ProcesadorTag {
	private Tag tagDinero;
	
	public ProcesadorTagDinero(Tag tagDinero) {
		this.tagDinero = tagDinero;
	}

	@Override
	public List<String> procesarTag(String datosTag, List<String> listaPlantilla) throws ExcepcionProcesadorTag {
		int indiceElementoInicio = 0;
		String inicioTag = tagDinero.getInicioTag();
		String finalTag = tagDinero.getFinalTag();
		listaPlantilla.add(indiceElementoInicio, inicioTag);
		listaPlantilla.add(finalTag);
		return listaPlantilla;
	}
	
	@Override
	public Tag getTag() {
		return tagDinero;
	}
}
