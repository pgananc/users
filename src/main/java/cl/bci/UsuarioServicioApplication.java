package cl.bci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UsuarioServicioApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServicioApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UsuarioServicioApplication.class);
	}
	
	
}
