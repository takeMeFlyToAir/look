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
@Table(name = "look_customer")
public class Customer extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1972489869581873269L;

    private String name;

    private String mobilePhone;

    private String sex;

    private Integer age;

    private String headImg;
}