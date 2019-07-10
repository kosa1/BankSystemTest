package utilities;
import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilis {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static void setExcelFile(String Path,String SheetName) throws Exception {

        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Log.info("Excel is opened");
        } catch (Exception e){
            throw (e);
        }
    }
    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int RowNum, int ColNum) throws Exception{
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
           // System.out.println("wartosc: " + CellData + " typ komorki: " + Cell.getCellType());
            return CellData;
        }catch (Exception e){
            return"";
        }
    }

    public static void writeExcel(String filePath,String fileName,String sheetName,String dataToWrite) throws IOException {
        File file = new File(filePath+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowNo = sheet.getLastRowNum();
        //Get the first row from the sheet
        Row row = sheet.createRow(++rowNo);
        System.out.println("row no: " + rowNo);
        if(row  == null){
            System.out.println("jest null");
        }
        else {
            System.out.println("nie jest null");
        }
        //assert row != null;
        Cell cell = row.createCell(0);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(dataToWrite);

        //Close input stream
        inputStream.close();
        //Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }
}
