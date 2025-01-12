package cl.bci.service;

import cl.bci.dto.UserDTO;
import cl.bci.model.User;

public interface IUserService {

	User saveUser(UserDTO userDTO, String token);

	User findByEmail(String email);
}
