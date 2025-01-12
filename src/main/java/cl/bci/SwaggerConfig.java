package cl.bci;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("BCI", "https://www.bci.cl", "info@bci.cl");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Back Api Documentation", "Back Api Documentation",
			"1.0", "PREMIUM", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			Collections.emptyList());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("cl.bci.controller")).paths(PathSelectors.ant("/users/**"))
				.build().apiInfo(DEFAULT_API_INFO).useDefaultResponseMessages(false);
	}

}
