package com.zzr.vo.customer;

import com.zzr.core.base.vo.BaseVO;
import lombok.Data;

import java.io.Serializable;

/**
 * User
 * @author zzr
 * @date 2017-7-4 下午2:49:38
 */
@Data
public class CustomerVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1972489869581873269L;

    private String name;

    private String mobilePhone;

    private String sex;

    private Integer age;

    private String headImg;
}