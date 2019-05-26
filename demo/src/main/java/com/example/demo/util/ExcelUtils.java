package com.example.demo.util;

import com.example.demo.entity.ExcelData;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.springframework.stereotype.Component;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Component(value = "excelUtils")
public class ExcelUtils {
    /**
     * 使用浏览器选择路径下载
     * @param response
     * @param fileName
     * @param data
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "utf-8"));
        exportExcel(data, response.getOutputStream());
    }

    public static int generateExcel(ExcelData excelData, String path) throws Exception {
        File f = new File(path);
        FileOutputStream out = new FileOutputStream(f);
        return exportExcel(excelData, out);
    }

    private static int exportExcel(ExcelData data, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        int rowIndex = 0;
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            rowIndex = writeExcel(wb, sheet, data);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //此处需要关闭 wb 变量
            out.close();
        }
        return rowIndex;
    }

    /**
     * 表不显示字段
     * @param wb
     * @param sheet
     * @param data
     * @return
     */
//    private static int writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
//        int rowIndex = 0;
//        writeTitlesToExcel(wb, sheet, data.getTitles());
//        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
//        autoSizeColumns(sheet, data.getTitles().size() + 1);
//        return rowIndex;
//    }

    /**
     * 表显示字段
     * @param wb
     * @param sheet
     * @param data
     * @return
     */
    private static int writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
        return rowIndex;
    }
    /**
     * 设置表头
     *
     * @param wb
     * @param sheet
     * @param titles
     * @return
     */
    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;
        Font titleFont = wb.createFont();
        //设置字体
        titleFont.setFontName("simsun");
        //设置粗体
        titleFont.setBoldweight(Short.MAX_VALUE);
        //设置字号
        titleFont.setFontHeightInPoints((short) 14);
        //设置颜色
        titleFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle titleStyle = wb.createCellStyle();
        //水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置图案颜色
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        //设置图案样式
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        Row titleRow = sheet.createRow(rowIndex);
        titleRow.setHeightInPoints(25);
        colIndex = 0;
        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }

    /**
     * 设置内容
     *
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeightInPoints(25);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    /**
     * 自动调整列宽
     *
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            System.out.println("1--"+orgWidth);
            System.out.println("2--"+newWidth);
            if (newWidth > orgWidth) {
                if(newWidth<255*256){
                    //sheet.setColumnWidth(i, newWidth < 3000 ? 3000 : newWidth);
                    if(newWidth > 30000){
                        sheet.setColumnWidth(i,15000 );
                    }else if(newWidth > 10000){
                        sheet.setColumnWidth(i,8000 );
                    }else if(newWidth > 5000){
                        sheet.setColumnWidth(i,5000 );
                    }else {
                        sheet.setColumnWidth(i,3000 );
                    }
                }else{
                    sheet.setColumnWidth(i,20000 );
                }
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置边框
     *
     * @param style
     * @param border
     * @param color
     */
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }



    /**
     * @Title: exportExcel
     * @Description: 导出Excel的方法
     * @author: evan @ 2014-01-09
     * @param workbook
     * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet)
     * @param sheetTitle  （sheet的名称）
     * @param headers    （表格的标题）
     * @param result   （表格的数据）
     * @param out  （输出流）
     * @throws Exception
     */
    public void exportExcel1(HSSFWorkbook workbook, int sheetNum,
                             String sheetTitle, String[] headers, List<List<Object>> result,
                             OutputStream out) throws Exception {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short) i);

            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 1;
            int colIndex;
            for (List<Object> rowData : result) {
                Row dataRow = sheet.createRow(index);
                dataRow.setHeightInPoints(25);
                colIndex = 0;
                for (Object cellData : rowData) {
                    Cell cell = dataRow.createCell(colIndex);
                    if (cellData != null) {
                        cell.setCellValue(cellData.toString());
                    } else {
                        cell.setCellValue("");
                    }
                    colIndex++;
                }
                index++;
            }

        }
    }




}
