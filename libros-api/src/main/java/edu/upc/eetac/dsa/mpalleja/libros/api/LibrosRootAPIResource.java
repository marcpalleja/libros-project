package edu.upc.eetac.dsa.mpalleja.libros.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
 
import edu.upc.eetac.dsa.mpalleja.libros.api.LibrosRootAPIResource;
 
@Path("/")
public class LibrosRootAPIResource {
	@GET
	public LibrosRootAPIResource getRootAPI() {
		LibrosRootAPIResource api = new LibrosRootAPIResource();
		return api;
	}
}