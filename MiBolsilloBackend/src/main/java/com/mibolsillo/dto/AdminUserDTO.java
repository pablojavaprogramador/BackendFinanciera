package com.mibolsillo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mibolsillo.model.Authority;
import com.mibolsillo.model.User;
import com.mibolsillo.utils.Constants;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.*;

/**
 * A DTO representing a user, with his authorities.
 */
public class AdminUserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    

    private Long claveunica;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String fechaNacimiento;

    private String identificacionOCR;

    private String claveElector;

    private String telefono;

    private String celular;
  
    private String correoElectronico;
    
    

    private Set<String> authorities;

    public AdminUserDTO() {
        // Empty constructor needed for Jackson.
    }

    public AdminUserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.isActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
//        this.createdBy = user.getCreatedBy();
//        this.createdDate = user.getCreatedDate();
//        this.lastModifiedBy = user.getLastModifiedBy();
//        this.lastModifiedDate = user.getLastModifiedDate();
        this.claveunica = user.getClaveunica();
        this.nombre = user.getNombre();
        this.apellidoPaterno= user.getApellidoPaterno();
        this.apellidoMaterno= user.getApellidoMaterno();
        this.fechaNacimiento= user.getFechaNacimiento();
        this.identificacionOCR= user.getIdentificacionOCR();
        this.claveElector= user.getClaveElector();
        this.telefono= user.getTelefono();
        this.celular= user.getCelular();
        this.correoElectronico= user.getCelular();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

	@Override
	public String toString() {
		return "AdminUserDTO [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", imageUrl=" + imageUrl + ", activated=" + activated + ", langKey=" + langKey
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", claveunica=" + claveunica + ", nombre=" + nombre
				+ ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento="
				+ fechaNacimiento + ", identificacionOCR=" + identificacionOCR + ", claveElector=" + claveElector
				+ ", telefono=" + telefono + ", celular=" + celular + ", correoElectronico=" + correoElectronico
				+ ", authorities=" + authorities + "]";
	}


}
