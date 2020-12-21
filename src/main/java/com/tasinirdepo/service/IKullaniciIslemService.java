package com.tasinirdepo.service;

import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.KullaniciIslem;

public interface IKullaniciIslemService {
	KullaniciIslem kullaniciIslemOlustur(KullaniciIslemTurleriEnum islem, Object entity, int id);
}
