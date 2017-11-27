package com.techdavid.tagprocessorproject.modelo;

public class Tag implements Comparable<Tag> {
	private String name;
	private String descripcion;
	private String cierre;
	public Tag(String name, String descripcion) {
		this.name = name;
		this.descripcion = descripcion;
	}
	public Tag(String name, String descripcion, String cierre) {
		this.name = name;
		this.descripcion = descripcion;
		this.cierre = cierre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCierre() {
		return cierre;
	}
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}
	
	@Override
	public int compareTo(Tag anotherTag) {
		return this.name.compareTo(anotherTag.getName());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		
}
