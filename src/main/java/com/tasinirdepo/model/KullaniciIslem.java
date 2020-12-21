package com.tasinirdepo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "depo_kullanici_islem")
public class KullaniciIslem {

	public KullaniciIslem(Date islemTarih, KullaniciIslemTurleri kullaniciIslemTurleri, Kullanici kullanici,
			String tanim, int entityId) {
		this.islemTarih = islemTarih;
		this.kullaniciIslemTurleri = kullaniciIslemTurleri;
		this.kullanici = kullanici;
		this.tanim = tanim;
		this.entityId = entityId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "islem_tarih")
	private Date islemTarih;

	@ManyToOne
	@JoinColumn(name = "islem_turu_id")
	private KullaniciIslemTurleri kullaniciIslemTurleri;

	@ManyToOne
	@JoinColumn(name = "kullanici_id")
	private Kullanici kullanici;

	@Column(name = "tanim")
	private String tanim;

	@Column(name = "entity_id")
	private int entityId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getIslemTarih() {
		return islemTarih;
	}

	public void setIslemTarih(Date islemTarih) {
		this.islemTarih = islemTarih;
	}

	public KullaniciIslemTurleri getKullaniciIslemTurleri() {
		return kullaniciIslemTurleri;
	}

	public void setKullaniciIslemTurleri(KullaniciIslemTurleri kullaniciIslemTurleri) {
		this.kullaniciIslemTurleri = kullaniciIslemTurleri;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
}
