package com.bank.microservice.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El genero es obligatorio")
    private String genero;

    @NotNull(message = "La edad es obligatoria")
    @Min(value =18, message = "La edad minima permitida es 18 años")
    @Max(value =100, message = "La edad máxima permitida es 100 años")
    private Integer edad;

    @Pattern(regexp = "^\\d{10}$", message = "El numero de identificacion debe tener 10 digitos")
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Pattern(regexp = "^\\d{10}$", message = "El numero de telefono debe tener 10 digitos")
    private String telefono;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=8, max=12, message = "La contraseña debe tener entre 8 y 12 caracteres")
    private String password;

    private boolean estado;
}
