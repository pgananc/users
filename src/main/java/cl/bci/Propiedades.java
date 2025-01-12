package cl.bci;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "password")
public class Propiedades {
	
	private String patternPassword;
	
	public String getPatternPassword() {
		return patternPassword;
	}
	public void setPatternPassword(String patternPassword) {
		this.patternPassword = patternPassword;
	}
}
