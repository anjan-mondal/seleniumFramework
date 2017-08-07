package utility;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils1 {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	
	private static String dataFilePath;

	public static Object[][] getData(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;

			int startCol = 1;

			int ci, cj;

			int totalRows = ExcelWSheet.getLastRowNum();

			// you can write a function as well to get Column count

			int totalCols = 2;
			totalCols = ExcelWSheet.getRow(0).getLastCellNum() - 1;// getPhysicalNumberOfCells();
			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

					// System.out.println("c["+i+"]"+"c["+j+"]"+tabArray[ci][cj]);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}
	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			int dataType = Cell.getCellType();
			if (dataType == 3) {
				return "";
			} else {
				String CellData = Cell.getStringCellValue();

				return CellData;
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}

	public static String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;

		try {

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");

			value = value.substring(posi + 1);

			//return value;

		} catch (Exception e) {

			throw (e);

		}
		
		dataFilePath= System.getProperty("user.dir");
		dataFilePath =dataFilePath+File.separator+"src"+File.separator+"testscripts"+File.separator+"datatables"+File.separator+value+".xlsx";
		return  dataFilePath;

	}

}


