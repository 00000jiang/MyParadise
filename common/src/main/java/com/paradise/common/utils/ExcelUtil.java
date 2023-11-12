package com.paradise.common.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author: daisx
 * @Date: 2021/10/11 15:50
 */
public class ExcelUtil {

    /**
     * excel首列序号列样式
     * @param workbook
     * @return
     */
    public static CellStyle firstCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        //设置边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        //文字
        Font font = workbook.createFont();
        font.setBold(Boolean.TRUE);
        cellStyle.setFont(font);
        return cellStyle;
    }


   public static CellStyle setCellStyle(XSSFWorkbook wb,String fontName,short points,boolean VerticalCenter,boolean HorizontalCenter){
       CellStyle cellStyle = wb.createCellStyle();
       Font font = wb.createFont();
       if (fontName!=null) {
           font.setFontName(fontName);
       }
       if (points!=0) {
           font.setFontHeightInPoints(points);
       }
       cellStyle.setFont(font);
       if (VerticalCenter) {
           cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
       }
       if (HorizontalCenter) {
           cellStyle.setAlignment(HorizontalAlignment.CENTER);
       }
       cellStyle.setWrapText(true);
       /*font.setFontName("宋体");
       font.setFontHeightInPoints((short) 30);
       cellStyle.setFont(font);
       cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);*/
       return  cellStyle;
   }


}
