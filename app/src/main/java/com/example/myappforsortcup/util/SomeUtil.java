package com.example.myappforsortcup.util;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by 蒲公英之流 on 2019-05-21.
 */

public class SomeUtil {

    public static String RandomDoubleString(String s, int seed){
        int length = new Random().nextInt(seed);
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < length;i++){
            builder.append(s);
        }
        return builder.toString();
    }

    public static boolean shakeControl(View view){
        if (view == null){
            return false;
        }
        YoYo.with(Techniques.Shake)
                .duration(70)
                .repeat(9)
                .playOn(view);
        return true;
    }

    public static void textTransformPdf(String content,String pdf_save_address){
        Document doc = new Document();// 创建一个document对象
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pdf_save_address); // pdf_address为Pdf文件保存到sd卡的路径
            PdfWriter.getInstance(doc, fos);
            doc.open();
            doc.setPageCount(1);
            doc.add(new Paragraph(content, setChineseFont())); // result为保存的字符串
            // ,setChineseFont()为pdf字体
            // 一定要记得关闭document对象
            doc.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font setChineseFont() {
        BaseFont bf = null;
        Font fontChinese = null;
        try {
            // STSong-Light : Adobe的字体
            // UniGB-UCS2-H : pdf 字体
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            fontChinese = new Font(bf, 12, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;
    }

    public static String getTimeForName(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ssmmhhddMMyyyy");
        String dateString = simpleDateFormat.format(new Date());
        return dateString;
    }

}
