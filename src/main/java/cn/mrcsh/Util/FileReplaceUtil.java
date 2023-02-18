package cn.mrcsh.Util;

import lombok.SneakyThrows;

import java.io.*;

public class FileReplaceUtil {
    @SneakyThrows
    public static File ReplaceAll(String regex,String replacement,File file){
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuffer buffer = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null){
            buffer.append(str+"\n");
        }
        String buffers = buffer.toString().replaceAll(regex, replacement);
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(buffers.getBytes());
        stream.flush();
        return file;
    }
}
