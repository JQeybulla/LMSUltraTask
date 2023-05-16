package utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;


public class Helper {
    Log logger = LogFactory.getLog(getClass());

    private HttpServletRequest request;

    public Helper() {

    }

    public Helper(HttpServletRequest request) {
        this.request = request;
    }


    public static String toMD5(String pass) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] data = pass.getBytes();
        m.update(data, 0, data.length);
        BigInteger i = new BigInteger(1, m.digest());
        return String.format("%1$032X", i);
    }


    public Properties getProperties(String type) {
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(request.getServletContext().getInitParameter(type));
        try {
            props.load(inputStream);
        } catch (IOException e) {
            logger.info("getPropertes" + e.toString());
        }
        return props;
    }

    public static void downloadFile(HttpServletResponse response, File file) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public static void download(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        //FileInputStream in = new FileInputStream(file);
        /*byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0 ){
            out.write(buffer, 0, length);
        }*/

        out.write(bytes);
        //in.close();
        out.flush();
        out.close();
    }

    public static void downloadFileTestInline(HttpServletResponse response, File file) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "inline; filename=\"" + file.getName() + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public static void downloadFile(HttpServletResponse response, File file, String responseFileName) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "inline; filename=\"" + responseFileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public static void download(HttpServletResponse response, ByteArrayOutputStream outputStream, String responceFileName) throws IOException {
        response.setHeader("Expires", "0");
        response.setHeader("charset", "=UTF-8");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Content-disposition", "inline; filename=\"" + responceFileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Pragma", "public");
        OutputStream out = response.getOutputStream();
        out.write(outputStream.toByteArray());
        out.flush();
    }

    public static String UpperCaseStr(String str) {
        String res = "";
        String str1 = "";
        String str2 = "";
        str1 = str.replace("i", "@#");
        str2 = str1.toUpperCase();
        res = str2.replace("@#", "İ");
        return res;
    }


}