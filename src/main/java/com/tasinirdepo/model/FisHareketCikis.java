package com.tasinirdepo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "depo_fis_hareket_cikis")
public class FisHareketCikis {

	public FisHareketCikis(FisHareketCikis model, double miktar, FisHareketGiris girisModel) {
		this.olcuBirim = model.getOlcuBirim();
		this.birim = model.getBirim();
		this.stokTanim = model.getStokTanim();
		this.miktar = miktar;
		this.birimFiyat = girisModel.getBirimFiyat();
		this.tutar = this.miktar * this.birimFiyat;
		this.yil = 2020;
		this.fisHareketGiris = girisModel;
	}

	public FisHareketCikis() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "olcu_birim_id")
	private DepoOlcuBirim olcuBirim;

	@Column(name = "miktar")
	private double miktar;

	@Column(name = "birim_fiyat")
	private double birimFiyat;

	@Column(name = "tutar")
	private double tutar;

	@ManyToOne
	@JoinColumn(name = "fis_id")
	private DepoFis depoFis;

	@Column(name = "yil")
	private int yil;

	@ManyToOne
	@JoinColumn(name = "birim_id")
	private Birim birim;

	@ManyToOne
	@JoinColumn(name = "stok_tanim_id")
	private StokTanim stokTanim;

	@ManyToOne
	@JoinColumn(name = "fis_hareket_giris_id")
	private FisHareketGiris fisHareketGiris;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DepoOlcuBirim getOlcuBirim() {
		return olcuBirim;
	}

	public void setOlcuBirim(DepoOlcuBirim olcuBirim) {
		this.olcuBirim = olcuBirim;
	}

	public double getMiktar() {
		return miktar;
	}

	public void setMiktar(double miktar) {
		this.miktar = miktar;
	}

	public double getBirimFiyat() {
		return birimFiyat;
	}

	public void setBirimFiyat(double birimFiyat) {
		this.birimFiyat = birimFiyat;
	}

	public double getTutar() {
		return tutar;
	}

	public void setTutar(double tutar) {
		this.tutar = tutar;
	}

	public DepoFis getDepoFis() {
		return depoFis;
	}

	public void setDepoFis(DepoFis depoFis) {
		this.depoFis = depoFis;
	}

	public int getYil() {
		return yil;
	}

	public void setYil(int yil) {
		this.yil = yil;
	}

	public Birim getBirim() {
		return birim;
	}

	public void setBirim(Birim birim) {
		this.birim = birim;
	}

	public StokTanim getStokTanim() {
		return stokTanim;
	}

	public void setStokTanim(StokTanim stokTanim) {
		this.stokTanim = stokTanim;
	}

	public FisHareketGiris getFisHareketGiris() {
		return fisHareketGiris;
	}

	public void setFisHareketGiris(FisHareketGiris fisHareketGiris) {
		this.fisHareketGiris = fisHareketGiris;
	}

}
