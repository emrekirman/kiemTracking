package com.tasinirdepo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.tasinirdepo.dto.FisHareketDto;
import com.tasinirdepo.interfaces.IExcelExportReport;
import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.model.StokTanim;
import com.tasinirdepo.service.StokTanimService;

@RestController
@RequestMapping("/rest")
public class StokTanimRestController {

	@Autowired
	private StokTanimService service;

	private ILogginManager loggingManager;

	private IExcelExportReport<FisHareketDto> excelHelper;

	@Autowired
	public StokTanimRestController(@Qualifier("girisCikislarReport") IExcelExportReport<FisHareketDto> excelHelper) {
		this.excelHelper = excelHelper;
	}

	@Autowired
	public void setLoggingManager(ILogginManager loggingManager) {
		this.loggingManager = loggingManager;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/stokTanim/getStokTanims")
	@KimlikDogrulama
	public ResponseEntity<List<StokTanim>> getStokTanims(@RequestHeader("token") String token) {
		try {
			List<StokTanim> data = service.findAll();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/stokTanim/depoKod")
	@KimlikDogrulama
	public ResponseEntity<String> getLastDepoKod(@RequestHeader("token") String token) {
		try {
			String data = service.getLastDepoKod();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200", methods = RequestMethod.POST, allowedHeaders = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/stokTanim/ekle")
	@KimlikDogrulama
	public ResponseEntity<Boolean> createStokTanim(@RequestHeader("token") String token, @RequestBody StokTanim model) {
		// Stok Tanım Ekleme
		try {
			service.create(model);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/stokTanim/sil/{id}")
	@KimlikDogrulama
	public ResponseEntity<Boolean> delete(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			service.delete(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/stokTanim/getById/{id}")
	@KimlikDogrulama
	public ResponseEntity<StokTanim> getById(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			StokTanim model = service.getById(id);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "/stokTanim/update")
	@KimlikDogrulama
	public ResponseEntity<Boolean> update(@RequestHeader("token") String token, @RequestBody StokTanim model) {
		try {
			service.update(model);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/stokTanim/getStokMiktar/{id}")
	@KimlikDogrulama
	public ResponseEntity<Double> getStokMiktar(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			double miktar = service.urunStokMiktariGetir(id);
			return ResponseEntity.ok(miktar);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/stokTanim/getGirisCikislar/{id}")
	@KimlikDogrulama
	public ResponseEntity<List<FisHareketDto>> getGirisCikislar(@RequestHeader("token") String token,
			@PathVariable("id") int id, @RequestHeader("baslangic") String baslangic,
			@RequestHeader("bitis") String bitis) {
		try {
			List<FisHareketDto> data = service.urunGirisCikislariGetir(id);
			data = service.getFisHareketByTarih(data, baslangic, bitis);
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "/stokTanim/getGirisCikisRapor")
	@KimlikDogrulama
	public ResponseEntity<Resource> getGirisCikisReport(@RequestHeader("token") String token,
			@RequestBody List<FisHareketDto> data, @RequestHeader("baslangic") String baslangic,
			@RequestHeader("bitis") String bitis) {
		try {
			String name = "girisCikislar.xlsx";
			data = service.getFisHareketByTarih(data, baslangic, bitis);
			InputStreamResource file = new InputStreamResource(excelHelper.toExcel(data));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + name)
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} catch (Exception e) {
			loggingManager.hataEkle(e, this);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
