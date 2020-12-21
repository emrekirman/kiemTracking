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
@Table(name = "depo_kullanici")
public class Kullanici {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "kullanici_ad")
	private String kullaniciAd;

	@Column(name = "ad")
	private String ad;

	@Column(name = "soyad")
	private String soyad;

	@Column(name = "eklenme_tarih")
	private Date eklenmeTarih;

	@ManyToOne
	@JoinColumn(name = "yetki_id")
	private Yetki yetki;

	@Column(name = "sifre")
	private String sifre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKullaniciAd() {
		return kullaniciAd;
	}

	public void setKullaniciAd(String kullaniciAd) {
		this.kullaniciAd = kullaniciAd;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Date getEklenmeTarih() {
		return eklenmeTarih;
	}

	public void setEklenmeTarih(Date eklenmeTarih) {
		this.eklenmeTarih = eklenmeTarih;
	}

	public Yetki getYetki() {
		return yetki;
	}

	public void setYetki(Yetki yetki) {
		this.yetki = yetki;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

}
