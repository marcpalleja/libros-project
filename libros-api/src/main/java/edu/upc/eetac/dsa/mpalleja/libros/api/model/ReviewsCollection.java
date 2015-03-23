package edu.upc.eetac.dsa.mpalleja.libros.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;


public class ReviewsCollection {
	
	private List<Review> reviews;
	private long newestTimestamp;
	private long oldestTimestamp;
 
	public ReviewsCollection() {
		super();
		reviews = new ArrayList<>();
	}
 
	public List <Review> getReviews() {
		return reviews;
	}
 
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
 
	public void addReview(Review review) {
		reviews.add(review);
	}
 
 	public long getNewestTimestamp() {
		return newestTimestamp;
	}
 
	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}
 
	public long getOldestTimestamp() {
		return oldestTimestamp;
	}
 
	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}
}

