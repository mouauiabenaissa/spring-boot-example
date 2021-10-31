package com.tfm.webapi.gesparcbackend.core.service.mappings.impl;

import com.tfm.webapi.gesparcbackend.core.service.api.AbstractGenericResult;
import com.tfm.webapi.gesparcbackend.core.service.mappings.GespMappingPropertiesAnnotation;
import com.tfm.webapi.gesparcbackend.core.service.mappings.GespMappingRule;
import com.tfm.webapi.gesparcbackend.model.mappings.GespMappingProperties;

abstract class AbstractGespMappingRule extends AbstractGenericResult implements GespMappingRule {
	
	/**
	 * @return L'objet {@link GespMappingProperties} associée à cette classe annotée par
	 *         {@link GespMappingPropertiesAnnotation}.
	 */
	protected GespMappingProperties getGespMappingPropertiesFromAnnotation() {
		final Class<? extends AbstractGespMappingRule> cl = getClass();
		
		if (cl.isAnnotationPresent(GespMappingPropertiesAnnotation.class)) {
			
			final GespMappingPropertiesAnnotation annotation = cl.getAnnotation(GespMappingPropertiesAnnotation.class);
			
			final GespMappingProperties gespMappingProperties = new GespMappingProperties();
			gespMappingProperties.setName(annotation.name());
			
			return gespMappingProperties; 
		} else {
			throw new IllegalStateException("Cette méthode ne doit etre appelée que si la classe est annoté par : " + GespMappingPropertiesAnnotation.class);
		}
	}
	

}
