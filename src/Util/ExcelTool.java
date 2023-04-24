package Util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ExcelTool {
    private Workbook workbook;

    public ExcelTool() {
    }

    public void writeExcel(ArrayList<JTable> tables, ArrayList<String> titles, String excelFilePath) {
        this.workbook = new XSSFWorkbook();
        int index = 0;
        for (JTable table: tables) {
            Sheet sheet = workbook.createSheet(titles.get(index));
            int rowIndex = 0;

//            zero based indexing
            writeTitle(sheet, titles.get(index++), rowIndex, table.getColumnCount() - 1, rowIndex);
            rowIndex++;

//            create CellStyle
            CellStyle cellHeaderStyle = createStyleForHeader(sheet);
            CellStyle cellStyle = createStyleForNormal(sheet);

            Row rowCount = sheet.createRow(rowIndex++);
            for(int i = 0; i < table.getColumnCount(); i++) {
                Cell cell = rowCount.createCell(i);
                cell.setCellStyle(cellHeaderStyle);
                cell.setCellValue(table.getColumnName(i));
            }

            for(int i = 0; i < table.getRowCount(); i++) {
                Row row = sheet.createRow(rowIndex++);
                for(int k = 0; k < table.getColumnCount(); k++) {
                    Cell cell = row.createCell(k);
                    if(table.getValueAt(i, k) != null) {
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(table.getValueAt(i, k).toString());
                    }
                }
            }
            autoSizeColumn(sheet, table.getColumnCount());
        }

        createOutputFile(workbook, excelFilePath);
    }

    // Create CellStyle for header
    private CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16); // font size

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private CellStyle createStyleForNormal(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    private void writeTitle(Sheet sheet, String title, int colFrom, int colTo, int rowIndex) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 20); // font size

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, colFrom, colTo));
        Row rowCount = sheet.createRow(rowIndex);
        Cell cell = rowCount.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(title);
    }

    private void autoSizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }


    // Create output file
    public void createOutputFile(Workbook workbook, String excelFilePath){
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
