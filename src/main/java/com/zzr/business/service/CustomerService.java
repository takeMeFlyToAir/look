package com.zzr.business.service;


import com.zzr.core.repository.service.BaseService;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import com.zzr.vo.customer.CustomerVO;
/**
 * Created by sjgtw-zzr on 2018/3/14.
 */
public interface CustomerService extends BaseService<CustomerVO> {

    PagerResult<CustomerVO> findPage(Pager<CustomerVO> customerVOPager);

}
