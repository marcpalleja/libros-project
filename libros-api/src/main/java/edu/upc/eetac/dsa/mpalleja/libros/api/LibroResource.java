package edu.upc.eetac.dsa.mpalleja.libros.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import com.mysql.jdbc.Statement;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.mpalleja.libros.api.model.Libro;
import edu.upc.eetac.dsa.mpalleja.libros.api.model.LibrosCollection;

@Path("/libros")
public class LibroResource {
	@Context
	private SecurityContext security;
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	private String GET_LIBROS_QUERY = "SELECT * FROM Libros";
	 
	@GET
	@Produces(MediaType.LIBROS_API_LIBRO_COLLECTION)
	public LibrosCollection getLibros() {
		LibrosCollection libros = new LibrosCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_LIBROS_QUERY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setLibroID(rs.getInt("LibroID"));
				libro.setTitulo(rs.getString("Titulo"));
				libro.setLengua(rs.getString("Lengua"));
				libro.setEdicion(rs.getString("Edicion"));
				libro.setFechaEdicion(rs.getString("FechaEdicion"));
				libro.setFechaImpresion(rs.getString("FechaImpresion"));
				libro.setEditorial(rs.getString("Editorial"));
				//libro.setLastModified(rs.getTimestamp("last_modified")
						//.getTime());
				//libro.setCreationTimestamp(rs
						//.getTimestamp("creation_timestamp").getTime());
				libros.addLibros(libro);
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return libros;
	}
	
	private String GET_LIBRO_BY_ID_QUERY = "SELECT * FROM Libros WHERE LibroID=?";
	 
	public Libro getLibro(@PathParam("LibroID") String LibroID) {
		Libro libro = new Libro();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_LIBRO_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(LibroID));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				libro.setLibroID(rs.getInt("LibroID"));
				libro.setTitulo(rs.getString("Titulo"));
				libro.setLengua(rs.getString("Lengua"));
				libro.setEdicion(rs.getString("Edicion"));
				libro.setFechaEdicion(rs.getString("FechaEdicion"));
				libro.setFechaImpresion(rs.getString("FechaImpresion"));
				libro.setEditorial(rs.getString("Editorial"));
				//libro.setLastModified(rs.getTimestamp("last_modified")
						//.getTime());
				//libro.setCreationTimestamp(rs
						//.getTimestamp("creation_timestamp").getTime());
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return libro;
	}	
	
	@GET
	@Path("/{LibroID}")
	@Produces(MediaType.LIBROS_API_LIBRO)
	public Response getBook(@PathParam("LibroID") String LibroID,
			@Context Request request) {

		CacheControl cc = new CacheControl();
		Libro libro = getLibro(LibroID);
	 
		
		EntityTag eTag = new EntityTag(libro.getTitulo() + libro.getLengua() + 
				libro.getEdicion() + libro.getFechaEdicion() + libro.getFechaImpresion() + libro.getEditorial());
	 
		Response.ResponseBuilder rb = request.evaluatePreconditions(eTag);
	 
		if (rb != null) {
			return rb.cacheControl(cc).tag(eTag).build();
		}
	 
		rb = Response.ok(libro).cacheControl(cc).tag(eTag);
	 
		return rb.build();
	}
	
	private Libro getLibroFromDatabase(String LibroID) {
		Libro libro = new Libro();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_LIBRO_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(LibroID));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				libro.setLibroID(rs.getInt("LibroID"));
				libro.setTitulo(rs.getString("Titulo"));
				libro.setLengua(rs.getString("Lengua"));
				libro.setEdicion(rs.getString("Edicion"));
				libro.setFechaEdicion(rs.getString("FechaEdicion"));
				libro.setFechaImpresion(rs.getString("FechaImpresion"));
				libro.setEditorial(rs.getString("Editorial"));
				
			} else {
				throw new NotFoundException("There's no sting with stingid="
						+ LibroID);
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return libro;
	}
	
	private String INSERT_LIBRO_QUERY = "INSERT INTO Libros(Titulo, Lengua, Edicion, FechaEdicion, FechaImpresion, Editorial) VALUES (?, ?, ?, ?, ?, ?)";
	 
	@POST
	@Consumes(MediaType.LIBROS_API_LIBRO)
	@Produces(MediaType.LIBROS_API_LIBRO)
	public Libro createSting(Libro libro) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_LIBRO_QUERY,
					Statement.RETURN_GENERATED_KEYS);
	 
			stmt.setString(1, libro.getTitulo());
			stmt.setString(2, libro.getLengua());
			stmt.setString(3, libro.getEdicion());
			stmt.setString(4, libro.getFechaEdicion());
			stmt.setString(5, libro.getFechaImpresion());
			stmt.setString(6, libro.getEditorial());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int LibroID = rs.getInt(1);
	 
				libro = getLibro(Integer.toString(LibroID));
			} else {
				// Something has failed...
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return libro;
	}
	
	private void validateLibro(Libro libro) {
		
		if (libro.getTitulo() == null || libro.getTitulo().length() > 100)
			throw new BadRequestException("Titulo can't be null or greater than 100 characters.");
		if (libro.getLengua() == null || libro.getLengua().length() > 100)
			throw new BadRequestException("Lengua can't be null or greater than 100 characters.");
		if (libro.getEdicion() == null || libro.getEdicion().length() > 100)
			throw new BadRequestException("Edicion can't be null or greater than 100 characters.");
		if (libro.getFechaEdicion() == null || libro.getFechaEdicion().length() > 100)
			throw new BadRequestException("FechaEdicion can't be null or greater than 100 characters.");
		if (libro.getFechaImpresion() == null || libro.getFechaImpresion().length() > 100)
			throw new BadRequestException("FechaImpresion can't be null or greater than 100 characters.");
		if (libro.getEditorial() == null || libro.getEditorial().length() > 100)
			throw new BadRequestException("Editorial can't be null or greater than 100 characters.");
		
	}
	
	
	private String DELETE_LIBRO_QUERY = "DELETE FROM Libros WHERE LibroID=?";
	 
	@DELETE
	@Path("/{LibroID}")
	public void deleteLibro(@PathParam("LibroID") String LibroID) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_LIBRO_QUERY);
			stmt.setInt(1, Integer.valueOf(LibroID));
	 
			int rows = stmt.executeUpdate();
			if (rows == 0)
				;// Deleting inexistent sting
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	private String UPDATE_LIBRO_QUERY = "UPDATE Libros SET Titulo=ifnull(?, Titulo) WHERE LibroID=?";
	 
	@PUT
	@Path("/{LibroID}")
	@Consumes(MediaType.LIBROS_API_LIBRO)@Produces(MediaType.LIBROS_API_LIBRO)
	public Libro updateLibro(@PathParam("LibroID") String LibroID, Libro libro) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_LIBRO_QUERY);
			stmt.setString(1, libro.getTitulo());
			/*stmt.setString(2, libro.getLengua());
			stmt.setString(3, libro.getEdicion());
			stmt.setString(4, libro.getFechaEdicion());
			stmt.setString(5, libro.getFechaImpresion());
			stmt.setString(6, libro.getEditorial());*/
			stmt.setInt(2, Integer.valueOf(LibroID));
	 
			int rows = stmt.executeUpdate();
			if (rows == 1)
				libro = getLibro(LibroID);
			else {
				;// Updating inexistent sting
			}
	 
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
	 
		return libro;
		}
	
	private void validateUpdateLibro(Libro libro) {
		if (libro.getTitulo() == null || libro.getTitulo().length() > 100)
			throw new BadRequestException("Titulo can't be null or greater than 100 characters.");
		if (libro.getLengua() == null || libro.getLengua().length() > 100)
			throw new BadRequestException("Lengua can't be null or greater than 100 characters.");
		if (libro.getEdicion() == null || libro.getEdicion().length() > 100)
			throw new BadRequestException("Edicion can't be null or greater than 100 characters.");
		if (libro.getFechaEdicion() == null || libro.getFechaEdicion().length() > 100)
			throw new BadRequestException("FechaEdicion can't be null or greater than 100 characters.");
		if (libro.getFechaImpresion() == null || libro.getFechaImpresion().length() > 100)
			throw new BadRequestException("FechaImpresion can't be null or greater than 100 characters.");
		if (libro.getEditorial() == null || libro.getEditorial().length() > 100)
			throw new BadRequestException("Editorial can't be null or greater than 100 characters.");
			
	}
}
