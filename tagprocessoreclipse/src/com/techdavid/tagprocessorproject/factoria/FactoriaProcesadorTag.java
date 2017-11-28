package com.techdavid.tagprocessorproject.factoria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagNumeral;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagPadre;

public class FactoriaProcesadorTag {
	
	private static FactoriaProcesadorTag factoriaProcesadorTag;
	private Set<Tag> listaTags = new TreeSet<Tag>();
	private static final String constanteNumeral = "#";
	private static final String constanteIgual = "=";
	private static final String filePath = "/home/ld-david/Documentos/workspace_eclipse/workspace_techandsolve/tagprocessorproject/src/main/resources/com/techdavid/tagprocessorproject/configuracion/tags.properties";
	
	public static final String plantilla = 
			"<!DOCTYPE​ ​ html>,<html>,<head>,<meta charset=\"utf-8\">,<title>,</title>,</head>,<body>,</body>,</html>"; 
	
	private FactoriaProcesadorTag() throws ExcepcionProcesadorTag { 
		this.cargarTags(filePath);
	}
	
	public static FactoriaProcesadorTag getFactoriaProcesadorTag() throws ExcepcionProcesadorTag {
		if(factoriaProcesadorTag == null) {
			factoriaProcesadorTag = new FactoriaProcesadorTag();
		}
		return factoriaProcesadorTag;
	}
	
	public ProcesadorTag getProcesadorTag() {
		Set<ProcesadorTag> listaProcesadorTag = new HashSet<ProcesadorTag>();
		ProcesadorTag procesadorTagNumeral = this.getProcesadorTagNumeral();
		listaProcesadorTag.add(procesadorTagNumeral);
		ProcesadorTag procesadorTag = new ProcesadorTagPadre(listaTags, plantilla, listaProcesadorTag);
		return procesadorTag;
	}
	
	private ProcesadorTag getProcesadorTagNumeral() {
		Tag tagNumeral = null;
		for(Tag tag : listaTags) {
			if(tag.getNombre().equals(constanteNumeral)) {
				tagNumeral = tag;
			}
		}
		ProcesadorTag procesadorTagNumeral = new ProcesadorTagNumeral(tagNumeral);
		return procesadorTagNumeral;
	}
	
	private void cargarTags(String filePath) throws ExcepcionProcesadorTag {
		try(FileReader fileReader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);) {
			while(true) {
				String tagConfigurado = bufferedReader.readLine();
				if(tagConfigurado == null) {
					break;
				}
				this.adicionarTag(tagConfigurado);
			}
		} catch(Exception ex) {
			String mensaje = "ERROR: An error has ocurred while reading the tag file.";
			throw new ExcepcionProcesadorTag(mensaje, ex);
		}
	}
	
	private void adicionarTag(String tagConfigurado) {
		String[] arrayTagConfigurado = tagConfigurado.split(constanteIgual);
		String nombreTag = arrayTagConfigurado[0];
		String inicioTag = arrayTagConfigurado[1];
		String finalTag = arrayTagConfigurado[2];
		Tag tag = new Tag(nombreTag, inicioTag, finalTag);
		listaTags.add(tag);
	}
}

