package com.techdavid.tagprocessorproject.factoria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.techdavid.tagprocessorproject.excepcion.ExcepcionProcesadorTag;
import com.techdavid.tagprocessorproject.modelo.Tag;
import com.techdavid.tagprocessorproject.procesador.especificacion.ProcesadorTag;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagAmpersand;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagArroba;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagDinero;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagNumeral;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagPadre;
import com.techdavid.tagprocessorproject.procesador.implementacion.ProcesadorTagPipeline;

public class FactoriaProcesadorTag {
	
	private static FactoriaProcesadorTag factoriaProcesadorTag;
	private Set<Tag> listaTags = new TreeSet<Tag>();
	private static final String constanteNumeral = "#";
	private static final String constanteIgual = "=";
	private static final String constanteArroba = "@";
	private static final String constanteDinero = "$";
	private static final String constanteAmpersand = "&";
	private static final String constantePipeline = "!";
	private static final String filePath = "tags.properties";
	
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
		ProcesadorTag procesadorTagArroba = this.getProcesadorTagArroba();
		ProcesadorTag procesadorTagDinero = this.getProcesadorTagDinero();
		ProcesadorTag procesadorTagAmpersand = this.getProcesadorAmpersand();
		ProcesadorTag procesadorTagPipeline = this.getProcesadorPipeline();
		listaProcesadorTag.add(procesadorTagNumeral);
		listaProcesadorTag.add(procesadorTagArroba);
		listaProcesadorTag.add(procesadorTagDinero);
		listaProcesadorTag.add(procesadorTagAmpersand);
		listaProcesadorTag.add(procesadorTagPipeline);
		ProcesadorTag procesadorTag = new ProcesadorTagPadre(listaTags, plantilla, listaProcesadorTag);
		return procesadorTag;
	}
	
	private ProcesadorTag getProcesadorPipeline() {
		Tag tagPipeline = null;
		for(Tag tag : listaTags) {
			if(tag.getNombre().equals(constantePipeline)) {
				tagPipeline = tag;
			}
		}
		ProcesadorTag procesadorTagPipeline = new ProcesadorTagPipeline(tagPipeline);
		return procesadorTagPipeline;
	}
	
	private ProcesadorTag getProcesadorAmpersand() {
		Tag tagAmpersand = null;
		for(Tag tag : listaTags) {
			if(tag.getNombre().equals(constanteAmpersand)) {
				tagAmpersand = tag;
			}
		}
		ProcesadorTag procesadorTagAmpersand = new ProcesadorTagAmpersand(tagAmpersand);
		return procesadorTagAmpersand;
	}
	
	private ProcesadorTag getProcesadorTagDinero() {
		Tag tagDinero = null;
		for(Tag tag : listaTags) {
			if(tag.getNombre().equals(constanteDinero)) {
				tagDinero = tag;
			}
		}
		ProcesadorTag procesadorTagDinero = new ProcesadorTagDinero(tagDinero);
		return procesadorTagDinero;
	}
	
	private ProcesadorTag getProcesadorTagArroba() {
		Tag tagArroba = null;
		for(Tag tag : listaTags) {
			if(tag.getNombre().equals(constanteArroba)) {
				tagArroba = tag;
			}
		}
		ProcesadorTag procesadorTagArroba = new ProcesadorTagArroba(tagArroba);
		return procesadorTagArroba;
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
		Path path = Paths.get(filePath);
		path = path.toAbsolutePath();
		try(FileReader fileReader = new FileReader(path.toString());
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
