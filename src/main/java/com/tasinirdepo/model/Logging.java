package com.tasinirdepo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "depo_logging")
public class Logging {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "hata_tanim")
	private String hataTanim;

	@Column(name = "sinif_adi")
	private String sinifAdi;
	
	@Column(name = "islem_tarih")
	private Date islemTarih;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHataTanim() {
		return hataTanim;
	}

	public void setHataTanim(String hataTanim) {
		this.hataTanim = hataTanim;
	}

	public String getSinifAdi() {
		return sinifAdi;
	}

	public void setSinifAdi(String sinifAdi) {
		this.sinifAdi = sinifAdi;
	}

	public Date getIslemTarih() {
		return islemTarih;
	}

	public void setIslemTarih(Date islemTarih) {
		this.islemTarih = islemTarih;
	}
	
	

}
