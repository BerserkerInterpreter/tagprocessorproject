package com.techdavid.tagprocessorproject.procesador.especificacion;

import java.util.List;
import java.util.Map;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;

public interface ProcesadorTag {
	
	public default List<String> procesarTag(String datosTag) throws ExcepcionProcesadorTag {
		return null;
	}
	
	public default Tag getTag() {
		return null;
	}
	
	public default String procesarTag(String datosTag, String plantilla) {
		return null;
	}
	
	public default List<String> procesarTag(String datosTag, List<String> listaPlantilla) throws Exception {
		return null;
	}

}
