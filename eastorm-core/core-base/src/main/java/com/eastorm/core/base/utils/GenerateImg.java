package com.eastorm.core.base.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class GenerateImg {
     BufferedImage buffImg=null;
     Graphics2D g=null;
     private int width;
     private int height;
     public GenerateImg(int  width,int height){
    	 buffImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    	 this.width=width;
    	 this.height=height;
     }
     public GenerateImg(){
    	 this(32,32);
     }
     /**
      * 
      * @param text �ı�
      * @param ext ��չ��
     * @throws IOException 
      */
     public void drawText(String text,String ext,ServletOutputStream  writer) throws IOException{
    	 g=buffImg.createGraphics();
    	 //���ñ���ɫ
    	 g.setColor(getRandColor(200,250)); 
         g.fillRect(0, 0, width, height); 
    	
         g.setColor(getRandColor(10,160)); 
         Font mFont = new Font("Times New Roman", Font.BOLD, 20);// ��������  
         g.setFont(mFont);  
    	 g.drawString(text, 6, 19);
    	 
    	 g.setColor(getRandColor(10,250)); 
    	 // ��������  
         Random random = new Random();  
         for (int i = 0; i < 10; i++) {  
             int x2 = random.nextInt(width);  
             int y2 = random.nextInt(height);  
             int x3 = random.nextInt(18);  
             int y3 = random.nextInt(18);  
             g.drawLine(x2, y2, x2 + x3, y2 + y3);  
         }  
         
    	 g.dispose();  
    	 ImageIO.write(buffImg, ext, writer);
    	 writer.close();
     }
     private Color getRandColor(int fc, int bc) { 
         Random random = new Random();  
         if (fc > 255)  
             fc = 255;  
         if (bc > 255)  
             bc = 255;  
         int r = fc + random.nextInt(bc - fc);  
         int g = fc + random.nextInt(bc - fc);  
         int b = fc + random.nextInt(bc - fc);  
         return new Color(r, g, b);  
     } 
}
