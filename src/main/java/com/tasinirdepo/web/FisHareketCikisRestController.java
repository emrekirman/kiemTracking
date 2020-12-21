package com.tasinirdepo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.service.IBaseService;
import com.tasinirdepo.service.IFisHareketCikisService;

@RestController
@RequestMapping("/rest")
public class FisHareketCikisRestController {

	@Autowired
	private IFisHareketCikisService service;

	private IBaseService<FisHareketCikis> service2;

	private ILogginManager logRepo;

	public FisHareketCikisRestController(@Qualifier("fisHareketCikisService") IBaseService<FisHareketCikis> service2,
			@Qualifier("logRepo") ILogginManager logRepo) {
		this.service2 = service2;
		this.logRepo = logRepo;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "fisHareketCikis/createAll")
	@KimlikDogrulama
	public ResponseEntity<Boolean> createAll(@RequestHeader("token") String token,
			@RequestBody List<FisHareketCikis> cikisList) {
		try {
			service.createAll(cikisList);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "fisHareketCikis/update")
	@KimlikDogrulama
	public ResponseEntity<DepoFis> update(@RequestHeader("token") String token, @RequestBody FisHareketCikis model) {
		try {
			FisHareketCikis data = service2.update(model);
			DepoFis dataFis = data.getDepoFis();
			dataFis.getCikisList().forEach(x -> {
				x.setDepoFis(null);
			});
			return ResponseEntity.ok(dataFis);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("fisHareketCikis/delete/{id}")
	@KimlikDogrulama
	public ResponseEntity<Boolean> delete(@RequestHeader("token") String token, @PathVariable int id) {
		try {
			service2.delete(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "fisHareketCikis/create")
	@KimlikDogrulama
	public ResponseEntity<Integer> create(@RequestHeader("token") String token, @RequestBody FisHareketCikis model) {
		try {
			List<FisHareketCikis> list = new ArrayList<FisHareketCikis>();
			list.add(model);
			for (FisHareketCikis fisHareketCikis : list) {
				service.cikisIslemi(fisHareketCikis, model.getDepoFis());
			}
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
