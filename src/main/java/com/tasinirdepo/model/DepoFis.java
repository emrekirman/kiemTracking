package com.tasinirdepo.model;

import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "depo_fis")
public class DepoFis{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fis_no")
	private int fisNo;
	
	@Column(name="fis_tarih")
	private Date fisTarih;
	
	@Column(name="dayanak_belge")
	private String dayanakBelge;
	
	@Column(name="dayanak_tarih")
	private Date dayanakTarih;
	
	@Column(name = "aciklama")
	private String aciklama;
	
	@Column(name="dayanak_no")
	private String dayanakNo;
	
	@Column(name="giris")
	private boolean giris;
	
	@ManyToOne
	@JoinColumn(name="islem_cesidi_id")
	@JsonProperty("islemCesidi")
	private IslemCesidi islemCesidi;
	
	@OneToMany(mappedBy = "depoFis")
	@JsonProperty("girisList")
	private List<FisHareketGiris> girisList;
	
	@OneToMany(mappedBy = "depoFis")
	@JsonProperty("cikisList")
	private List<FisHareketCikis> cikisList;
	
	@ManyToOne
	@JoinColumn(name = "cari_id")
	@JsonProperty("cariTanim")
	private CariTanim cariTanim;
	
	@ManyToOne
	@JoinColumn(name = "birim_id")
	private Birim birim;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFisNo() {
		return fisNo;
	}

	public void setFisNo(int fisNo) {
		this.fisNo = fisNo;
	}

	public Date getFisTarih() {
		return fisTarih;
	}

	public void setFisTarih(Date fisTarih) {
		this.fisTarih = fisTarih;
	}

	public String getDayanakBelge() {
		return dayanakBelge;
	}

	public void setDayanakBelge(String dayanakBelge) {
		this.dayanakBelge = dayanakBelge;
	}

	public Date getDayanakTarih() {
		return dayanakTarih;
	}

	public void setDayanakTarih(Date dayanakTarih) {
		this.dayanakTarih = dayanakTarih;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getDayanakNo() {
		return dayanakNo;
	}

	public void setDayanakNo(String dayanakNo) {
		this.dayanakNo = dayanakNo;
	}

	public IslemCesidi getIslemCesidi() {
		return islemCesidi;
	}

	public void setIslemCesidi(IslemCesidi islemCesidi) {
		this.islemCesidi = islemCesidi;
	}

	public boolean isGiris() {
		return giris;
	}

	public void setGiris(boolean giris) {
		this.giris = giris;
	}
	
	public List<FisHareketGiris> getGirisList() {
		return girisList;
	}

	public void setGirisList(List<FisHareketGiris> girisList) {
		this.girisList = girisList;
	}

	public CariTanim getCariTanim() {
		return cariTanim;
	}

	public void setCariTanim(CariTanim cariTanim) {
		this.cariTanim = cariTanim;
	}

	public Birim getBirim() {
		return birim;
	}

	public void setBirim(Birim birim) {
		this.birim = birim;
	}

	public List<FisHareketCikis> getCikisList() {
		return cikisList;
	}

	public void setCikisList(List<FisHareketCikis> cikisList) {
		this.cikisList = cikisList;
	}
	
}

