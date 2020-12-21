package com.tasinirdepo.dto;

import com.tasinirdepo.model.FisHareketGiris;

public class StokMevcuduDto {

	public StokMevcuduDto(String stokTanim, String stokKod, String birim, double miktar) {
		this.stokTanim = stokTanim;
		this.stokKod = stokKod;
		this.birim = birim;
		this.miktar = miktar;
	}

	public StokMevcuduDto(FisHareketGiris model) {
		this.stokTanim = model.getStokTanim().getTanim();
		this.stokKod = model.getStokTanim().getDepoKod();
		this.birim = model.getOlcuBirim().getTanim();
		this.miktar = model.getMiktar();
	}

	private String stokTanim;

	private String stokKod;

	private String birim;

	private double miktar;

	public String getStokTanim() {
		return stokTanim;
	}

	public void setStokTanim(String stokTanim) {
		this.stokTanim = stokTanim;
	}

	public String getStokKod() {
		return stokKod;
	}

	public void setStokKod(String stokKod) {
		this.stokKod = stokKod;
	}

	public String getBirim() {
		return birim;
	}

	public void setBirim(String birim) {
		this.birim = birim;
	}

	public double getMiktar() {
		return miktar;
	}

	public void setMiktar(double miktar) {
		this.miktar = miktar;
	}

}
