package com.tasinirdepo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tasinirdepo.model.FisHareketGiris;
import com.tasinirdepo.service.IDepoFisService;
import com.tasinirdepo.service.IFisHareketGirisService;

@RestController
@RequestMapping("/rest")
public class FisHareketGirisRestController {

	private IFisHareketGirisService fisHareketGirisService;

	private IDepoFisService fisService;

	private ILogginManager logRepo;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "fisHareketGiris/createAll")
	@KimlikDogrulama
	public ResponseEntity<Boolean> createAll(@RequestHeader("token") String token,
			@RequestBody List<FisHareketGiris> girisList) {
		try {
			fisHareketGirisService.createAll(girisList);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "fisHareketGiris/update")
	@KimlikDogrulama
	public ResponseEntity<DepoFis> update(@RequestHeader("token") String token, @RequestBody FisHareketGiris model) {
		try {
			fisHareketGirisService.update(model);
			DepoFis data = fisService.getById(model.getDepoFis().getId());
			data.getGirisList().forEach(x -> {
				x.setDepoFis(null);
			});
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("fisHareketGiris/delete/{id}")
	@KimlikDogrulama
	public ResponseEntity<Boolean> delete(@RequestHeader("token") String token, @PathVariable int id) {
		try {
			fisHareketGirisService.delete(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "fisHareketGiris/create")
	@KimlikDogrulama
	public ResponseEntity<Integer> create(@RequestHeader("token") String token, @RequestBody FisHareketGiris model) {
		try {
			int res = fisHareketGirisService.create(model);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Autowired
	public void setFisHareketGirisRepository(IFisHareketGirisService fisHareketGirisRepository) {
		this.fisHareketGirisService = fisHareketGirisRepository;
	}

	@Autowired
	public void setFisService(IDepoFisService fisService) {
		this.fisService = fisService;
	}

	@Autowired
	public void setLogRepo(ILogginManager logRepo) {
		this.logRepo = logRepo;
	}
}
