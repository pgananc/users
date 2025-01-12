package cl.bci.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cl.bci.dto.UserDTO;
import cl.bci.exception.ModelNotFoundException;
import cl.bci.model.Phone;
import cl.bci.model.User;
import cl.bci.repository.IUserRepo;

@ExtendWith(SpringExtension.class) // Extiende para pruebas con Spring
@SpringBootTest
@TestPropertySource(properties = "password.pattern=^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") // Simulamos la propiedad
																								// password.pattern

class UserServiceImplTest {

	@Mock
	private IUserRepo userRepo; // Mock del repositorio de usuarios

	@InjectMocks
	private UserServiceImpl userService; // Servicio a probar

	@Value("${password.pattern}")
	private String passwordPattern;

	@BeforeEach
	public void setUp() {
		// Inicializar mocks antes de cada prueba
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testPasswordPatternInjection() {
		// Verificar que la propiedad se inyectó correctamente
		assertNotNull(passwordPattern);
	}

	@Test
	public void testSaveUserEmailAlreadyExists() {
		// Preparar datos de entrada
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("mito@gmail.com");
		userDTO.setName("Mito");
		userDTO.setPassword("1234abcD"); // Debe cumplir con el patrón
		userDTO.setPhones(Arrays.asList(new Phone()));

		// Simulamos que el correo ya está registrado
		when(userRepo.findOneByEmail(userDTO.getEmail())).thenReturn(new User());

		// Ejecutar y verificar que se lanza la excepción correcta
		ModelNotFoundException thrown = assertThrows(ModelNotFoundException.class, () -> {
			userService.saveUser(userDTO, "sampleToken");
		});

		assertEquals("El correo ya registrado.", thrown.getMessage());
	}

}
