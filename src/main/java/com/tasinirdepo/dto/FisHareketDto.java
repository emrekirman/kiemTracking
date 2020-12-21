package com.tasinirdepo.dto;

import java.util.Date;

import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.model.FisHareketGiris;

public class FisHareketDto {

	public FisHareketDto(int id, int fisNo, Date fisTarih, String aciklama, String islemCesidiTanim, String cariAd,
			String birimTanim, boolean giris, double miktar) {
		this.id = id;
		this.fisNo = fisNo;
		this.fisTarih = fisTarih;
		this.aciklama = aciklama;
		this.islemCesidiTanim = islemCesidiTanim;
		this.cariAd = cariAd;
		this.birimTanim = birimTanim;
		this.giris = giris;
		this.miktar = miktar;
	}

	public FisHareketDto(FisHareketGiris model) {
		this.id = model.getId();
		this.fisNo = model.getDepoFis().getFisNo();
		this.fisTarih = model.getDepoFis().getFisTarih();
		this.aciklama = model.getDepoFis().getAciklama();
		this.islemCesidiTanim = model.getDepoFis().getIslemCesidi().getTanim();
		// this.cariAd = model.getCariTanim().getCariAd();
		this.stokTanim = model.getStokTanim().getTanim();
		this.giris = true;
		this.miktar = model.getMiktar();
		this.birimFiyat = model.getBirimFiyat();
	}

	public FisHareketDto(FisHareketCikis model) {
		this.id = model.getId();
		this.fisNo = model.getDepoFis().getFisNo();
		this.fisTarih = model.getDepoFis().getFisTarih();
		this.aciklama = model.getDepoFis().getAciklama();
		this.islemCesidiTanim = model.getDepoFis().getIslemCesidi().getTanim();
		this.stokTanim = model.getStokTanim().getTanim();
		this.giris = false;
		this.miktar = model.getMiktar();
		this.birimFiyat = model.getBirimFiyat();
	}

	private int id;

	private int fisNo;

	private Date fisTarih;

	private String aciklama;

	private String islemCesidiTanim;

	private String cariAd;

	private String birimTanim;

	private String stokTanim;

	private boolean giris;

	private double miktar;

	private double birimFiyat;

	private double tutar;

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

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getIslemCesidiTanim() {
		return islemCesidiTanim;
	}

	public void setIslemCesidiTanim(String islemCesidiTanim) {
		this.islemCesidiTanim = islemCesidiTanim;
	}

	public String getCariAd() {
		return cariAd;
	}

	public void setCariAd(String cariAd) {
		this.cariAd = cariAd;
	}

	public String getBirimTanim() {
		return birimTanim;
	}

	public void setBirimTanim(String birimTanim) {
		this.birimTanim = birimTanim;
	}

	public boolean isGiris() {
		return giris;
	}

	public void setGiris(boolean giris) {
		this.giris = giris;
	}

	public double getMiktar() {
		return miktar;
	}

	public void setMiktar(double miktar) {
		this.miktar = miktar;
	}

	public String getStokTanim() {
		return stokTanim;
	}

	public void setStokTanim(String stokTanim) {
		this.stokTanim = stokTanim;
	}

	public double getBirimFiyat() {
		return birimFiyat;
	}

	public void setBirimFiyat(double birimFiyat) {
		this.birimFiyat = birimFiyat;
	}

	public double getTutar() {
		this.tutar = this.birimFiyat * this.miktar;
		return this.tutar;
	}

}
