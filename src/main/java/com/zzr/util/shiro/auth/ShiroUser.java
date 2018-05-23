package com.zzr.util.shiro.auth;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;


public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1373760761780840081L;
	public Long id;
	public String account;
	public String name;
	public Long companyId;
	public Date lastRefreshed;

	public ShiroUser(Long id, String name, String account, Long companyId) {
		this.id = id;
		this.account = account;
		this.name = name;
		this.lastRefreshed = new Date();
	}

	public String getUserId() {
		return name;
	}

	public String getaccount() {
		return account;
	}

	public Long getId() {
		return id;
	}

	public Long getCompanyId() {
		return companyId;
	}


	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return account;
	}

	/**
	 * 重载equals,只计算name;
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "userId");
	}
	
	/**
	 * 重载equals,只比较loginName
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "userId");
	}

}
