package edu.upc.eetac.dsa.mpalleja.libros.api.model;

public class Review {

	private int ReviewID;
	private String Username;
	private String Nombre;
	private int IDLibro;
	private String Contenido;
	private long lastModified;
	private long creationTimestamp;
	
	public int getReviewID() {
		return ReviewID;
	}
	public void setReviewID(int reviewID) {
		ReviewID = reviewID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getIDLibro() {
		return IDLibro;
	}
	public void setIDLibro(int iDLibro) {
		IDLibro = iDLibro;
	}
	public String getContenido() {
		return Contenido;
	}
	public void setContenido(String contenido) {
		Contenido = contenido;
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
	
}
