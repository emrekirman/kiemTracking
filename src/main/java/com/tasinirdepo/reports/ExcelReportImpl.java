package com.tasinirdepo.reports;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Repository;

import com.tasinirdepo.interfaces.IExcelReportHelper;

@Repository("excelReport")
public class ExcelReportImpl implements IExcelReportHelper {

	@Override
	public File getFileFromResource(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			try {
				return new File(resource.toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	@Override
	public InputStream getInputStreamFromResource(String fileName) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(fileName);
			return inputStream;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void setCell(Row row, int cellNumber, String value) {
		Cell cell = row.getCell(cellNumber);
		if (cell != null) {
			cell.setCellValue(value);
		}
	}

	@Override
	public void setCell(Row row, int cellNumber, double value) {
		Cell cell = row.getCell(cellNumber);
		if (cell != null) {
			cell.setCellValue(value);
		}
	}

}
