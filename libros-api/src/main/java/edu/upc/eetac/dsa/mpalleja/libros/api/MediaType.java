package edu.upc.eetac.dsa.mpalleja.libros.api;

public interface MediaType {

	public final static String LIBROS_API_USER = "application/vnd.libros.api.user+json";
	public final static String LIBROS_API_USER_COLLECTION = "application/vnd.libros.api.user.collection+json";
	public final static String LIBROS_API_LIBRO = "application/vnd.libros.api.libro+json";
	public final static String LIBROS_API_LIBRO_COLLECTION = "application/vnd.libros.api.libro.collection+json";
	public final static String LIBROS_API_ERROR = "application/vnd.dsa.libros.error+json";
	public final static String LIBROS_API_AUTOR = "application/vnd.libros.api.autor+json";
	public final static String REVIEWS_API_REVIEW = "application/vnd.reviews.api.review+json";
	public final static String REVIEWS_API_REVIEW_COLLECTION = "application/vnd.reviews.api.review.collection+json";
	
}
