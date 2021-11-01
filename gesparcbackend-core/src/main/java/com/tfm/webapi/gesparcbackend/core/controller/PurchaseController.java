package com.tfm.webapi.gesparcbackend.core.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.webapi.gesparcbackend.core.service.PurchaseService;
import com.tfm.webapi.gesparcbackend.model.BasicModel;
import com.tfm.webapi.gesparcbackend.model.GenericResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@ResponseBody
	@PostMapping(path = "/purchase")
	public GenericResponse purchase(final @Valid @RequestBody BasicModel params, final HttpServletResponse response) {
		
		log.info("Runing 'purchase'");
		
		return null;
	}

}
