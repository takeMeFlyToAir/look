package com.zzr.business.serviceImpl;

import com.zzr.business.dao.CustomerDao;
import com.zzr.business.entity.Customer;
import com.zzr.business.service.CustomerService;
import com.zzr.util.common.CollectionUtil;
import com.zzr.util.common.EntityUtil;
import com.zzr.util.common.ObjectConvertUtil;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import com.zzr.vo.customer.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by sjgtw-zzr on 2018/3/14.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;


    @Override
    public CustomerVO add(CustomerVO o) {
        o.setId(EntityUtil.getAutoId());
        customerDao.save(ObjectConvertUtil.voToPO(o,Customer.class));
        return o;
    }

    @Override
    public void delList(List<Long> ids) {
        Condition condition = new Condition(Customer.class);
        condition.or().andIn("id",ids);
        customerDao.deleteByCondition(condition);
    }

    @Override
    public void del(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customerDao.delete(customer);
    }

    @Override
    public List<CustomerVO> findAll() {
        return ObjectConvertUtil.poListToVOList(customerDao.select(new Customer()),CustomerVO.class);
    }

    @Override
    public CustomerVO getById(Long id) {
        return ObjectConvertUtil.poToVO(customerDao.get(id),CustomerVO.class);
    }

    @Override
    public CustomerVO update(CustomerVO o) {
        customerDao.updateSelective(ObjectConvertUtil.voToPO(o,Customer.class));
        return o;
    }
    @Override
    public PagerResult<CustomerVO> findPage(Pager<CustomerVO> customerVOPager) {
        CustomerVO customerVO = customerVOPager.getCondition();
        Condition condition = new Condition(Customer.class);
        Example.Criteria criteria = condition.or().andEqualTo("deleted",0);
        if(customerVO.getName() != null){
            criteria.andEqualTo("name",customerVO.getName());
        }
        PagerResult<Customer> customerPagerResult = customerDao.selectPage(condition,customerVOPager);
        PagerResult<CustomerVO> customerVOPagerResult = new PagerResult<>();
        customerVOPagerResult.setRows(ObjectConvertUtil.poListToVOList(customerPagerResult.getRows(),CustomerVO.class));
        customerVOPagerResult.setTotal(customerPagerResult.getTotal());
        return customerVOPagerResult;
    }
}
