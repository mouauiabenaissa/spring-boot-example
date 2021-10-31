package com.tfm.webapi.gesparcbackend.model;

import java.beans.Transient;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GenericResponse implements BasicModel {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Status            status;
	
	private Collection<String>      infos;
	
	private Collection<String>      errors;
	
	private Collection<String>      warnings;
	
	public enum Status {
		SUCCESS, FAILURE, PENDING, TIMEOUT;
	}
	
	@Transient
	public boolean isSuccess() {
		return getErrors() == null || getErrors().isEmpty();	
	}
}
