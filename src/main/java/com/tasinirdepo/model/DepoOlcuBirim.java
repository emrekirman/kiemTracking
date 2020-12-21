package com.tasinirdepo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "depo_olcu_birim")
public class DepoOlcuBirim {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tanim")
	private String tanim;
	
	@Column(name = "eklenme_tarih")
	private Date eklenmeTarih;

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

	public Date getEklenmeTarih() {
		return eklenmeTarih;
	}

	public void setEklenmeTarih(Date eklenmeTarih) {
		this.eklenmeTarih = eklenmeTarih;
	}
	
}

