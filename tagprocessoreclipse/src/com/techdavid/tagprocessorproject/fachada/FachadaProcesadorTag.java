package com.techdavid.tagprocessorproject.fachada;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techdavid.tagprocessorproject.controlador.ControladorTag;

@RestController
@RequestMapping("/api")
public class FachadaProcesadorTag {
	@RequestMapping(method = RequestMethod.GET, value = "/procesartag",
			produces = "application/json")
	public ResponseEntity<String> procesarTag(String datosTag) {
		ControladorTag controladorTag =
				ControladorTag.getControladorTag();
		String informacionTags = controladorTag.procesarTag(datosTag);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Access-Control-Allow-Origin", "*");
		ResponseEntity<String> responseEntity =
				new ResponseEntity<String>(informacionTags, responseHeaders, HttpStatus.ACCEPTED);
		return responseEntity;
	}
}

