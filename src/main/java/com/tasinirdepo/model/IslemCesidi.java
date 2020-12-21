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
@Table(name = "depo_islem_cesidi")
public class IslemCesidi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "tanim")
	private String tanim;

	@Column(name = "eklenme_tarih")
	private Date eklenmeTarih;

	@OneToMany(mappedBy = "islemCesidi", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DepoFis> depoFisler;

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

	public List<DepoFis> getDepoFisler() {
		return depoFisler;
	}

	public void setDepoFisler(List<DepoFis> depoFisler) {
		this.depoFisler = depoFisler;
	}
}
