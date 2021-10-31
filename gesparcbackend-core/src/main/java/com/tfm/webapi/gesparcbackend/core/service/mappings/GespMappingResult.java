package com.tfm.webapi.gesparcbackend.core.service.mappings;

import java.beans.Transient;

import com.tfm.webapi.gesparcbackend.core.service.api.AbstractGenericResult;
import com.tfm.webapi.gesparcbackend.model.mappings.GespMappingProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class GespMappingResult extends AbstractGenericResult {
	
	private String                value;
	
	private GespMappingProperties mappingProperties;
	
	@Transient
	public void setValueWithMarkers(final String value) {
		setValue("{{" + value + "}}");
	}
}
