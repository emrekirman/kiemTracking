package com.tasinirdepo.interfaces;

import java.io.File;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;

public interface IExcelReportHelper {

	File getFileFromResource(String fileName);
	
	InputStream getInputStreamFromResource(String fileName);

	void setCell(Row row, int cellNumber, String value);

	void setCell(Row row, int cellNumber, double value);
}
