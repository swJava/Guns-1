package com.stylefeng.guns.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/23 08:11
 * @Version 1.0
 */
public class ExcelUtil {

    public static void addCells(Row header, List<String> headers, XSSFCellStyle headerStyle) {
        for(String headerText : headers){
            addCell(header, headerText, headerStyle);
        }
    }

    public static void addCell(Row row, Object value, XSSFCellStyle cellStyle) {
        Cell cell = null;
        int currColumnNum = row.getLastCellNum();

        if (currColumnNum < 0)
            currColumnNum = 0;

        if (value instanceof Integer
                || value instanceof Long
                || value instanceof Double){
            cell = row.createCell(currColumnNum, Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(Double.parseDouble(String.valueOf(value)));
        }else if (value instanceof String){
            cell = row.createCell(currColumnNum, Cell.CELL_TYPE_STRING);
            cell.setCellValue((String)value);
        }
        if (null != cell)
            cell.setCellStyle(cellStyle);
    }

    public static int getVersion(InputStream stream) {
        if (null == stream)
            return 0;

        try {
            if (POIFSFileSystem.hasPOIFSHeader(stream)) {
                return 3;
            }
            if (POIXMLDocument.hasOOXMLHeader(stream)) {
                return 7;
            }
        }catch(Exception e){
        }

        return 0;
    }

    /**
     * 读取批量数据文件
     * @param inputStream
     * @return
     */
    public static Map<Integer, List<String>> readData(InputStream inputStream) {
        return null;
    }
}
