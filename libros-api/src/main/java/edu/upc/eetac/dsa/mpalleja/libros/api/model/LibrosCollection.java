package edu.upc.eetac.dsa.mpalleja.libros.api.model;

import java.util.ArrayList;
import java.util.List;
 
public class LibrosCollection {
	private List<Libro> libros;
 
	public LibrosCollection() {
		super();
		libros = new ArrayList<>();
	}
 
	public List<Libro> getLibros() {
		return libros;
	}
 
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
 
	public void addLibros(Libro Libro) {
		libros.add(Libro);
	}
}
