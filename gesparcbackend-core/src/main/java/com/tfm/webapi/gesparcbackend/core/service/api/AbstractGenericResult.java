package com.tfm.webapi.gesparcbackend.core.service.api;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class AbstractGenericResult implements GenericResult {

	@Getter
	private final List<String> infos    = new ArrayList<>();
	
	@Getter
	private final List<String> warnings = new ArrayList<>();
	
	@Getter
	private final List<String> errors   = new ArrayList<>();

}
