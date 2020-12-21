package com.tasinirdepo.interfaces;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface IExcelExportReport<T> {
	public ByteArrayInputStream toExcel(List<T> data);
	
	public ByteArrayInputStream toExcel(T data);
}
