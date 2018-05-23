package com.zzr.vo.user;

import com.zzr.core.base.vo.BaseVO;
import lombok.Data;

import java.io.Serializable;

/**
 * User
 * @author wangfan
 * @date 2017-7-4 下午2:49:38
 */
@Data
public class UserVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1972489869581873269L;

    private String account;

    private String password;

    private String nickname;

    private String mobilePhone;

    private String sex;

    private Integer status;  //0正常，1冻结

    private String token;

    private String headImg;


    public Boolean isLock(){
        return (status == null) ? false : (status == 1) ? true : false;
    }
}