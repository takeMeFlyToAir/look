package com.zzr.util.common;

import java.util.List;

/**
 * Created by sjgtw-zzr on 2018/3/29.
 */
public class CollectionUtil {

    public static boolean isNullOrEmpty(List list){
        if(list == null || list.size() == 0){
            return true;
        }
        return false;
    }

    public static boolean isNotNullOrEmpty(List list){
        return !isNullOrEmpty(list);
    }


}
