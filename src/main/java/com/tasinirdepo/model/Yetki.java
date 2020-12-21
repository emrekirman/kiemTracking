package com.tasinirdepo.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="depo_yetki_tanimlari")
public class Yetki {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="tanim")
	private String tanim;
	
	@Column(name="olusturma_tarih")
	private Date olusturmaTarih;

	@JsonIgnore
	@OneToMany(mappedBy = "yetki")
	private List<Kullanici> yetkiler;	
	
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

	public Date getOlusturmaTarih() {
		return olusturmaTarih;
	}

	public void setOlusturmaTarih(Date olusturmaTarih) {
		this.olusturmaTarih = olusturmaTarih;
	}

	public List<Kullanici> getYetkiler() {
		return yetkiler;
	}

	public void setYetkiler(List<Kullanici> yetkiler) {
		this.yetkiler = yetkiler;
	}

	
}
