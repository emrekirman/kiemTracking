package com.tasinirdepo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.model.Yetki;
import com.tasinirdepo.service.IYetkiService;

@RestController
@RequestMapping("/rest")
public class YetkiRestController {

	private IYetkiService service;

	@Autowired
	public void setService(IYetkiService service) {
		this.service = service;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/yetki/getAll")
	@KimlikDogrulama
	public ResponseEntity<List<Yetki>> getYetkis(@RequestHeader("token") String token) {
		try {
			List<Yetki> data = service.findAll();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
