package com.zzr.business.entity;

import com.zzr.core.base.entity.IdEntity;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * User
 * @author zzr
 * @date 2017-7-4 下午2:49:38
 */
@Data
@Table(name = "sys_user")
public class User extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1972489869581873269L;


    private String account;

    private String password;

    private String nickname;

    private String mobilePhone;

    private String sex;

    private Integer status;  //0正常，1冻结

    private String token;

    private String headImg;
}