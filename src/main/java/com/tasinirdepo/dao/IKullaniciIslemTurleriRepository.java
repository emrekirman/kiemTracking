package com.tasinirdepo.dao;

import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.KullaniciIslemTurleri;

public interface IKullaniciIslemTurleriRepository extends IBaseRepository<KullaniciIslemTurleri>{
	KullaniciIslemTurleri getByEnum(KullaniciIslemTurleriEnum islem);
}
