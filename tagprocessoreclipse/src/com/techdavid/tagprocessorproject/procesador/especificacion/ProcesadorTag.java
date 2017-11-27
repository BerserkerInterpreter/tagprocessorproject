package com.techdavid.tagprocessorproject.procesador.especificacion;

import java.util.List;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;

public interface ProcesadorTag {
	public default List<String> procesarTag(String pageData) throws ExcepcionProcesadorTag {
		return null;
	}
	
	public default Tag getTag() {
		return null;
	}
	
	public default String procesarTag(String pageDate, String plantilla) {
		return null;
	}
	
	public default List<String> procesarTag(String pageDate, List<String> listaPlantilla, Integer indice) throws Exception {
		return null;
	}

}
