package cl.bci.dto;

import java.io.Serializable;
import java.util.List;

import cl.bci.model.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Información del usuario.")
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9105655543151392862L;

	@ApiModelProperty(notes = "Nombre debe tener entre 2 y 100 caracteres.")
	private String name;

	@ApiModelProperty(notes = "Email de usuario.")
	private String email;

	@ApiModelProperty(notes = "Password de usuario.")
	private String password;

	@ApiModelProperty(notes = "Telélefono de usuario.")
	private List<Phone> phones;
}
