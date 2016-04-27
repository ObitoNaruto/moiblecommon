package com.android.mobile.utils.util.io;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by xinming.xxm on 2016/4/27.
 */
public class MemoryUtils {

    public static String getExtMemoryInfo(Context context){
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        long totalSize = blockSize * totalBlocks;
        long avaiSize = blockSize * availableBlocks;

        String totalStr = Formatter.formatFileSize(context, totalSize);
        String avaiStr = Formatter.formatFileSize(context, avaiSize);

        return "总内存:" + totalStr + "\n" + "可用内存:" + avaiStr;
    }

    public static String getRomSpaceInfo(Context context){
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());

        long blockSize = statFs.getBlockSizeLong();
        long totalBlocks = statFs.getBlockCountLong();
        long avaiableBlocks = statFs.getAvailableBlocksLong();

        long totalSize = blockSize * totalBlocks;
        long avaiSize = blockSize * avaiableBlocks;

        String totalStr = Formatter.formatFileSize(context, totalSize);
        String avaiStr = Formatter.formatFileSize(context, avaiSize);

        return "手机内存总空间" + totalStr + "\n" + "手机内存可用空间" + avaiStr;
    }
}
