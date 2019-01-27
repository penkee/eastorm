package com.eastorm.core.base.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.util.Base64;

public class FileUtils {
	private static Logger logger=Logger.getLogger(FileUtils.class);
	
	public static void upload(final String url,MultipartFile file) throws IllegalStateException, IOException{
    	if(file==null||file.getSize()<=0)return;
		//图片
    	file.transferTo(new File(url));
					logger.info("正在压缩1："+url);
					ImgCompress imgCom = new ImgCompress(url);  
					imgCom.resizeFix();
	}
	
	 /**
     * base64字符串转文件
     * @param base64
     * @return
     */
    public static String base64ToFile(String base64,String baseUrl) {
        File file = null;
        
        String fm_showPicBase= DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")+StringFunc.getRandomStr(5);
		String saveUrlTemp="%sbizImages"+File.separator+"product"+File.separator+"%s.jpg";
		
		String fileName = String.format(saveUrlTemp, baseUrl,fm_showPicBase);
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            file = new File(fileName);
            if (!file.exists())
                file.createNewFile();
            byte[] bytes = Base64.decodeFast(base64);// 将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (out!= null) {
                    out.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return String.format(saveUrlTemp, "",fm_showPicBase);
    }
}
