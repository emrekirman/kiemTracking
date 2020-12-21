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
@Table(name = "depo_stok_tanimlari")
public class StokTanim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "tanim")
	private String tanim;

	@Column(name = "depo_kod")
	private String depoKod;

	@Column(name = "eklene_tarih")
	private Date eklenmeTarih;

	@OneToMany(mappedBy = "stokTanim", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<FisHareketGiris> girisler;

	@OneToMany(mappedBy = "stokTanim",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<FisHareketCikis> cikislar;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	public String getDepoKod() {
		return depoKod;
	}

	public void setDepoKod(String depoKod) {
		this.depoKod = depoKod;
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

	public List<FisHareketCikis> getCikislar() {
		return cikislar;
	}

	public void setCikislar(List<FisHareketCikis> cikislar) {
		this.cikislar = cikislar;
	}
	
}
