package com.tasinirdepo.dao;

import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.KullaniciIslemTurleri;

public interface IKullaniciIslemRepository {
	KullaniciIslemTurleri getByEnum(KullaniciIslemTurleriEnum islem);
}
