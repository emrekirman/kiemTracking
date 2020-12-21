package com.tasinirdepo.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dto.StokMevcuduDto;
import com.tasinirdepo.interfaces.IExcelExportReport;
import com.tasinirdepo.interfaces.IExcelReportHelper;
import com.tasinirdepo.interfaces.ILogginManager;

@Service
@Qualifier("stokMevcuduReport")
public class StokMevcuduReport implements IExcelExportReport<StokMevcuduDto> {

	private IExcelReportHelper excelReport;

	private ILogginManager logManager;

	@Autowired
	public StokMevcuduReport(IExcelReportHelper excelReport, @Qualifier("logRepo") ILogginManager logManager) {
		this.excelReport = excelReport;
		this.logManager = logManager;
	}

	@Override
	public ByteArrayInputStream toExcel(List<StokMevcuduDto> data) {
		try {
//			File file = excelReport.getFileFromResource("reports/StokMevcudu.xlsx");
//			FileInputStream excelFile;
//			excelFile = new FileInputStream(file);

			InputStream stream = excelReport.getInputStreamFromResource("/reports/StokMevcudu.xlsx");

			XSSFWorkbook workbook2 = new XSSFWorkbook(stream);
			XSSFSheet sheet = workbook2.getSheetAt(0);

//			XSSFWorkbook workbook2 = new XSSFWorkbook();
//			XSSFSheet sheet = workbook2.createSheet();
			int siraNo = 1;
			int rowNumber = 2;
			for (StokMevcuduDto item : data) {
				Row row = sheet.getRow(rowNumber);
				excelReport.setCell(row, 0, siraNo);
				excelReport.setCell(row, 1, item.getStokKod());
				excelReport.setCell(row, 2, item.getStokTanim());
				excelReport.setCell(row, 3, item.getMiktar() + " " + item.getBirim());
				siraNo++;
				rowNumber++;
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook2.write(out);
			workbook2.close();
			return new ByteArrayInputStream(out.toByteArray());

		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return null;
		}
	}

	@Override
	public ByteArrayInputStream toExcel(StokMevcuduDto data) {

		return null;
	}

}
