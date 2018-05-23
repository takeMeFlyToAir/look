package com.zzr.web.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created by ZYW on 2014/7/18.
 */
public class SystemUtils {

    public static String getFileNameExt(String fileName){
        if(fileName == null || StringUtils.isEmpty(fileName)){
            throw new RuntimeException("参数fileName不能为空值！");
        }
        return "."+FilenameUtils.getExtension(fileName);
    }

    public static String timeFileName(String fileName){
        String fileExt = getFileNameExt(fileName);
        return System.currentTimeMillis()+fileExt;
    }
    /**
     * 判断目录是否存在，如果不存在就创建
     * */
    public static boolean isNotExistCreate(String filePath){
        File f = new File(filePath);
        if(!f.exists()){
            return f.mkdirs();
        }
        return true;
    }
 }
