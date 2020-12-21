package com.tasinirdepo.dto;

import java.util.Date;

import com.tasinirdepo.model.DepoFis;

public class FaturaListeVM {

	public FaturaListeVM(int id, int fisNo, Date fisTarih, String aciklama, String islemCesidiTanim, String cariAd,
			boolean cikis) {
		this.id = id;
		this.fisNo = fisNo;
		this.fisTarih = fisTarih;
		this.aciklama = aciklama;
		this.islemCesidiTanim = islemCesidiTanim;
		this.cariAd = cariAd;
		this.giris = cikis;
	}

	public FaturaListeVM(DepoFis model) {
		this.id = model.getId();
		this.fisNo = model.getFisNo();
		this.fisTarih = model.getFisTarih();
		this.aciklama = model.getAciklama();
		this.islemCesidiTanim = model.getIslemCesidi().getTanim();
		this.cariAd = model.getCariTanim() != null ? model.getCariTanim().getCariAd() : " ";
		this.giris = model.isGiris();
	}

	private int id;

	private int fisNo;

	private Date fisTarih;

	private String aciklama;

	private String islemCesidiTanim;

	private String cariAd;

	private boolean giris;

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

	public boolean isGiris() {
		return giris;
	}

	public void setGiris(boolean cikis) {
		this.giris = cikis;
	}

}
