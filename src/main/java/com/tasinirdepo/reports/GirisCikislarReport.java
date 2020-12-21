package com.tasinirdepo.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dto.FisHareketDto;
import com.tasinirdepo.interfaces.IExcelExportReport;
import com.tasinirdepo.interfaces.IExcelReportHelper;
import com.tasinirdepo.interfaces.ILogginManager;

@Service
@Qualifier("girisCikislarReport")
public class GirisCikislarReport implements IExcelExportReport<FisHareketDto> {

	private IExcelReportHelper excelReport;

	private ILogginManager logManager;

	@Autowired
	public GirisCikislarReport(IExcelReportHelper excelReport, @Qualifier("logRepo") ILogginManager logManager) {
		this.excelReport = excelReport;
		this.logManager = logManager;
	}

	@Override
	public ByteArrayInputStream toExcel(List<FisHareketDto> data) {
		try {
			InputStream stream = excelReport.getInputStreamFromResource("/reports/GirisCikislar.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(stream);
			// XSSFSheet tempSheet = workbook.getSheetAt(0);
			workbook.setSheetHidden(0, 2);
			XSSFSheet sheet = workbook.cloneSheet(0);


			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			
			excelReport.setCell(sheet.getRow(1), 2, data.get(0).getStokTanim());
			excelReport.setCell(sheet.getRow(1), 6, dateFormat.format(new Date().getTime()));
			
			double genelToplamMiktar = genelToplam(data)[0];
			double genelToplamTutar = genelToplam(data)[1];

			int sayfaToplamMiktar = 0;
			double sayfaToplamTutar = 0;

			int siraNo = 1;
			int rowNumber = 3;
			for (FisHareketDto item : data) {
				Row row = sheet.getRow(rowNumber);
				excelReport.setCell(row, 0, siraNo);
				excelReport.setCell(row, 1, item.isGiris() ? "Giriş" : "Çıkış");
				excelReport.setCell(row, 2, item.getFisNo());
				excelReport.setCell(row, 3, dateFormat.format(item.getFisTarih()));
				excelReport.setCell(row, 4, item.getBirimFiyat());
				excelReport.setCell(row, 5, item.getMiktar());
				excelReport.setCell(row, 6, (item.getMiktar() * item.getBirimFiyat()));

				if (item.isGiris()) {
					sayfaToplamMiktar += item.getMiktar();
					sayfaToplamTutar += (item.getMiktar() * item.getBirimFiyat());
				} else {
					sayfaToplamMiktar -= item.getMiktar();
					sayfaToplamTutar -= (item.getMiktar() * item.getBirimFiyat());
				}

				Row genelToplamRow = sheet.getRow(50);
				excelReport.setCell(genelToplamRow, 5, genelToplamMiktar);
				excelReport.setCell(genelToplamRow, 6, genelToplamTutar);

				Row sayfaToplamRow = sheet.getRow(49);
				excelReport.setCell(sayfaToplamRow, 5, sayfaToplamMiktar);
				excelReport.setCell(sayfaToplamRow, 6, sayfaToplamTutar);

				if (rowNumber == 48) {

					sheet = workbook.cloneSheet(0);
					excelReport.setCell(sheet.getRow(1), 2, item.getStokTanim());
					excelReport.setCell(sheet.getRow(1), 6, dateFormat.format(new Date().getTime()));

					sayfaToplamMiktar = 0;
					sayfaToplamTutar = 0;

					rowNumber = 2;// alt tarafta bir arttırdığımız için bir eksiğine setledik
				}
				siraNo++;
				rowNumber++;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			workbook.close();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return null;
		}
	}

	@Override
	public ByteArrayInputStream toExcel(FisHareketDto data) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] genelToplam(List<FisHareketDto> data) {
		double toplamTutar = 0;
		int toplamMiktar = 0;
		for (FisHareketDto item : data) {
			if (item.isGiris()) {
				toplamTutar += (item.getMiktar() * item.getBirimFiyat());
				toplamMiktar += item.getMiktar();
			} else {
				toplamTutar -= (item.getMiktar() * item.getBirimFiyat());
				toplamMiktar -= item.getMiktar();
			}
		}
		double[] arr = { toplamMiktar, toplamTutar };
		return arr;
	}

}
