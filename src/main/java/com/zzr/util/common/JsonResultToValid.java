package com.zzr.util.common;

/**
 * Created by sjgtw-zzr on 2018/4/21.
 */
public class JsonResultToValid {
    private String info="通过信息验证！";

    private String status="y";


    public JsonResultToValid(){

    }

    public JsonResultToValid(String info){
        this(info,"n");
    }

    public JsonResultToValid(String info, String status) {
        this.info = info;
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public String getStatus() {
        return status;
    }
}
