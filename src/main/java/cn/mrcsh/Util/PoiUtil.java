package cn.mrcsh.Util;

import cn.mrcsh.Annotations.ExcelFieldName;
import cn.mrcsh.Entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class PoiUtil {

    private PoiUtil(){}

    /**
     * 往客户端推送表格
     * @param response response对象
     * @param fileName 文件名称
     * @param projects 数据集合
     * @param columnNames 对应exile列名
     * @param keys 对应实体类字段名
     */
    @CrossOrigin
    public static void start_download(HttpServletResponse response, String fileName, List<?> projects,
                                      String[] columnNames, String[] keys) throws IOException {

        //将集合中对象的属性  对应到  List<Map<String,Object>>
        List<Map<String,Object>> list=createExcelRecord(projects, keys);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            //将转换成的Workbook对象通过流形式下载
            createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        response.setHeader("Access-Control-Allow-Origin","*");
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    private static List<Map<String, Object>> createExcelRecord(List<?> projects, String[] keys) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet");
        listmap.add(map);
        Object project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            for(int i=0; i<keys.length; i++){
                mapValue.put(keys[i], getFieldValue(keys[i], project));
            }

            listmap.add(mapValue);
        }
        return listmap;
    }

    /**
     * 利用反射  根据属性名获取属性值
     * @param fieldName 列名
     * @param o 对象
     * @return 值
     */
    private static Object getFieldValue(String fieldName,Object o){
        return ReflectUtil.getFieldValue(o, fieldName);
    }
    /**
     * 创建excel文档对象
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    private static Workbook createWorkBook(List<Map<String, Object>> list, String[] keys, String[] columnNames) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    @SneakyThrows
    @CrossOrigin
    public static <T> void export_Excel(HttpServletResponse response, BaseMapper<T> mapper,Class<T> clazz) {
        List<String> FieldNames = new ArrayList<>();
        List<String> ExcelFieldNames = new ArrayList<>();
        ReflectUtil.forEachFields(clazz, (e) -> {
            FieldNames.add(e.getName());
            ExcelFieldNames.add(e.getAnnotation(ExcelFieldName.class).value());
        });
        List<T> list = mapper.selectList(null);
        String[] Entity_FieldNames = FieldNames.toArray(new String[]{});
        String[] Excel_FieldNames = ExcelFieldNames.toArray(new String[]{});
        PoiUtil.start_download(response, UUID.randomUUID().toString(), list, Excel_FieldNames, Entity_FieldNames);
    }

}
