package com.tasinirdepo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.model.IslemCesidi;
import com.tasinirdepo.service.IBaseService;

@RestController
@RequestMapping("/rest")
public class IslemCesidiRestController {

	private IBaseService<IslemCesidi> service;

	@Autowired
	@Qualifier("islemCesidiService")
	public void setService(IBaseService<IslemCesidi> service) {
		this.service = service;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "islemCesidi/getAll")
	@KimlikDogrulama
	public ResponseEntity<List<IslemCesidi>> getIslemCesidi(@RequestHeader("token") String token) {
		try {
			List<IslemCesidi> data = service.findAll();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
