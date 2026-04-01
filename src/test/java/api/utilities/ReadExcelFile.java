package api.utilities;

import java.io.FileInputStream;
import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcelFile {
	
	public static FileInputStream inputStream;
	public static XSSFWorkbook workBook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static String getCellValue(String fileName,String sheetName, int rowNo, int colNo)
	{
		try {

			
			inputStream = new FileInputStream(new File(fileName));
			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);
			cell = excelSheet.getRow(rowNo).getCell(colNo);
			
			
			workBook.close();
			
			
			
			return cell.getStringCellValue();
			
		}//End of try
		catch(Exception e) {
			return null;
		}
	}
	
	public static int getRowCount(String fileName, String sheetName)
	{
		try {
			inputStream = new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);
			
			int totalRows = excelSheet.getLastRowNum() + 1;
			workBook.close();
			
			return totalRows;
			
		}//End of try
		catch(Exception e) {
			return 0;
		}
	}
	
	public static int getColCount(String fileName, String sheetName)
	{
		try {
			inputStream = new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);
			
			int totalCols = excelSheet.getRow(0).getLastCellNum();
			workBook.close();
			return totalCols;
		} //End of try
		catch(Exception e)
		{
			return 0;
		}
	}
	
	
	
	
}
