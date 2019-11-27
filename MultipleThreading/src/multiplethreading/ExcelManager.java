/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplethreading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Thuan.Truong
 */
public class ExcelManager implements Runnable {
    public final static int ROW_NUMBER = 100;
    public final static int COL_NUMBER = 1000;
    public File file;
    public XSSFWorkbook workbook;
    
    /**
     * Constructor to create the Excel Manager object
     * @param filePath 
     */
    public ExcelManager(String filePath) {
        try {
            file = new File(filePath);
            
            workbook = new XSSFWorkbook();
            XSSFSheet sheet1 = workbook.createSheet("Java Book 1");
            XSSFSheet sheet2 =workbook.createSheet("Java Book 2");
            
            for (int row = 0; row < ROW_NUMBER; row++) {
                XSSFRow row1= sheet1.createRow(row);
                XSSFRow row2= sheet2.createRow(row);
                for (int col = 0; col < COL_NUMBER; col++) {
                    row1.createCell(col);
                    row2.createCell(col);
                }
            }
            
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelManager.class.getName()).log(Level.WARNING, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelManager.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
    /**
     * Write data to Excel file. This method is synchronized
     * @param row : Index of row
     * @param col : Index of column
     * @param sheetIndex : Index of sheet
     * @param data : Data will be write to file
     * @return 
     */
    public synchronized boolean writeToFile(int row, int col, int sheetIndex, String data) {
        boolean writeStatus = false;
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        try (FileOutputStream outFile = new FileOutputStream(file)){

            //Update the value of cell
            XSSFCell cell = sheet.getRow(row).getCell(col);
            cell.setCellValue(data);
            workbook.write(outFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            writeStatus = false;
        } catch (IOException ex) {
            Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            writeStatus = false;
        }
        
        return writeStatus;
    }
    
    /**
     * Override the run method of Runnable implementation to fill all data to excel file
     */
    @Override
    public void run() {
        for (int row = 0; row < ROW_NUMBER; row++) {
            for (int col = 0; col < COL_NUMBER; col++) {
                try {
                     writeToFile(row, col, 0, "Testing");
                    System.out.println(Thread.currentThread().getName() + " - Write data to [" + row + ":" + col + "]");
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExcelManager.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }
    }
}
