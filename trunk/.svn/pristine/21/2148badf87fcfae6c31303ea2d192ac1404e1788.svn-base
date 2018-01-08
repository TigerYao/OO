package com.taiwan.oomatcher.utils;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.taiwan.oomatcher.base.BaseApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 作 者: huanghuojun
 * 描 述: SD卡相关的辅助类
 * 版 本:
 * 创建日期: 2018/1/2 17:31
 */

public class SDCardUtils {
    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取ixuehao在sd卡中的缓存路径
     *
     * @return
     */
    public static String getIxuehaoPath() {
        if (getSDCardPath() == null) {
            return null;
        } else {
            File file = new File(getSDCardPath() + "ixuehao" + File.separator);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
    }

    /**
     * 获取ixuehao在sd卡中的缓存路径
     *
     * @return
     */
    public static String getCachePath() {
        if (getIxuehaoPath() == null) {
            return null;
        } else {
            File file = new File(getIxuehaoPath() + File.separator + "cache" + File.separator);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
    }

    /**
     * 获取ixuehao在sd卡中的缓存路径
     *
     * @return
     */
    public static String getDataBasePath() {
        if (getIxuehaoPath() == null) {
            return null;
        } else {
            File file = new File(getIxuehaoPath() + File.separator + "databases" + File.separator);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
    }

    /**
     * 获取ixuehao在sd卡中的缓存路径
     *
     * @return
     */
    public static String getLogPath() {
        if (getIxuehaoPath() == null) {
            return null;
        } else {
            File file = new File(getIxuehaoPath() + File.separator + "log" + File.separator);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getDCIMPath() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if(file == null || !file.exists()){
            return getSDCardPath();
        }
        return file.getAbsolutePath() + File.separator;
    }

    public static void writeLogFile(String content, String fileName) {
        File file = new File(getTempPath(), fileName);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(content + "\r\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    public static String getTempPath() {
        if (getSDCardPath() == null) {
            return null;
        } else {
            File file = new File(getSDCardPath() + "ixuehao" + File.separator + "temp" + File.separator);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
    }

    public static String getCameraFilePath() {
        if (getSDCardPath() == null) {
            return null;
        } else {
            File tempFile = new File(getTempPath());
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            File file = new File(tempFile.getAbsolutePath(), System.currentTimeMillis() + ".png");
            return file.getAbsolutePath();
        }
    }

    /**
     * 保存到SD卡
     * dirPath是输出的文件夹名称，filaName是输出的文件名，两者共同组成输出的路径，如“ /mnt/sdcard/pictures/shot.png”。
     * 还要注意在AndroidManifest中注册写入权限：
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     */
    public static String saveToSD(Bitmap bmp, String dirPath, String fileName) throws IOException {
        // 判断sd卡是否存在
        if (isSDCardEnable()) {
            File dir = new File(dirPath);
            // 判断文件夹是否存在，不存在则创建
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dirPath, fileName);
            // 判断文件是否存在，不存在则创建
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                if (fos != null) {
                    // 第一参数是图片格式，第二个是图片质量，第三个是输出流
                    boolean compress = bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    // 用完关闭
                    fos.flush();
                    fos.close();
                    if (!compress) {
                        ToastUtils.showShort("保存失败。");
                    }
                    MediaScannerConnection.scanFile(BaseApplication.getInstance().getApplicationContext(), new String[]{file.getPath()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.e("Scanned ", "===>> " + path);
//                                    ToastUtils.showShort("onScanCompleted===>>"+path);
                                }
                            });
                    return file.toString();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ToastUtils.showShort("找不到SD卡！");
            return null;
        }
        return null;
    }

}
