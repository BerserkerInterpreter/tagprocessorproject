package com.techdavid.tagprocessorproject.procesador.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.NavigableMap;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;

public class ProcesadorTagPadre implements ProcesadorTag {

	private String plantilla;
	private Set<Tag> listaTags;
	private Set<ProcesadorTag> listaProcesadorTag =
			new HashSet<ProcesadorTag>();

	private static final String constanteSaltoLinea = "\\\\n";
	private static final String constanteComa = ",";
	private static final Integer constanteMenosUno = -1;

	public ProcesadorTagPadre(Set<Tag> listaTags, String plantilla, Set<ProcesadorTag> listaProcesadorTag) {
		this.plantilla = plantilla;
		this.listaTags = listaTags;
		this.listaProcesadorTag = listaProcesadorTag;
	}

	@Override
	public List<String> procesarTag(String datosTag) throws ExcepcionProcesadorTag {
		try {
			String[] arrayDatosTag = datosTag.split(constanteSaltoLinea);
			List<String> listaElementosPlantilla = this.producirArrayElementosPlantilla(datosTag);
			this.procesarTags(arrayDatosTag, listaElementosPlantilla);
			listaElementosPlantilla.removeIf((String elemento)-> elemento == null);
			return listaElementosPlantilla;
		} catch(Exception ex) {
			String mensaje = "ERROR: Ha ocurrido una excepcion al realizar el procesamiento de los tags suministrados.";
			throw new ExcepcionProcesadorTag(mensaje, ex);
		}
	}

	private void procesarTags(String[] arrayDatosTag, List<String> listaElementosPlantilla)
		throws Exception {
		for(String datosTag : arrayDatosTag) {
			Map<Integer, Tag> listaBusquedaTags = this.buscarTags(datosTag);
			for(Map.Entry<Integer, Tag> entradaTag : listaBusquedaTags.entrySet()) {
				for(ProcesadorTag procesadorTag : listaProcesadorTag) {
					if(entradaTag.getValue().equals(procesadorTag.getTag())) {
						listaElementosPlantilla =
								procesadorTag.procesarTag(datosTag, listaElementosPlantilla);
						break;
					}
				}
			}
		}
	}

	public List<String> producirArrayElementosPlantilla(String datosTag) {
		datosTag = datosTag.trim();
		String[] arrayElementosPlantilla = plantilla.split(constanteComa);
		List<String> listaElementosPlantilla = new ArrayList<>();
		listaElementosPlantilla = Arrays.asList(arrayElementosPlantilla);
		List<String> listaElementosPlantillaModificable = new ArrayList<>();
		listaElementosPlantillaModificable.addAll(listaElementosPlantilla);
		return listaElementosPlantillaModificable;
	}

	private Map<Integer, Tag> buscarTags(String datosTag) {
		NavigableMap <Integer, Tag> listaBusquedaTags = new TreeMap<>();
		for(Tag tag : listaTags) {
			int indice = datosTag.indexOf(tag.getNombre());
			if(indice == constanteMenosUno) {
				continue;
			}
			listaBusquedaTags.put(indice, tag);
		}
		listaBusquedaTags = listaBusquedaTags.descendingMap();
		return listaBusquedaTags;
	}

}
