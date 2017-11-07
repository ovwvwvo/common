/*
 * *
 *  * Created by rawer.
 *
 */

package com.ovwvwvo.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

/**
 * 文件工具类
 */
@SuppressLint("NewApi")
public class FileUtil {

    /**
     * 设置缓存文件夹位置
     *
     * @param fileName 文件名
     * @return /sdcard/Android/data/application package/cache或/data/data/application package/cache
     */
    @SuppressLint("NewApi")
    public static File getDiskCacheDir(Context context, String fileName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
            .getExternalStorageState())
            || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + fileName);
    }

    /**
     * 从路径获取文件名
     */
    public static String getFileName(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }
    }

    /**
     * 将文件存储到指定的路径下
     *
     * @param content  存储的内容
     * @param filePath 存储路径
     */
    public static void saveFile(String content, String filePath) {
        saveFile(content, filePath, false);
    }

    /**
     * 将文件存储到指定的路径下
     *
     * @param content  存储的内容
     * @param filePath 存储路径
     * @param bo       是否对文件进行续写
     */
    public static void saveFile(String content, String filePath, boolean bo) {
        if (filePath == null || filePath.equals("")) {
            return;
        } else {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists() && file.length() > 0) {
                file.delete();
            }
            try {
                FileWriter writer = new FileWriter(file, bo);
                writer.write(content);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param filePath 文件路径
     * @return 返回的字符串
     */
    public static String readFile(String filePath) {
        StringBuffer stringBuffer = new StringBuffer();
        if (filePath == null || filePath == "") {
            return null;
        } else {
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            try {
                FileReader reader = new FileReader(file);
                char c[] = new char[1024];
                int count = 0;
                while ((count = reader.read(c)) != -1) {
                    stringBuffer.append(c, 0, count);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 存储文件到SDcard
     *
     * @param url      图片地址
     * @param filePath sdcard的存储地址
     */
    public static void saveFileToSDcard(String url, String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists() && file.length() > 0) {
            file.delete();
        }
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream(filePath);
            is = new URL(url).openStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件的MD5
     */
    public static String getFileMD5(File file) {
        if (!file.isFile())
            return null;
        FileInputStream fis = null;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 通过路径生成Base64文件
     */
    public static String getBase64FromPath(String path) {
        String base64 = "";
        try {
            File file = new File(path);
            if (!file.exists()) {
                return null;
            }
            byte[] buffer = new byte[(int) file.length() + 100];
            @SuppressWarnings("resource")
            int length = new FileInputStream(file).read(buffer);
            base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

}
