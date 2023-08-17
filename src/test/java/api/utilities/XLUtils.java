package api.utilities;

import java.io.*;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public FileInputStream fn;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	public XLUtils(String path)
	{
		this.path=path;
	}
	public int getRowCount(String sheetName) throws IOException
	{
		fn=new FileInputStream(path);
		workbook=new XSSFWorkbook(fn);
		sheet = workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		fn.close();
		workbook.close();
		return rowcount;	
	}
	public int getColCount(String sheetName, int rowNum) throws IOException
	{
		fn=new FileInputStream(path);
		workbook=new XSSFWorkbook(fn);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellcount=row.getLastCellNum();
		fn.close();
		workbook.close();
		return cellcount;	
	}
	public String getcellValue(String sheetName,int rowno,int cellno) throws IOException
	{
		fn=new FileInputStream(path);
		workbook=new XSSFWorkbook(fn);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowno);
		cell=row.getCell(cellno);
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		return data;	
	}
	public void setCellValue(String sheetName, int rowno,int colno,String data ) throws IOException
	{
		File file=new File(path);
		if(!file.exists())
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fn=new FileInputStream(path);
		workbook=new XSSFWorkbook(fn);
		if(workbook.getSheetIndex(sheetName)==-1)
		workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
		if(sheet.getRow(rowno)==null)
			sheet.createRow(rowno);
		row=sheet.getRow(rowno);
		cell=row.createCell(colno);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		fo.close();
		fn.close();
		workbook.close();		
	}
	
}
