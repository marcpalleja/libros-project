package edu.upc.eetac.dsa.mpalleja.libros.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;

public class LibrosApplication extends ResourceConfig {
	public LibrosApplication() {
		super();
		register(DeclarativeLinkingFeature.class);
	}
}
