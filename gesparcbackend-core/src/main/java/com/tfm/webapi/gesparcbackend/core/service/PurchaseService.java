package com.tfm.webapi.gesparcbackend.core.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class PurchaseService implements GespBackendService {
	
	@Getter
	private List<String> serviceInfos    = new ArrayList<>();
	
	@Getter
	private List<String> serviceWarnings = new ArrayList<>();
	
	@Getter
	private List<String> serviceErrors   = new ArrayList<>();
	
	

}
