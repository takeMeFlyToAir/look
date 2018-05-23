//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzr.util.common;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 8382815206967060681L;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_NORMAL = 200;
    public static final int CODE_FAIL = -1;
    private boolean result;
    private int errorCode;
    private String message;
    private T data;
    public Object ext;

    /** @deprecated */
    @Deprecated
    public JsonResult(boolean result) {
        this(result, (String)null);
    }

    public JsonResult(T data) {
        this(true, (String)null, data);
    }

    public JsonResult(JsonResult.ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getDisplay());
    }

    public JsonResult(int errorCode, String message) {
        this(isSuccess(errorCode), errorCode, message, (T) null);
    }

    public JsonResult(boolean result, String message) {
        this(result, message, (T) null);
    }

    public JsonResult(String message, T data) {
        this(true, message, data);
    }

    /** @deprecated */
    @Deprecated
    public JsonResult(boolean result, String message, T data) {
        this(result, result?0:-1, message, data);
    }

    private JsonResult(boolean result, int errorCode, String message, T data) {
        this.result = result;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    private static boolean isSuccess(int errorCode) {
        return 0 == errorCode || 200 == errorCode;
    }

    /** @deprecated */
    @Deprecated
    public JsonResult(int errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.result = isSuccess(errorCode);
        this.message = message;
        this.data = data;
    }

    /** @deprecated */
    @Deprecated
    public JsonResult(boolean result, JsonResult.ErrorCode errorCode, String message, T data) {
        this.result = result;
        if(errorCode != null) {
            this.errorCode = errorCode.getCode();
        }

        this.message = message;
        this.data = data;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <K> JsonResult<K> createFailureResult(String message) {
        return new JsonResult(false, message);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(JsonResult.ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
    }

    public static enum ErrorCode {
        /** @deprecated */
        @Deprecated
        NONE("", 200),
        NOT_LOGIN("您的登录状态已失效，请重新登录！", 401),
        NOT_FIND_DATA("系统未查询到该数据", 4041),
        NOT_VALID("对不起，安全验证失败", 402),
        CURRENT_NOT_PERMISS_OPERATE("当前状态下不容许该操作", 4021),
        DATA_VALID_FAILURE("数据格式不符合要求，请重新提交!", 400),
        JAVA_CODE_ERROR("网络出错，请重新操作！", 505),
        BUSSINESS_NOT_SUPPORTED("当前客户端版本过低，不支持该功能，请下载最新版本", 600),
        PERMISS_DENIED("对不起，您没有执行该操作的权限", 403),
        PERMISS_DATA_DENIED("对不起，您没有操作该数据的权限！", 4032);

        private String display;
        private int code;

        private ErrorCode(String display, int code) {
            this.display = display;
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public String getValue() {
            return this.name();
        }

        public static JsonResult.ErrorCode fromCode(int code) {
            JsonResult.ErrorCode[] states = values();
            JsonResult.ErrorCode[] var2 = states;
            int var3 = states.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                JsonResult.ErrorCode state = var2[var4];
                if(state.getCode() == code) {
                    return state;
                }
            }

            return null;
        }

        public String toString() {
            return this.display;
        }

        public String getDisplay() {
            return this.display;
        }
    }
}
