package com.tfm.webapi.gesparcbackend.model.mappings;

import javax.validation.constraints.NotBlank;

import com.tfm.webapi.gesparcbackend.model.BasicModel;

import lombok.Data;

@Data
public class GespMappingProperties implements BasicModel {
	
	private static final long     serialVersionUID = 1L;
	
	/**
	 * Nom de la propriété telle qu'elle apparait dans les traceurs
	 */
	@NotBlank
	private String name;
	
	/**
	 * La clé dans le fichier de conf de l'application qui contient la valeur à utiliser
	 */
	@NotBlank
	private String               staticSetting;

}
