/*
 */
package org.hellojavaer.poi.excel.utils.write;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 */
public class ExcelWriteTheme {

    public static final int BASE = 0;

    /**
     * 设置超链接等样式
     *
     * @param hssfWorkbook
     * @return
     */
    public static HSSFCellStyle linkStyleOfHSSF(HSSFWorkbook hssfWorkbook) {
        // 生成并设置另一个样式
        HSSFCellStyle linkStyle = hssfWorkbook.createCellStyle();
        //设置单元格边框
        //        linkStyle.setBorderBottom((short) 1);
        //        linkStyle.setBorderLeft((short) 1);
        //        linkStyle.setBorderRight((short) 1);
        //        linkStyle.setBorderTop((short) 1);
        //设置单元格背景颜色
        //        linkStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        //        linkStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        //设置字体下划线
        font.setUnderline((byte) 1);
        //设置字体颜色
        font.setColor(HSSFColor.BLUE.index);
        //设置字体
        linkStyle.setFont(font);
        // 生成另一个字体
        //        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        linkStyle.setFont(font);
        return linkStyle;
    }

    /**
     * 设置超链接等样式
     *
     * @param xssfWorkbook
     * @return
     */
    public static XSSFCellStyle linkStyleOfXSSF(XSSFWorkbook xssfWorkbook) {
        // 生成并设置另一个样式
        XSSFCellStyle linkStyle = xssfWorkbook.createCellStyle();
        //设置单元格边框
        //        linkStyle.setBorderBottom((short) 1);
        //        linkStyle.setBorderLeft((short) 1);
        //        linkStyle.setBorderRight((short) 1);
        //        linkStyle.setBorderTop((short) 1);
        //设置单元格背景颜色
        //        linkStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        //        linkStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        //设置字体下划线
        font.setUnderline((byte) 1);
        //设置字体颜色
        font.setColor(HSSFColor.BLUE.index);
        //设置字体
        linkStyle.setFont(font);
        // 生成另一个字体
        //        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        linkStyle.setFont(font);
        return linkStyle;
    }
}
