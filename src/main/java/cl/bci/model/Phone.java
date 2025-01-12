package cl.bci.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Entidad para representar la tabla phone.
 * 
 * Representa la información de un teléfono asociada a un usuario.
 * Incluye validaciones de datos y mapeo JPA.
 * 
 * @author PABI1
 */
@Data
@Entity
@Table(name = "phone")
@ApiModel(description = "Información del teléfono.")
public class Phone {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ApiModelProperty(notes = "Número de teléfono del usuario.")
    @NotBlank(message = "El número de teléfono no debe estar vacío.")
    @Pattern(
        regexp = "^[0-9]{7,15}$",
        message = "El número de teléfono debe contener solo dígitos y tener entre 7 y 15 caracteres."
    )
    @Column(name = "number", nullable = false)
    private String number;

    @ApiModelProperty(notes = "Código del país.")
    @NotBlank(message = "El código de país no debe estar vacío.")
    @Pattern(
        regexp = "^[0-9]{1,5}$",
        message = "El código de país debe contener solo dígitos y tener entre 1 y 5 caracteres."
    )
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @ApiModelProperty(notes = "Código de la ciudad.")
    @NotBlank(message = "El código de ciudad no debe estar vacío.")
    @Pattern(
        regexp = "^[0-9]{1,5}$",
        message = "El código de ciudad debe contener solo dígitos y tener entre 1 y 5 caracteres."
    )
    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
