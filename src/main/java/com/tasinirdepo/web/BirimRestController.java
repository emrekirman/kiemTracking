package com.tasinirdepo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.logging.LoggingManager;
import com.tasinirdepo.model.Birim;
import com.tasinirdepo.service.IBirimService;

@RestController
@RequestMapping("/rest")
public class BirimRestController {

	private IBirimService birimService;

	@Autowired
	public void setBirimService(IBirimService birimService) {
		this.birimService = birimService;
	}

	@Autowired
	private LoggingManager logRepo;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/birim/getAll")
	@KimlikDogrulama
	public ResponseEntity<List<Birim>> getAll(@RequestHeader("token") String token) {
		try {
			List<Birim> data = birimService.findAll();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/birim/delete/{id}")
	@KimlikDogrulama
	public ResponseEntity<Boolean> delete(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			birimService.delete(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "/birim/update")
	@KimlikDogrulama
	public ResponseEntity<Boolean> update(@RequestHeader("token") String token, @RequestBody Birim model) {
		try {
			birimService.update(model);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "/birim/ekle")
	@KimlikDogrulama
	public ResponseEntity<Boolean> ekle(@RequestHeader("token") String token, @RequestBody Birim model) {
		try {
			birimService.create(model);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/birim/getById/{id}")
	@KimlikDogrulama
	public ResponseEntity<Birim> getById(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			Birim model = birimService.getById(id);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
