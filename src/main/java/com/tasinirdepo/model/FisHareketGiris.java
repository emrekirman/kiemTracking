package com.tasinirdepo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "depo_fis_hareket_giris")
public class FisHareketGiris {
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
	@JoinColumn(name = "stok_tanim_id")
	private StokTanim stokTanim;

	@ManyToOne
	@JoinColumn(name = "cari_id")
	private CariTanim cariTanim;

	@OneToMany(mappedBy = "fisHareketGiris")
	private List<FisHareketCikis> cikisList;

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

	public StokTanim getStokTanim() {
		return stokTanim;
	}

	public void setStokTanim(StokTanim stokTanim) {
		this.stokTanim = stokTanim;
	}

	public CariTanim getCariTanim() {
		return cariTanim;
	}

	public void setCariTanim(CariTanim cariTanim) {
		this.cariTanim = cariTanim;
	}

	public List<FisHareketCikis> getCikisList() {
		return cikisList;
	}

	public void setCikisList(List<FisHareketCikis> cikisList) {
		this.cikisList = cikisList;
	}

}
