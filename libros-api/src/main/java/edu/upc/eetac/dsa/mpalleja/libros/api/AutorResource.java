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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.mpalleja.libros.api.model.Autor;
import edu.upc.eetac.dsa.mpalleja.libros.api.model.Libro;
import edu.upc.eetac.dsa.mpalleja.libros.api.model.LibrosCollection;

@Path("/autores")
public class AutorResource {
	@Context
	private SecurityContext security;
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	private String GET_AUTOR_BY_ID_QUERY = "SELECT * FROM autores WHERE idautor=?";
	
	public Autor getAutor(@PathParam("idautor") String idautor) {
		Autor autor = new Autor();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_AUTOR_BY_ID_QUERY);
			stmt.setInt(1, Integer.valueOf(idautor));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				autor.setIdautor(rs.getInt("idautor"));
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
	 
		return autor;
	}	
	
	private String INSERT_AUTOR_QUERY = "INSERT INTO autores(nombre) VALUES (?)";
	 
	@POST
	@Consumes(MediaType.LIBROS_API_AUTOR)
	@Produces(MediaType.LIBROS_API_AUTOR)
	public Autor createAutor(Autor autor) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_AUTOR_QUERY,
					Statement.RETURN_GENERATED_KEYS);
	 
			stmt.setString(1, autor.getNombre());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idautor = rs.getInt(1);
	 
				autor = getAutor(Integer.toString(idautor));
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
	 
		return autor;
	}
	
	private void validateAutor(Autor autor) {
		if (autor.getNombre() == null || autor.getNombre().length() > 60)
			throw new BadRequestException("Autor can't be null or greater than 60 characters.");

	}
	
	private String DELETE_AUTOR_QUERY = "DELETE FROM autores WHERE idautor=?";
	 
	@DELETE
	@Path("/{idautor}")
	public void deleteAutor(@PathParam("idautor") String idautor) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_AUTOR_QUERY);
			stmt.setInt(1, Integer.valueOf(idautor));
	 
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
	
	private String UPDATE_AUTOR_QUERY = "UPDATE autores SET nombre=ifnull(?, nombre) WHERE idautor=?";
	 
	@PUT
	@Path("/{idautor}")
	@Consumes(MediaType.LIBROS_API_AUTOR)@Produces(MediaType.LIBROS_API_AUTOR)
	public Autor updateAutor(@PathParam("idautor") String idautor, Autor autor) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_AUTOR_QUERY);
			stmt.setString(1, autor.getNombre());
			/*stmt.setString(2, libro.getLengua());
			stmt.setString(3, libro.getEdicion());
			stmt.setString(4, libro.getFechaEdicion());
			stmt.setString(5, libro.getFechaImpresion());
			stmt.setString(6, libro.getEditorial());*/
			stmt.setInt(2, Integer.valueOf(idautor));
	 
			int rows = stmt.executeUpdate();
			if (rows == 1)
				autor = getAutor(idautor);
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
	 
		return autor;
		}

	public void validateUpdateAutor (Autor autor){
		if (autor.getNombre().length() > 60)
			throw new BadRequestException("Autor name can't be greater that 60 characters");
	}
	
}
