package cl.bci.service.impl;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.bci.dto.UserDTO;
import cl.bci.exception.ModelNotFoundException;
import cl.bci.model.User;
import cl.bci.repository.IUserRepo;
import cl.bci.service.IUserService;
import cl.bci.util.PasswordValidator;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepo userRepo;

	@Value("${password.pattern}")
	private String patternPassword;

	/**
	 * Metodo para guardar usuario.
	 */
	@Override
	public User saveUser(UserDTO userDTO, String token) {
		User user = generarUsuario(userDTO);
		if (validateEmail(userDTO.getEmail())) {
			throw new ModelNotFoundException("El correo ya registrado.");
		}
		if (!validatePassword(user.getPassword())) {
			throw new ModelNotFoundException("El password no conicide con el patron.");
		}
		if (Objects.isNull(userDTO.getPhones()) || userDTO.getPhones().isEmpty()) {
			throw new ModelNotFoundException("Ingrese al menos un telefono");
		}
		if (user.getPhones() != null) {
			user.getPhones().forEach(phone -> phone.setUser(user));
		}
		user.setToken(token);
		return userRepo.save(user);
	}

	/**
	 * Método para buscar usuario por mail.
	 */
	@Override
	public User findByEmail(String email) {
		return userRepo.findOneByEmail(email);

	}

	/**
	 * Método para validar email.
	 * 
	 * @param email Email
	 * @return Estado existe.
	 */
	private boolean validateEmail(String email) {
		User user = findByEmail(email);
		return Objects.nonNull(user);
	}

	/**
	 * Método para validar password.
	 * 
	 * @param password password.
	 * @return Estado de validación.
	 */
	private boolean validatePassword(String password) {
		return PasswordValidator.isValid(password, patternPassword);
	}

	/**
	 * Método para tranformar userDto a user.
	 * 
	 * @param userDTO Usuario dto.
	 * @return User.
	 */
	private User generarUsuario(UserDTO userDTO) {
		User user = new User();
		user.setPhones(new ArrayList<>());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setPhones(userDTO.getPhones());
		return user;
	}

}
