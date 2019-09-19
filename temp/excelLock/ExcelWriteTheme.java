/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hellojavaer.poi.excel.utils.write;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author <a href="mailto:hellojavaer@gmail.com">zoukaiming</a>
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

    /**
     * 设置 锁定 样式
     * @param xssfWorkbook
     * @return
     */
    public static XSSFCellStyle lockedStyleOfXSSF(XSSFWorkbook xssfWorkbook) {
        // 生成并设置另一个样式
        XSSFCellStyle lockedStyle = xssfWorkbook.createCellStyle();
        lockedStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        lockedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        //        style.setFillForegroundColor(HSSFColor.GREEN.index);//设置图案颜色
//        style.setFillBackgroundColor(HSSFColor.RED.index);//设置图案背景色
//        style.setFillPattern(HSSFCellStyle.SQUARES);//设置图案样式

        lockedStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        lockedStyle.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        lockedStyle.setLocked(true);
        return lockedStyle;
    }

    /**
     * 设置 未锁定 样式
     * @param xssfWorkbook
     * @return
     */
    public static XSSFCellStyle unLockedStyleOfXSSF(XSSFWorkbook xssfWorkbook) {
        // 生成并设置另一个样式
        XSSFCellStyle unLockedStyle = xssfWorkbook.createCellStyle();
        unLockedStyle.setLocked(false);
        return unLockedStyle;
    }

}
