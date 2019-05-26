package com.example.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

//@Component(value = "POIExcelUtils")
public class POIExcelUtils {
    /**Excel文档对象*/
    private HSSFWorkbook wb;
    /**sheet页*/
    private HSSFSheet st;
    /**表格字体格式设置*/
    private HSSFFont tabTitleFont;
    /**表格样式*/
    private HSSFCellStyle tabTitleStyle;
    /**表格标题行*/
    private HSSFRow tableTitlerow;
    /**表格标题单元格*/
    private HSSFCell tableCell;
    /**单元格的空间位置*/
    private CellRangeAddress cellRangeAddress;
    /**表头样式*/
    private HSSFCellStyle tabHeadStyle;
    /**表格字体格式设置*/
    private HSSFFont tabHeadFont;
    /**表头行*/
    private HSSFRow tableHeadRow;
    /**工作薄自适应宽度倍数因子*/
    private final double SHEETWIDTHMULTIPLEFACTOR = 1.1211;
    /**表格数据样式*/
    private HSSFCellStyle tabDataStyle;
    /**Excel文档对象*/
    private WritableWorkbook wbk;
    /**文件*/
    private File file;
    /**工作簿*/
    private WritableSheet ws;
    /**
     * @functionName POIExcelUtils
     * @description 构造器,初始化一些有用的类
     * @author yzh
     * @param sheetName sheet页的名字
     * @param tableTitle 表格标题名
     * @param headTableColumnsNameArr 表头列名数组
     * @param queryList 查询list
     * @param columnNameArr 字段名数组
     * @date 2018-10-14
     */
    public <T>POIExcelUtils(String sheetName,String tableTitle,String[] headTableColumnsNameArr,List<T> queryList,String[] columnNameArr){
        wb = new HSSFWorkbook();
        /**创建一个sheet页*/
        st = wb.createSheet(sheetName);
        /**表格标题样式**/
        tabTitleStyle = wb.createCellStyle();
        /**表格字体格式*/
        tabTitleFont = wb.createFont();
        /**表格单元格的空间位置*/
        if(null != headTableColumnsNameArr && headTableColumnsNameArr.length > 0){
            //表格标题合并的列数
            int tableTitileMergeRows = headTableColumnsNameArr.length;
            cellRangeAddress = new CellRangeAddress(0, 0, 0, tableTitileMergeRows-1);
        }else{
            cellRangeAddress = new CellRangeAddress(0, 0, 0, 0);
        }
        /**表头样式*/
        tabHeadStyle = wb.createCellStyle();
        /**表头字体格式*/
        tabHeadFont = wb.createFont();
        //设置表头为sheet页的第二行
        tableHeadRow = st.createRow(1);
        /**表数据样式*/
        tabDataStyle = wb.createCellStyle();

        /*设置sheet页的样式和表格标题、定义表头等基本信息*/
        setExcelSheetStyleAndBaseInfo(tableTitle,headTableColumnsNameArr,queryList,columnNameArr);
    }

    /**
     * @functionName setExcelSheetStyleAndBaseInfo
     * @description 设置sheet页的样式和表格标题、定义表头等基本信息
     * @author yzh
     * @param tableTitle 表格标题
     * @param headTableColumnsNameArr 表头列名数组
     * @param queryList 查询list
     * @param columnNameArr 字段名数组
     * @date 2018-10-14
     */
    public <T>void setExcelSheetStyleAndBaseInfo(String tableTitle,String[] headTableColumnsNameArr,List<T> queryList,String[] columnNameArr){
        /**1、设置表格标题格式*/
        HSSFCellStyle rnTabTitleStyle = setCellBorderStyle(tabTitleStyle);
        //设置字体
        tabTitleFont.setFontName("黑体");
        //设置字体大小
        tabTitleFont.setFontHeightInPoints((short) 16);
        //将表格标题字体加入到表格标题样式中
        rnTabTitleStyle.setFont(tabTitleFont);
        //设置表格标题并为表格标题单元格设置样式
        //setExcelSheetTabTitle(tableTitle,rnTabTitleStyle);

        /**2、设置表头格式及基本信息*/
        HSSFCellStyle rnTabHeadStyle = setCellBorderStyle(tabHeadStyle);
        //设置字体
        tabHeadFont.setFontName("黑体");
        //设置字体大小
        tabHeadFont.setFontHeightInPoints((short) 14);
        //将表格标题字体加入到表头样式中
        rnTabHeadStyle.setFont(tabHeadFont);
        //设置表头列名
        setHeadTableColumns(headTableColumnsNameArr,rnTabHeadStyle);

        /**3、设置 表格数据单元格样式基本信息*/
        HSSFCellStyle rnTabDataStyle = setCellBorderStyle(tabDataStyle);
        //创建一个DataFormat对象
        HSSFDataFormat format = wb.createDataFormat();
        //设置为文本格式(可以防止单元格文本太长而溢出)
        rnTabDataStyle.setDataFormat(format.getFormat("@"));
        //设置表格数据单元格样式和内容
        setTabDataCellStyleAndContent(queryList,columnNameArr,rnTabDataStyle);

        //*初始化工作薄自适应宽度
        setSheetWidthSelfAdaption(headTableColumnsNameArr);
    }

    /**
     * @functionName setTabDataCellStyleAndContent
     * @description 设置表格数据单元格样式和内容
     * @author yzh
     * @param list 查询list
     * @param columnNameArr 字段数组
     * @param rnTabDataStyle 表格数据样式
     * @date 2018-10-14
     */
    public <T> void setTabDataCellStyleAndContent(List<T> list,String[] columnNameArr,HSSFCellStyle rnTabDataStyle){
        if(!isEmpty(columnNameArr) && columnNameArr.length > 0){
            //表格数据从第三行开始
            int dataRow = 2;
            HSSFRow tableDataRow = null;
            //遍历出每一个对象
            for(T t: list){
                tableDataRow = st.createRow(dataRow);
                //Field[] fArr = t.getClass().getDeclaredFields();
                Field f = null;
                HSSFCell dataCell = null;
                //防止最后一列溢出,所以这里需要比字段数目多一列
                for(int i = 0;i<columnNameArr.length+1;i++){
                    try {
                        if(i<columnNameArr.length){
                            //获得私有属性域
                            f = t.getClass().getDeclaredField(columnNameArr[i]);
                            //设置私有属性可操作
                            f.setAccessible(true);
                            //获取字段的值
                            String value = f.get(t).toString();
                            dataCell = tableDataRow.createCell(i);
                            //设置单元格数据
                            dataCell.setCellValue(value);
                            //设置单元格格式
                            dataCell.setCellStyle(rnTabDataStyle);
                        }else{
                            dataCell = tableDataRow.createCell(i);
                            //设置单元格数据
                            dataCell.setCellValue("");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                dataRow++;
            }

        }else{
            return;
        }
    }

    /**
     * @functionName setHeadTableColumns
     * @description 设置表头列名
     * @author yzh
     * @param headTableColumnsNameArr 表头列名数组
     * @param rnTabHeadStyle 表头样式
     * @date 2018-10-14
     */
    private void setHeadTableColumns(String[] headTableColumnsNameArr,HSSFCellStyle rnTabHeadStyle){
        if(null != headTableColumnsNameArr && headTableColumnsNameArr.length > 0){
            for(int i = 0;i < headTableColumnsNameArr.length;i++){
                //表头第一列所在的单元格
                HSSFCell headTableCell = tableHeadRow.createCell(i);
                //设置表头第一列所在的单元格的值
                headTableCell.setCellValue(headTableColumnsNameArr[i]);
                //设置表头单元格样式
                headTableCell.setCellStyle(rnTabHeadStyle);
            }
            return;
        }else{
            return;
        }
    }

    /**
     * @functionName setCellBorderStyle
     * @description 设置单元格边框
     * @author yzh
     * @param cellStyle HSSFCellStyle对象
     * @date 2018-10-14
     */
    @SuppressWarnings("deprecation")
    private HSSFCellStyle setCellBorderStyle(HSSFCellStyle cellStyle){
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //下边框
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //左边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //上边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //右边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置内容水平居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置内容垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return cellStyle;
    }

    /**
     * @functionName setExcelSheetTabTitle
     * @description 设置表格标题并为表格标题单元格设置样式
     * @author yzh
     * @param tabTitle 表格标题
     * @param rnTabTitleStyle 表格标题样式
     * @date 2018-10-14
     */
    private void setExcelSheetTabTitle(String tabTitle,HSSFCellStyle rnTabTitleStyle){
        //起始行，结束行，起始列，终止列
        st.addMergedRegion(cellRangeAddress);
        //将表格标题设置在第一行（0）
        tableTitlerow = st.createRow(0);
        //选中合并后的单元格作为表格标题信息存储的那个单元格
        tableCell = tableTitlerow.createCell(0);
        //设置表格标题内容
        tableCell.setCellValue(tabTitle);
        //为表格单元格设置样式
        tableCell.setCellStyle(rnTabTitleStyle);
    }

    /**
     * @functionName setSheetWidthSelfAdaption
     * @description 不定表格列的工作簿的宽度自适应
     * @author yzh
     * @param headTableColumnsNameArr 表头列名数组
     * @date 2018-10-14
     */
    private void setSheetWidthSelfAdaption(String[] headTableColumnsNameArr){
        if(null != headTableColumnsNameArr && headTableColumnsNameArr.length > 0){
            //获取表头列数
            int headTableColumns = headTableColumnsNameArr.length;
            //工作薄单元格总宽度
            int sumSheetColumnWidth = 0;
            //获取Excel空白文档默认的列数
            int sheetDefaultColumnNums = 21;//st.getLastRowNum();
            //获取Excel空白文档默认的列宽，由于这里获取的单字符的长度，因此需要*256，以便与getColumnWidth的单位保持一致
            int sheetDefaultWidth = st.getDefaultColumnWidth()*256;
            //获取Excel空白文档默认的总宽宽
            double totalSheetWidth = sheetDefaultColumnNums*sheetDefaultWidth;
            //工作薄单元格总宽度
            for (int m = 0; m < headTableColumns; m++) {
                //设置自适应列宽
                st.autoSizeColumn((short)m);
                //获取自适应列宽后的列宽
                //getColumnWidth(int columnIndex)get the width (in units of 1/256th of a character width )以一个字符的1/256的字母宽度作为一个单位
                sumSheetColumnWidth += st.getColumnWidth(m);
            }
            //获取sheet页默认宽度相对于数据部分宽度的倍数
            double rate = totalSheetWidth*1.0/sumSheetColumnWidth;
            //重置宽度
            for (int m = 0; m < headTableColumns; m++) {
                //重置宽度
                st.setColumnWidth(m,(int) (st.getColumnWidth(m)*rate*SHEETWIDTHMULTIPLEFACTOR)+200);
            }
            return;
        }else{
            return;
        }
    }

    /**
     * @functionName setSheetWidthSelfAdaption
     * @description 不定表格列的工作簿宽度自适应（可自定义宽度倍数因子）
     * @author yzh
     * @param headTableColumnsNameArr 表头列名数组
     * @param tableWidthMultipleFactor 工作薄自适应宽度倍数因子
     * @date 2018-10-14
     */
    public void setSheetWidthSelfAdaption(String[] headTableColumnsNameArr,int tableWidthMultipleFactor){
        if(null != headTableColumnsNameArr && headTableColumnsNameArr.length > 0){
            //获取表头列数
            int headTableColumns = headTableColumnsNameArr.length;
            //工作薄单元格总宽度
            int sumSheetColumnWidth = 0;
            //获取Excel空白文档默认的列数
            int sheetDefaultColumnNums = st.getLastRowNum();
            //获取Excel空白文档默认的列宽，由于这里获取的单字符的长度，因此需要*256，以便与getColumnWidth的单位保持一致
            int sheetDefaultWidth = st.getDefaultColumnWidth()*256;
            //获取Excel空白文档默认的总宽宽
            double totalSheetWidth = sheetDefaultColumnNums*sheetDefaultWidth;
            //工作薄单元格总宽度
            for (int m = 0; m < headTableColumns; m++) {
                //设置自适应列宽
                st.autoSizeColumn((short)m);
                //获取自适应列宽后的列宽
                //getColumnWidth(int columnIndex)get the width (in units of 1/256th of a character width )以一个字符的1/256的字母宽度作为一个单位
                sumSheetColumnWidth += st.getColumnWidth(m);
            }
            //获取sheet页默认宽度相对于数据部分宽度的倍数
            double rate = totalSheetWidth*1.0/sumSheetColumnWidth;
            //重置宽度
            for (int m = 0; m < headTableColumns; m++) {
                //重置宽度,如果工作薄自适应宽度倍数因子>1,则启用手工设置的因子
                if(tableWidthMultipleFactor > 1){
                    st.setColumnWidth(m,(int) (st.getColumnWidth(m)*rate*tableWidthMultipleFactor)+200);
                    //否则启用系统默认初始化因子
                }else{
                    st.setColumnWidth(m,(int) (st.getColumnWidth(m)*rate*SHEETWIDTHMULTIPLEFACTOR)+200);
                }
            }
            return;
        }else{
            return;
        }
    }

    /**
     * @functionName excelDataExport
     * @description 导出Excel文档
     * @author yzh
     * @param exportFilePath 导出文件路径
     * @date 2018-10-14
     */
    public void excelDataExport(String exportFilePath){
        File file = new File(exportFilePath);
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            //调用write方法导出xls文件
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null != out){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @functionName isEmpty
     * @description 判断对象是否为空
     * @author yzh
     * @param obj
     * @date 2018-10-14
     */
    public boolean isEmpty(Object obj){
        boolean flag = true;
        if(null != obj && !"".equals(obj)){
            flag = false;
        }
        return flag;
    }


    /**
     * @functionName POIExcelUtils
     * @description 构造器,初始化一些有用的类,支持多个sheet同时导出
     * @author yzh
     * @param filePath 文件导出名
     * @param sheetName sheet页的名字数组
     * @param tablesColumnArr 表格字段数组
     * @param list 查询list(元素为list)
     * @date 2018-10-14
     */
    @SuppressWarnings("unchecked")
    public <T>POIExcelUtils(String filePath,String[] sheetName, String[] tablesColumnArr,List<Object> list){
        file = new File(filePath);
        try {
            wbk = Workbook.createWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //工作簿的名字以及工作薄的位置
        for(int i = 0;i<sheetName.length;i++){
            ws = wbk.createSheet(sheetName[i], i);
            //为每一个sheet页添加标题和内容
            setSheetTableContent(tablesColumnArr[i],ws,(List<T>) list.get(i));
        }
        //Excel操作完毕之后，关闭所有的操作资源
        try {
            //从内存中写入文件中
            wbk.write();
            //关闭资源，释放内存
            wbk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @functionName setSheetTableContent
     * @description 设置sheet表格样式及内容
     * @author yzh
     * @param tableColumns 表头列拼接字符串
     * @param ws sheet对象
     * @param list 查询list(元素为list)
     * @date 2018-10-14
     */
    private <T>void setSheetTableContent(String tableColumns,WritableSheet ws,List<T> list){
        if(!isEmpty(tableColumns)){
            String[] tableColumnsArr = tableColumns.split(",");
            for(int i = 0;i<tableColumnsArr.length;i++){
                try{
                    WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
                    WritableCellFormat wcfFC = new WritableCellFormat(wfc);
                    wcfFC.setBackground(Colour.GRAY_25);
                    wcfFC.setAlignment(Alignment.CENTRE);
                    Label label = new Label(i, 0,tableColumnsArr[i],wcfFC);
                    ws.setColumnView(i,20); //设置列宽
                    ws.addCell(label);  //添加标题
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            Field[] fArr = null;
            T t = null;
            //下面开始添加单元格
            for(int m=0;m<list.size();m++){
                t = list.get(m);
                //获得私有属性域
                fArr = t.getClass().getDeclaredFields();
                //防止最后一列溢出,所以这里需要比字段数目多一列
                for(int j = 0;j<fArr.length+1;j++){
                    if(j<fArr.length){
                        try{
                            Field f = fArr[j];
                            //设置私有属性可操作
                            f.setAccessible(true);
                            //获取字段的值
                            String value = f.get(t).toString();
                            //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
                            Label labelC = new Label(j, m+1, value);
                            //将生成的单元格添加到工作表中
                            ws.addCell(labelC);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        Label labelC = new Label(j, m+1,"");
                        //将生成的单元格添加到工作表中
                        try {
                            ws.addCell(labelC);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * @functionName POIExcelUtils
     * @description 构造器,初始化一些有用的类,同时导出多个sheet页
     * @author yzh
     * @param sheetNameArr sheet页的名字数组
     * @param tableTitleArr 表格标题名数组
     * @param headTablesColumnsNameArr 多个表头列名数组
     * @param queryList 查询list的集合
     * @param tablesColumnNameArr 字段名拼接串的的数组
     * @date 2018-10-14
     */
    @SuppressWarnings("unchecked")
    public <T>POIExcelUtils(String[] sheetNameArr,String[] tableTitleArr,String[] headTablesColumnsNameArr,List<Object> queryList,String[] tablesColumnNameArr){
        wb = new HSSFWorkbook();
        if(!isEmpty(sheetNameArr) && sheetNameArr.length>0 && !isEmpty(tableTitleArr) && tableTitleArr.length>0){
            for(int i = 0;i<sheetNameArr.length;i++){
                /**创建一个sheet页*/
                st = wb.createSheet(sheetNameArr[i]);
                /**表格标题样式**/
                tabTitleStyle = wb.createCellStyle();
                /**表格字体格式*/
                tabTitleFont = wb.createFont();
                if(!isEmpty(headTablesColumnsNameArr) && headTablesColumnsNameArr.length>0 && !isEmpty(tablesColumnNameArr) && tablesColumnNameArr.length>0){
                    String[] headTableColumnsNameArr = headTablesColumnsNameArr[i].split(",");
                    /**表格单元格的空间位置*/
                    if(null != headTableColumnsNameArr && headTableColumnsNameArr.length > 0){
                        //表格标题合并的列数
                        int tableTitileMergeRows = headTableColumnsNameArr.length;
                        cellRangeAddress = new CellRangeAddress(0, 0, 0, tableTitileMergeRows-1);
                    }else{
                        cellRangeAddress = new CellRangeAddress(0, 0, 0, 0);
                    }
                    /**表头样式*/
                    tabHeadStyle = wb.createCellStyle();
                    /**表头字体格式*/
                    tabHeadFont = wb.createFont();
                    //设置表头为sheet页的第二行
                    tableHeadRow = st.createRow(1);
                    /**表数据样式*/
                    tabDataStyle = wb.createCellStyle();
                    //获取每个sheet页的列名
                    String[] tableColumnNameArr = tablesColumnNameArr[i].split(",");
                    /**设置sheet页的样式和表格标题、定义表头等基本信息*/
                    setExcelSheetStyleAndBaseInfo(tableTitleArr[i],headTableColumnsNameArr,(List<T>)queryList.get(i),tableColumnNameArr);
                }
            }
        }
    }


}
