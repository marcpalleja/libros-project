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

import edu.upc.eetac.dsa.mpalleja.libros.api.model.Libro;
import edu.upc.eetac.dsa.mpalleja.libros.api.model.LibrosCollection;
import edu.upc.eetac.dsa.mpalleja.libros.api.model.Review;
import edu.upc.eetac.dsa.mpalleja.libros.api.model.ReviewsCollection;

	@Path("/reviews")
	public class ReviewResource {
		@Context
		private SecurityContext security;
		
		private DataSource ds = DataSourceSPA.getInstance().getDataSource();
		private String GET_REVIEWS_QUERY = "SELECT * FROM Reviews";
		 
		@GET
		@Produces(MediaType.REVIEWS_API_REVIEW_COLLECTION)
		public ReviewsCollection getReviews() {
			ReviewsCollection reviews = new ReviewsCollection();
		 
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}
		 
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(GET_REVIEWS_QUERY);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Review review = new Review();
					review.setReviewID(rs.getInt("ReviewID"));
					review.setUsername(rs.getString("Username"));
					review.setNombre(rs.getString("Nombre"));
					review.setIDLibro(rs.getInt("IDLibro"));
					review.setContenido(rs.getString("Contenido"));
					reviews.addReview(review);
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
		 
			return reviews;
		}
		
		private String GET_REVIEW_BY_ID_QUERY = "SELECT * FROM Reviews WHERE ReviewID=?";
		 
		
		public Review getReview( String ReviewID) {
			Review review = new Review();
		 
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}
		 
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(GET_REVIEW_BY_ID_QUERY);
				stmt.setInt(1, Integer.valueOf(ReviewID));
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					review.setReviewID(rs.getInt("ReviewID"));
					review.setUsername(rs.getString("Username"));
					review.setNombre(rs.getString("Nombre"));
					review.setIDLibro(rs.getInt("IDLibro"));
					review.setContenido(rs.getString("Contenido"));
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
		 
			return review;
		}	
		
		private String INSERT_REVIEW_QUERY = "INSERT INTO Reviews(Username, Nombre, IDLibro, Contenido) VALUES (?, ?, ?, ?)";
		 
		@POST
		@Consumes(MediaType.REVIEWS_API_REVIEW)
		@Produces(MediaType.REVIEWS_API_REVIEW)
		public Review createReview(Review review) {
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}
		 
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(INSERT_REVIEW_QUERY,
						Statement.RETURN_GENERATED_KEYS);
		 
				stmt.setString(1, review.getUsername());
				stmt.setString(2, review.getNombre());
				stmt.setInt(3, review.getIDLibro());
				stmt.setString(4, review.getContenido());
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int ReviewID = rs.getInt(1);
		 
					review = getReview(Integer.toString(ReviewID));
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
		 
			return review;
		}
		
		private void validateUserPermision(String username) {
			if (!security.getUserPrincipal().getName().equals(username))
				throw new ForbiddenException("You are not allowed to post this review.");
		}
		
		
		private void validateReview(Review review) {
			if (review.getUsername() == null || review.getUsername().length() > 20)
				throw new BadRequestException("Username can't be null.");
			if (review.getNombre() == null || review.getNombre().length() > 70)
				throw new BadRequestException("Nombre can't be null.");
			if (review.getContenido() == null || review.getContenido().length() > 500)
				throw new BadRequestException("Contenido can't be greater than 500 characters.");
		}
		
		private void validateUser(int reviewid) {
			Review review = getReview(Integer.toString(reviewid));
			String username = review.getUsername();
			if (!security.getUserPrincipal().getName().equals(username))
				throw new ForbiddenException("You are not allowed to post this review.");
		}
		
		private String DELETE_REVIEW_QUERY = "delete from Reviews where ReviewID = ?";
		 
		@DELETE
		@Path("/{ReviewID}")
		public void deleteReview(@PathParam("ReviewID") int reviewid) {
			
			validateUser(reviewid);
			
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}
		 
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(DELETE_REVIEW_QUERY);
				stmt.setInt(1, reviewid);
		 
				int rows = stmt.executeUpdate();
				if (rows == 0)
					throw new NotFoundException("There's no review with reviewid = "+ reviewid);
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
		
		private String UPDATE_REVIEW_QUERY = "UPDATE Reviews SET Username=ifnull(?, Username) WHERE ReviewID=?";
		 
		@PUT
		@Path("/{ReviewID}")
		@Consumes(MediaType.REVIEWS_API_REVIEW)@Produces(MediaType.REVIEWS_API_REVIEW)
		public Review updateReview(@PathParam("ReviewID") String ReviewID, Review review) {
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}
		 
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(UPDATE_REVIEW_QUERY);
				stmt.setString(1, review.getUsername());
				/*stmt.setString(2, libro.getLengua());
				stmt.setString(3, libro.getEdicion());
				stmt.setString(4, libro.getFechaEdicion());
				stmt.setString(5, libro.getFechaImpresion());
				stmt.setString(6, libro.getEditorial());*/
				stmt.setInt(2, Integer.valueOf(ReviewID));
		 
				int rows = stmt.executeUpdate();
				if (rows == 1)
					review = getReview(ReviewID);
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
		 
			return review;
			}
			
	}



