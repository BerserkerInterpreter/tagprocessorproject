package com.techdavid.tagprocessorproject.controlador;

import java.util.List;

import com.techdavid.tagprocessorproject.factoria.FactoriaProcesadorTag;
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

	public String procesarTag(String datosTag) {
		String resultado = "";
		try {
			ProcesadorTag procesadorTag = 
					FactoriaProcesadorTag.getFactoriaProcesadorTag().getProcesadorTag();
			List<String> listaDatosTag = procesadorTag.procesarTag(datosTag);
			for(String strDatoTag : listaDatosTag) {
				resultado += strDatoTag;
			}
		} catch (ExcepcionProcesadorTag ex) {
			ex.printStackTrace();
			resultado = datosTag;
		}
		return resultado;
	}

}

