package utilities;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilis {

    public static XSSFSheet ExcelWSheet;
    public static XSSFWorkbook ExcelWBook;
    public static XSSFCell Cell;
    public static FileInputStream ExcelFile;

    public static void removeRow(XSSFSheet sheet, int rowIndex) {
        int lastRowNum=sheet.getLastRowNum();
        if(rowIndex>=0&&rowIndex<lastRowNum){
            sheet.shiftRows(rowIndex+1,lastRowNum, -1);
        }
        if(rowIndex==lastRowNum){
            XSSFRow removingRow=sheet.getRow(rowIndex);
            if(removingRow!=null){
                sheet.removeRow(removingRow);
            }
        }
    }
    public static void setExcelFile(String Path,String SheetName) throws Exception {

        try {
            // Open the Excel file
            ExcelFile = new FileInputStream(Path);
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
            // System.out.println("wartosc: " + CellData + " typ komorki: " + Cell.getCellType());
            return Cell.getStringCellValue();
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
      //  System.out.println("row no: " + rowNo);

        assert row != null;
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

    public static void deleteRow(String value) throws Exception {
        // looking for right value
        int index=0;
        for(int i=1; i<=ExcelWSheet.getLastRowNum();i++){
            if(ExcelUtilis.getCellData(i,0).equals(value)){
                //save index
                index = i;
                Log.info("index: "+index);
            }
        }
        // deleting whole row
        removeRow(ExcelWSheet, index);
//        if(index!=0) {
//            Row rowToDelete = ExcelWSheet.getRow(index);
//            ExcelWSheet.removeRow(rowToDelete);
//            Log.info("Correct value has been removed from excel file");
//        }else {
//            Log.info("Correct value to delete could not be found!");
//        }
        // close input stream
       // ExcelFile.close();
    }

    public boolean deleteRow(String sheetName, String excelPath, int rowNo) throws IOException {

        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;

        try {
            FileInputStream file = new FileInputStream(new File(excelPath));
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return false;
            }
            int lastRowNum = sheet.getLastRowNum();
            if (rowNo >= 0 && rowNo < lastRowNum) {
               // sheet.shiftRows(rowNo + 1, lastRowNum, -1);
                Row row = sheet.getRow(2);
                sheet.removeRow(row);
            }
            if (rowNo == lastRowNum) {
                XSSFRow removingRow = sheet.getRow(rowNo);
                if (removingRow != null) {
                    sheet.removeRow(removingRow);
                }
            }
            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(excelPath));
            workbook.write(outFile);
            outFile.close();


        } catch (Exception e) {
            throw e;
        } finally {
            if (workbook != null)
                workbook.close();
        }
        return false;
    }

}
