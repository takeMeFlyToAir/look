package com.zzr.business.dao;

import com.zzr.business.entity.Customer;
import com.zzr.business.entity.User;
import com.zzr.business.mapper.CustomerMapper;
import com.zzr.business.mapper.UserMapper;
import com.zzr.core.repository.dao.BaseIdEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by sjgtw-zzr on 2018/3/14.
 */
@Repository
public class CustomerDao extends BaseIdEntityDao<Customer> {

    @Autowired
    private CustomerMapper customerMapper;
}
