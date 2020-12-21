package com.tasinirdepo.enums;

public enum KullaniciIslemTurleriEnum {
	Guncelleme(1), Silme(2), Ekleme(3);
	
	private final int value;
	
	KullaniciIslemTurleriEnum(int newValue){
		value=newValue;
	}

	public int getValue() {
		return value;
	}
	
}
