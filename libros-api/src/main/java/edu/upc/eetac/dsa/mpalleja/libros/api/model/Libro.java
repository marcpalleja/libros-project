package edu.upc.eetac.dsa.mpalleja.libros.api.model;

import java.util.List;

import javax.ws.rs.core.Link;

public class Libro {

	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public int getLibroID() {
		return LibroID;
	}
	public void setLibroID(int libroID) {
		LibroID = libroID;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getLengua() {
		return Lengua;
	}
	public void setLengua(String lengua) {
		Lengua = lengua;
	}
	public String getEdicion() {
		return Edicion;
	}
	public void setEdicion(String edicion) {
		Edicion = edicion;
	}
	public String getFechaEdicion() {
		return FechaEdicion;
	}
	public void setFechaEdicion(String fechaEdicion) {
		FechaEdicion = fechaEdicion;
	}
	public String getFechaImpresion() {
		return FechaImpresion;
	}
	public void setFechaImpresion(String fechaImpresion) {
		FechaImpresion = fechaImpresion;
	}
	public String getEditorial() {
		return Editorial;
	}
	public void setEditorial(String editorial) {
		Editorial = editorial;
	}
	public long getLastModified() {
		return lastModified;
	}
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	private List<Link> links;
	private int LibroID;
	private String Titulo;
	private String Lengua;
	private String Edicion;
	private String FechaEdicion;
	private String FechaImpresion;
	private String Editorial;
	private long lastModified;
	private long creationTimestamp;
}
