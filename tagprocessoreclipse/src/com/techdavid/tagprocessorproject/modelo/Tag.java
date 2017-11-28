package com.techdavid.tagprocessorproject.modelo;

public class Tag implements Comparable<Tag> {
	private String nombre;
	private String inicioTag;
	private String finalTag;
	public Tag(String nombre, String inicioTag) {
		this.nombre = nombre;
		this.inicioTag = inicioTag;
	}
	public Tag(String nombre, String inicioTag, String finalTag) {
		this.nombre = nombre;
		this.inicioTag = inicioTag;
		this.finalTag = finalTag;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getInicioTag() {
		return inicioTag;
	}
	public void setInicioTag(String inicioTag) {
		this.inicioTag = inicioTag;
	}
	public String getFinalTag() {
		return finalTag;
	}
	public void setFinalTag(String finalTag) {
		this.finalTag = finalTag;
	}
	
	@Override
	public int compareTo(Tag anotherTag) {
		return this.finalTag.compareTo(anotherTag.getFinalTag());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finalTag == null) ? 0 : finalTag.hashCode());
		result = prime * result + ((inicioTag == null) ? 0 : inicioTag.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (finalTag == null) {
			if (other.finalTag != null)
				return false;
		} else if (!finalTag.equals(other.finalTag))
			return false;
		if (inicioTag == null) {
			if (other.inicioTag != null)
				return false;
		} else if (!inicioTag.equals(other.inicioTag))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
		
}

