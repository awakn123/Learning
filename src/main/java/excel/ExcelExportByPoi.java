package excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelExportByPoi {
	public static void main(String[] args) throws IOException, InvalidFormatException {

		SXSSFWorkbook wb = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
		//打开压缩。flush会将数据传入一个临时的xml文件中，打开压缩后会是一个gzip文件。
		// 原文：
		/**
		 * SXSSF flushes sheet data in temporary files (a temp file per sheet) and the size of
		 * these temporary files can grow to a very large value.
		 * For example, for a 20 MB csv data the size of the temp xml becomes more than a gigabyte.
		 * If the size of the temp files is an issue, you can tell SXSSF to use gzip compression。
		 */
		wb.setCompressTempFiles(true);
		Sheet sh = wb.createSheet();
		for(int rownum = 0; rownum < 1000; rownum++){
			Row row = sh.createRow(rownum);
			for(int cellnum = 0; cellnum < 10; cellnum++){
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();
				cell.setCellValue(address);
			}

			// manually control how rows are flushed to disk
			if(rownum % 100 == 0) {
				((SXSSFSheet)sh).flushRows(100); // retain 100 last rows and flush all others

				// ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
				// this method flushes all rows
			}

		}

		File f = new File("sxssf.xlsx");
		if (f.exists()) f.delete();
		FileOutputStream out = new FileOutputStream(f);
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
		/*String fileName = "九九乘法表.xlsx";
//		createWorkbook(fileName);
		List<Map<String, Object>> mapList = Lists.newArrayList();
		for (int i=1;i<10;i++) {
			Map<String, Object> map = Maps.newHashMap();
			for (int j=1;j<10;j++) {
				map.put(String.valueOf(j), i*j);
			}
			mapList.add(map);
		}
		ExcelUtils.mapToExcelFile(mapList, fileName);*/
	}

	private static void createWorkbook(String fileName) throws IOException {
		File f = new File(fileName);
		if (f.exists()) f.delete();
		Workbook wb = new XSSFWorkbook();
		Sheet sheet1 = wb.createSheet("first");
		Sheet sheet2 = wb.createSheet("second");
		Row row = sheet1.createRow((short)0);
		// Create a cell and put a value in it.
		Cell cell = row.createCell(0);
		cell.setCellValue(1);
		cell = row.createCell(2);
		CellStyle cellStyle = wb.createCellStyle();
		CreationHelper createHelper = wb.getCreationHelper();
		cellStyle.setDataFormat(
				createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		cell.setCellValue(new Date());
		cell.setCellStyle(cellStyle);
		row.createCell(3).setCellValue("a string");
		row.createCell(4).setCellValue(true);
		row.createCell(5).setCellType(CellType.ERROR);
		row = sheet1.createRow(1);
		createCell(wb, row, (short) 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
		createCell(wb, row, (short) 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
		createCell(wb, row, (short) 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
		createCell(wb, row, (short) 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
		createCell(wb, row, (short) 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
		createCell(wb, row, (short) 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
		createCell(wb, row, (short) 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

		FileOutputStream fileOut = new FileOutputStream(f);
		wb.write(fileOut);
		fileOut.close();
	}

	private static void createCell(Workbook wb, Row row, short column, HorizontalAlignment halign, VerticalAlignment valign) {
		Cell cell = row.createCell(column);
		cell.setCellValue("Align It");
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(halign);
		cellStyle.setVerticalAlignment(valign);
		cell.setCellStyle(cellStyle);
	}
}
