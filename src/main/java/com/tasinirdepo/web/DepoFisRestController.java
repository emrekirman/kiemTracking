package com.tasinirdepo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasinirdepo.dto.FaturaListeVM;
import com.tasinirdepo.dto.StokMevcuduDto;
import com.tasinirdepo.interfaces.IExcelExportReport;
import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.logging.LoggingManager;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.service.IDepoFisService;
import com.tasinirdepo.service.IFisHareketCikisService;

@RestController
@RequestMapping("/rest")
public class DepoFisRestController {

	@Autowired
	private IDepoFisService depoFisService;

	@Autowired
	private LoggingManager logRepo;

	@Autowired
	private IFisHareketCikisService service;

	private IExcelExportReport<StokMevcuduDto> excelHelper;

	@Autowired
	public DepoFisRestController(IExcelExportReport<StokMevcuduDto> excelHelper) {
		this.excelHelper = excelHelper;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "depoFis/create")
	@KimlikDogrulama
	public ResponseEntity<DepoFis> createDepoFis(@RequestHeader("token") String token, @RequestBody DepoFis depoFis) {
		try {
			int fisId = depoFisService.create(depoFis);
			return ResponseEntity.ok(depoFisService.getById(fisId));
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "depoFis/createCikisFis")
	@KimlikDogrulama
	public ResponseEntity<Integer> createCikisFis(@RequestHeader("token") String token, @RequestBody DepoFis depoFis) {
		try {
			List<FisHareketCikis> cikisList = depoFis.getCikisList();
			depoFis.setCikisList(null);
			depoFisService.create(depoFis);
			for (FisHareketCikis fisHareketCikis : cikisList) {
				service.cikisIslemi(fisHareketCikis, depoFis);
			}
			return ResponseEntity.ok(depoFis.getId());

		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "depoFis/update")
	@KimlikDogrulama
	public ResponseEntity<DepoFis> update(@RequestHeader("token") String token, @RequestBody DepoFis model) {
		try {
			DepoFis data = depoFisService.update(model);
			data.getGirisList().forEach(x -> {
				x.setDepoFis(null);
			});
			data.getCikisList().forEach(x -> {
				x.setDepoFis(null);
				x.setFisHareketGiris(null);
			});
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoFis/getAll/{giris}")
	@KimlikDogrulama
	public ResponseEntity<List<FaturaListeVM>> getAll(@RequestHeader("token") String token,
			@PathVariable("giris") boolean giris) {
		try {
			List<DepoFis> data1 = depoFisService.findAll(giris);
			List<FaturaListeVM> data = depoFisService.createFaturaListeVm(data1);
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoFis/getById/{id}")
	@KimlikDogrulama
	public ResponseEntity<DepoFis> getAll(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			DepoFis data1 = depoFisService.getById(id);
			data1.getGirisList().forEach(x -> {
				x.setDepoFis(null);
				x.setCikisList(null);
				x.getStokTanim().setGirisler(null);
				/*
				 * Burada depo fişi boşalttık fakat ui kısmına girişlerin depo fişi boş gidiyor.
				 * Bu durum silme veya güncelleme işlemi yaparken foreign key hatası verebilir.
				 * Bunun için angular tarafında servise giriş gönderileceği zaman depo fişine
				 * hali hazırdaki fiş set edilmeli.
				 */
			});
			data1.getCikisList().forEach(x -> {
				x.setDepoFis(null);
				x.setFisHareketGiris(null);
			});
			return ResponseEntity.ok(data1);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoFis/getFisNo")
	@KimlikDogrulama
	public ResponseEntity<Integer> getFisNo(@RequestHeader("token") String token) {
		try {
			int data = depoFisService.getNewFisNo();
			return ResponseEntity.ok(data);

		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoFis/getStokMevcudu")
	@KimlikDogrulama
	public ResponseEntity<List<StokMevcuduDto>> getStokMevcudu(@RequestHeader("token") String token) {
		try {
			List<StokMevcuduDto> data = depoFisService.stokMevcudu();
			return ResponseEntity.ok(data);

		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoFis/download")
	@KimlikDogrulama
	public ResponseEntity<Resource> getFile(@RequestHeader("token") String token) {
		try {
			String fileName = "stokMevcudu.xls";
			List<StokMevcuduDto> data = depoFisService.stokMevcudu();
			InputStreamResource file = new InputStreamResource(excelHelper.toExcel(data));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
