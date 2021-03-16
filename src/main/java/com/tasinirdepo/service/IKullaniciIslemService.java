package com.tasinirdepo.service;

import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.KullaniciIslem;

public interface IKullaniciIslemService extends IBaseService<KullaniciIslem>{
	KullaniciIslem kullaniciIslemOlustur(KullaniciIslemTurleriEnum islem, Object entity, int id);
}
