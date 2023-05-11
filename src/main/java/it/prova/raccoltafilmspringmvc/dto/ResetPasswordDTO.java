package it.prova.raccoltafilmspringmvc.dto;


import javax.validation.constraints.NotBlank;

import it.prova.raccoltafilmspringmvc.validation.ValidationWithPassword;

public class ResetPasswordDTO {

	@NotBlank(message = "{password.notblank}", groups = ValidationWithPassword.class)
	private String vecchiaPassword;

	private String confermaPassword;

	@NotBlank(message = "{password.notblank}", groups = ValidationWithPassword.class)
	private String nuovaPassword;

	
	
	
	public ResetPasswordDTO() {
		super();
	}
	
	public ResetPasswordDTO(String vecchiaPassword) {
		super();
	}

	public String getVecchiaPassword() {
		return vecchiaPassword;
	}

	public void setVecchiaPassword(String vecchiaPassword) {
		this.vecchiaPassword = vecchiaPassword;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	public String getNuovaPassword() {
		return nuovaPassword;
	}

	public void setNuovaPassword(String nuovaPassword) {
		this.nuovaPassword = nuovaPassword;
	}

	
	
}
