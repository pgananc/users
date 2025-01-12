package cl.bci.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Entidad que representa la tabla user.
 * 
 * @author PABI1
 */
@Data
@Entity
@Table(name = "user")
@ApiModel(description = "Información del usuario.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @ApiModelProperty(notes = "Nombre del usuario, debe tener entre 2 y 100 caracteres.")
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(notes = "Correo electrónico único del usuario.")
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(
        regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", 
        message = "El correo electrónico no tiene un formato válido."
    )
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ApiModelProperty(notes = "Contraseña del usuario. Debe tener al menos 8 caracteres, una letra mayúscula, una minúscula, un número y un carácter especial.")
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "La contraseña debe tener al menos 8 caracteres, incluir una letra mayúscula, una minúscula, un número y un carácter especial."
    )
    @Column(name = "password", nullable = false)
    private String password;

    @ApiModelProperty(notes = "Fecha de creación del usuario.")
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @ApiModelProperty(notes = "Fecha de la última actualización del usuario.")
    @Column(name = "modified")
    private LocalDateTime modified;

    @ApiModelProperty(notes = "Fecha del último ingreso del usuario.")
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ApiModelProperty(notes = "Token de acceso de la API.")
    @Column(name = "token", nullable = true)
    private String token;

    @ApiModelProperty(notes = "Indica si el usuario está activo en el sistema.")
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ApiModelProperty(notes = "Listado de teléfonos asociados al usuario.")
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Phone> phones;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
        lastLogin = created;
    }

    @PreUpdate
    public void preUpdate() {
        modified = LocalDateTime.now();
    }
}
