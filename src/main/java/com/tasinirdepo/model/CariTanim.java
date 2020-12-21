package com.tasinirdepo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "depo_cari_tanimlari")
public class CariTanim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cariAd")
	private String cariAd;

	@Column(name = "eklenme_tarih")
	private Date eklenmeTarih;

	@OneToMany(mappedBy = "cariTanim", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<FisHareketGiris> girisler;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCariAd() {
		return cariAd;
	}

	public void setCariAd(String cariAd) {
		this.cariAd = cariAd;
	}

	public Date getEklenmeTarih() {
		return eklenmeTarih;
	}

	public void setEklenmeTarih(Date eklenmeTarih) {
		this.eklenmeTarih = eklenmeTarih;
	}

	public List<FisHareketGiris> getGirisler() {
		return girisler;
	}

	public void setGirisler(List<FisHareketGiris> girisler) {
		this.girisler = girisler;
	}
	
}
