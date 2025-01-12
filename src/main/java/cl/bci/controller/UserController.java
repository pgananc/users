package cl.bci.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.dto.UserDTO;
import cl.bci.model.User;
import cl.bci.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(value = "User Management", tags = "User Controller", description = "APIs para gestionar usuarios.")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * Endpoint para registrar un nuevo usuario.
	 *
	 * @param userDTO Datos del usuario en formato JSON.
	 * @return Usuario registrado con código de respuesta HTTP 201 (CREATED).
	 */
	@ApiOperation(value = "Registrar un nuevo usuario", notes = "Este endpoint permite crear un nuevo usuario con sus datos.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario registrado exitosamente."),
			@ApiResponse(code = 400, message = "Error en la solicitud. Validaciones fallidas."),
			@ApiResponse(code = 500, message = "Error interno del servidor.") })
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> register(HttpServletRequest request, @Valid @RequestBody UserDTO userDTO) {
		User savedUser = null;
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			savedUser = userService.saveUser(userDTO, token);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(savedUser, HttpStatus.UNAUTHORIZED);

	}

}
