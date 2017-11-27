package com.techdavid.tagprocessorproject.controlador;

import java.util.List;

import com.techdavid.tagprocessor.factoria.FactoriaProcesadorTag;
import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ControladorTag {
	
	private static ControladorTag controladorTag;
	
	public static ControladorTag getControladorTag() {
		if(controladorTag == null) {
			controladorTag = new ControladorTag();
		}
		return controladorTag;
	}

	public String procesarTag(String pageData) {
		String resultado = "";
		try {
			ProcesadorTag procesadorTag = 
					FactoriaProcesadorTag.getFactoriaProcesadorTag().getProcesadorTag();
			List<String> listaPageTags = procesadorTag.procesarTag(pageData);
			for(String strTag : listaPageTags) {
				resultado += strTag;
			}
		} catch (ExcepcionProcesadorTag ex) {
			ex.printStackTrace();
			resultado = pageData;
		}
		return resultado;
	}

}
