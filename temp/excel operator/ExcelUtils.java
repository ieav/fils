package com.huawei.idt.libing.vulnerabilitymanagementservice.util;


import com.huawei.idt.libing.vulnerabilitymanagementservice.model.VulExportBean;
import com.huawei.idt.libing.vulnerabilitymanagementservice.util.excelutils.ExcelUtils;
import com.huawei.idt.libing.vulnerabilitymanagementservice.util.excelutils.common.VulStatusEnum;
import com.huawei.idt.libing.vulnerabilitymanagementservice.util.excelutils.write.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ExcelOperatorUtils {

    @Autowired
    private NormalUtils utils;

    /*导出excel 设置 漏洞编号、dts_no 的超链接 */
    public final static String BASE_CVE_URL="https://nvd.nist.gov/vuln/detail/";

    public final static String  BASE_DTS_URL="http://dts.huawei.com/net/dts/DTS/DTSWorkFlowPage.aspx?No=";


    /***
     * 解析excel文件
     * @param file
     * @return
     * @throws IOException
     */
    public  HashMap<String, ArrayList<String[]>> analysisFile(MultipartFile file) throws IOException {
        HashMap<String, ArrayList<String[]>> hashMap = new HashMap<>();
        //获取workbook对象
        Workbook workbook = null;
        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        //根据后缀名是否excel文件
        if(filename.endsWith("xls")){
            //2003
            workbook = new HSSFWorkbook(inputStream);
        }else if(filename.endsWith("xlsx")){
            //2007
            workbook = new XSSFWorkbook(inputStream);
        }

        //创建对象，把每一行作为一个String数组，所以数组存到集合中
        ArrayList<String[]> arrayList = new ArrayList<>();
        short fixedColumns=0;  //设置固定只读几列数据;

        if(workbook != null){
            //循环sheet,现在是单sheet
//            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
            for(int sheetNum = 0;sheetNum <1;sheetNum++){  //修改值读取第一个sheet 页中的内容
                //获取第一个sheet
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    hashMap.put("文件sheet为空!",arrayList);
                    return hashMap;
                }
                //获取当前sheet开始行和结束行
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                //循环开始，除了前两行
                for(int rowNum = firstRowNum ;rowNum <= lastRowNum;rowNum++){
                    //获取当前行
                    Row row = sheet.getRow(rowNum);
                    //获取当前行的开始列和结束列
                    short firstCellNum = row.getFirstCellNum();
                    short lastCellNum = row.getLastCellNum();

                    if(fixedColumns==0){
                        fixedColumns=lastCellNum;   //获取表头列数 从第二行 开始读取数据
                        continue;
                    }

                    //获取总行数
                    int lastCellNum2 = row.getPhysicalNumberOfCells();
                    String[] strings = new String[fixedColumns];

                    //循环当前行
                    for(int cellNum = firstCellNum;cellNum < fixedColumns;cellNum++){
                        Cell cell = row.getCell(cellNum);

                       /* if( cell == null || "".equals(cell) || cell.getCellType()== Cell.CELL_TYPE_BLANK ){
                            hashMap.put("第"+(rowNum+1)+"行,第"+(cellNum+1)+"列为空",arrayList);
                            return hashMap;
                        }*/

                        String  cellValue = "";
                        cellValue = getCellValue(cell);
                        strings[cellNum] = cellValue;
                    }
                    arrayList.add(strings);

                }
            }
        }
        inputStream.close();
        hashMap.put("OK",arrayList);
        return hashMap;
    }


    //把每一个cell转换为string
    public  String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字转换成string，防止12.0这种情况
        if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){
            cell.setCellType(cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字0
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串1
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());

                    try {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }

                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    /*截取 Hyperlink  超链接的值*/

    public static String getCellStringValue(Cell cell) {
		String cellValue = "";
		if (cell != null) {
			// cell.getCellTypeEnum(),获取单元格类型,case不同类型进行不同处理
			switch (cell.getCellTypeEnum()) {
			case _NONE: // 未知类型，用于表示初始化前的状态或缺少具体类型。仅供内部使用。
				break;
			case NUMERIC: // 数字类型 小数,整数,日期
				
				// 如果是数字类型的话,判断是不是日期类型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date d = cell.getDateCellValue();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					cellValue = formatter.format(d);
				} else if(cell.getCellStyle().getDataFormat() == 57) {
					Date d = cell.getDateCellValue();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
					cellValue = formatter.format(d);
				} else {
					DecimalFormat df = new DecimalFormat("0");  
					cellValue = df.format(cell.getNumericCellValue());  
				}
				
				break;
			case STRING: // 字符串类型
				cellValue = cell.getStringCellValue();
				break;
			case FORMULA: // 公式类型
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			case BLANK: // 空白的单元格
				break;
			case BOOLEAN: // 布尔类型
				break;
			case ERROR: // 错误类型
				break;
			default:
				break;
			}
		}
		return cellValue;
	}

    //判断row是否为空
    public  boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }

    //文件读取到指定的位置
    public String saveFile(MultipartFile file) throws IOException {
        MultipartFile update = file;
        //文件中参数名字
        String name = update.getName();
        //文件名字
        String originalFilename = update.getOriginalFilename();
        //是否为空
        boolean empty = update.isEmpty();
        //传输文件到指定路径中
        String path = "F://LDJS/boco/uploading/"+originalFilename;
        update.transferTo(new File(path));
        //文件类型
        String contentType = update.getContentType();
        InputStream inputStream = update.getInputStream();
        inputStream.close();
        //是否存在此路径
        boolean path1 = new File(path).exists();
        if(path1){
            return "OK";
        }else{
            return "导入文件失败";
        }

    }

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param titleArrary  excel 标题
     * @param dataList 数据列表
     * @param filePath 文件路径
     *  @param fileName 文件名
     * @throws Exception
     *
     */
    public void exportSXSSFExcel(String sheetName, String[] titleArrary, List<String[]> dataList, String filePath, HttpServletResponse response, String fileName){

        long startt=System.currentTimeMillis();
        System.out.println("==================excel write start !====================");

        // 内存中缓存记录数 建议10000 不然会导致内存溢出
//        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        Font titleFont = workbook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setFillBackgroundColor(IndexedColors.PALE_BLUE.index);

        int rowIndex = 0;
        Row row = sheet.createRow(rowIndex++);

        // 标题
        int cellSize = titleArrary.length;
        for (int i = 0; i < cellSize; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(titleCellStyle);
            cell.setCellValue(titleArrary[i]);
            if (titleArrary[i] != null) {
                if(i==0||i==9){
                    sheet.setColumnWidth(i, titleArrary[i].getBytes().length * 2 * 600);
                }else if(i==2||i==3||i==10){
                    sheet.setColumnWidth(i, titleArrary[i].getBytes().length * 2 * 300);
                }else{
                    sheet.setColumnWidth(i, titleArrary[i].getBytes().length * 2 * 180);
                }
            }
        }

        // 数据 样式
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 锁定单元格样式
        XSSFCellStyle lockedStyle= (XSSFCellStyle) workbook.createCellStyle();
        lockedStyle.setLocked(true);
        lockedStyle.setBorderBottom(BorderStyle.THIN);
        lockedStyle.setBorderLeft(BorderStyle.THIN);
        lockedStyle.setBorderRight(BorderStyle.THIN);
        lockedStyle.setBorderTop(BorderStyle.THIN);
        lockedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        lockedStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        //锁定 链接样式
        XSSFCellStyle lockedLinkStyle= (XSSFCellStyle) workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        //设置字体下划线
        font.setUnderline((byte) 1);
        //设置字体颜色
        font.setColor(HSSFColor.BLUE.index);
        //设置字体
        lockedLinkStyle.setFont(font);

        lockedLinkStyle.setLocked(true);
        lockedLinkStyle.setBorderBottom(BorderStyle.THIN);
        lockedLinkStyle.setBorderLeft(BorderStyle.THIN);
        lockedLinkStyle.setBorderRight(BorderStyle.THIN);
        lockedLinkStyle.setBorderTop(BorderStyle.THIN);
        lockedLinkStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedLinkStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        lockedLinkStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        // 非锁定链接样式
        XSSFCellStyle linkStyle = (XSSFCellStyle) workbook.createCellStyle();
        linkStyle.setFont(font);
        linkStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        linkStyle.setLocked(false);
//        linkStyle.setBorderBottom(BorderStyle.THIN);
//        linkStyle.setBorderLeft(BorderStyle.THIN);
//        linkStyle.setBorderRight(BorderStyle.THIN);
//        linkStyle.setBorderTop(BorderStyle.THIN);

        XSSFCellStyle unLockedStyle = (XSSFCellStyle) workbook.createCellStyle();
        unLockedStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        unLockedStyle.setLocked(false);

        CreationHelper createHelper = workbook.getCreationHelper();
        for (String[] data : dataList) {
            row = sheet.createRow(rowIndex);
            // 遍历列
            for (int i = 0; i < data.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(dataCellStyle);
                cell.setCellValue(data[i]);

                /*0 ID,*/
                /*1 产品/平台版本,*/
                /*2 使用位置, 父节点 名称*/
                /*3 软件名称,*/
                if(i==0||i==1||i==2||i==3){
                    cell.setCellValue(data[i]);
                    cell.setCellStyle(lockedStyle);
                }

                /*4 软件版本,*/
                if(i==4&&!utils.isStrNull(data[i])){
                    String [] urlAndVul=data[i].split("#@#");

                    //设置超链接
                    Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
                    link.setAddress(urlAndVul[1]);
                    link.setLabel(data[0]);
                    cell.setHyperlink(link);

                    cell.setCellStyle(lockedLinkStyle);
                    cell.setCellValue(urlAndVul[0]);
                }else if(i==5){  /*5    PDM编码,*/
                    cell.setCellValue(data[i]);
                    cell.setCellStyle(lockedStyle);
                }else if(i==6){  /*6    漏洞CVE编码,*/

                    Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
                    link.setAddress(BASE_CVE_URL+data[i]);
                    link.setLabel(data[i]);
                    cell.setHyperlink(link);

                    cell.setCellStyle(lockedLinkStyle);
                    cell.setCellValue(data[i]);
                }else if(i==7){  /*7    评分,*/
                    cell.setCellStyle(lockedStyle);
                    cell.setCellValue(data[i]);
                }else if(i==8){  /*8    漏洞修复状态,*/
                    cell.setCellValue(data[i]);
                    cell.setCellStyle(unLockedStyle);
                }else if(i==9) { /*9    DTS单号,*/
                    if(!utils.isStrNull(data[i])){
                        //设置超链接
                        Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
                        link.setAddress(BASE_DTS_URL+data[i]);
                        link.setLabel(data[i]);
                        cell.setHyperlink(link);

                        cell.setCellStyle(linkStyle);
                        cell.setCellValue(data[i]);

                    }else{
                        cell.setCellStyle(unLockedStyle);
                        cell.setCellValue(data[i]);
                    }

                }else if(i==10){ /*10   修复版本*/
                    cell.setCellValue(data[i]);
                    cell.setCellStyle(unLockedStyle);
                }else if(i==11||i==12){   /*11  pbi_id,*//*12   nodePbiId*/
                    cell.setCellStyle(lockedStyle);
                    cell.setCellValue(data[i]);
                }

            }
            rowIndex++;
        }

        sheet.autoSizeColumn(2);sheet.autoSizeColumn(10);

        sheet.setColumnHidden(11,true);
        sheet.setColumnHidden(12,true);

        XSSFSheet sheet1= (XSSFSheet) sheet;
        sheet.protectSheet("abcdefg");
        ((XSSFSheet) sheet1).enableLocking();

        CTSheetProtection sheetProtection = sheet1.getCTWorksheet().getSheetProtection();
        sheetProtection.setSelectLockedCells(false);
        sheetProtection.setSelectUnlockedCells(false);
        sheetProtection.setFormatCells(true);
        sheetProtection.setFormatColumns(true);
        sheetProtection.setFormatRows(true);
        sheetProtection.setInsertColumns(true);
        sheetProtection.setInsertRows(false);
        sheetProtection.setInsertHyperlinks(true);
        sheetProtection.setDeleteColumns(true);
        sheetProtection.setDeleteRows(true);
        sheetProtection.setSort(false);
        sheetProtection.setAutoFilter(false);
        sheetProtection.setPivotTables(true);
        sheetProtection.setObjects(true);
        sheetProtection.setScenarios(true);

        sheet.setAutoFilter(CellRangeAddress.valueOf("A1:K1"));

        /* 下拉框 */
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(new String[]{"Unaffected", "Risk-Accepted", "Unfixed","Fixed"});
        CellRangeAddressList addressList = new CellRangeAddressList(1, dataList.size(), 8, 8);
        XSSFDataValidation validation = (XSSFDataValidation)dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        sheet.addValidationData(validation);

        if (!fileName.endsWith(".xlsx")) {
            fileName += ".xlsx";
        }

        try {
            if(StringUtils.isNotEmpty(filePath)){
                File saveFile = new File(filePath);
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                saveFile.createNewFile();

                OutputStream outputStream = new FileOutputStream(saveFile);
                workbook.write(outputStream);
                outputStream.close();
            }
//                if(response!=null){
//                    response.setContentType("application/vnd.ms-excel;charset=utf-8");
//                    fileName=URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
//                    fileName = response.encodeURL(new String(fileName.getBytes("utf-8"), "iso8859-1"));
//                    response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//                    OutputStream outputStream =response.getOutputStream();
//                    workbook.write(outputStream);
//                    outputStream.close();
//                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("excel 写入耗时： "+(System.currentTimeMillis()-startt)+"  毫秒");

    }

}
