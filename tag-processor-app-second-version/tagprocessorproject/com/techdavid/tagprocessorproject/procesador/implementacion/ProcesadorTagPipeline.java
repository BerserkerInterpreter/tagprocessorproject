package com.techdavid.tagprocessorproject.procesador.implementacion;

import java.util.List;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagPipeline implements ProcesadorTag {

	private Tag tagPipeline;
	
	public ProcesadorTagPipeline(Tag tagPipeline) {
		this.tagPipeline = tagPipeline;
	}

	@Override
	public List<String> procesarTag(String datosTag, List<String> listaPlantilla) throws ExcepcionProcesadorTag {
		int indiceElementoInicio = 0;
		String inicioTag = tagPipeline.getInicioTag();
		String finalTag = tagPipeline.getFinalTag();
		listaPlantilla.add(indiceElementoInicio, inicioTag);
		listaPlantilla.add(finalTag);
		return listaPlantilla;
	}
	
	@Override
	public Tag getTag() {
		return tagPipeline;
	}

}
