package com.zzr.business.service;


import com.zzr.core.repository.service.BaseService;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import com.zzr.vo.user.UserVO;

/**
 * Created by sjgtw-zzr on 2018/3/14.
 */
public interface UserService extends BaseService<UserVO> {

    PagerResult<UserVO> findPage(Pager<UserVO> userVOPager);

    UserVO getUser(String account);
}
