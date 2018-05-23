package com.zzr.business.serviceImpl;

import com.zzr.business.dao.UserDao;
import com.zzr.business.entity.User;
import com.zzr.business.service.UserService;
import com.zzr.util.common.CollectionUtil;
import com.zzr.util.common.EntityUtil;
import com.zzr.util.common.ObjectConvertUtil;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import com.zzr.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by sjgtw-zzr on 2018/3/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public UserVO add(UserVO o) {
        o.setId(EntityUtil.getAutoId());
        userDao.save(ObjectConvertUtil.voToPO(o,User.class));
        return o;
    }

    @Override
    public void delList(List<Long> ids) {
        Condition condition = new Condition(User.class);
        condition.or().andIn("id",ids);
        userDao.deleteByCondition(condition);
    }

    @Override
    public void del(Long id) {
        User user = new User();
        user.setId(id);
        userDao.delete(user);
    }

    @Override
    public List<UserVO> findAll() {
        return ObjectConvertUtil.poListToVOList(userDao.select(new User()),UserVO.class);
    }

    @Override
    public UserVO getById(Long id) {
        return ObjectConvertUtil.poToVO(userDao.get(id),UserVO.class);
    }

    @Override
    public UserVO update(UserVO o) {
        userDao.updateSelective(ObjectConvertUtil.voToPO(o,User.class));
        return o;
    }
    @Override
    public PagerResult<UserVO> findPage(Pager<UserVO> userVOPager) {
        UserVO userVO = userVOPager.getCondition();
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.or().andEqualTo("deleted",0);
        if(userVO.getAccount() != null){
            criteria.andEqualTo("account",userVO.getAccount());
        }
        PagerResult<User> userPagerResult = userDao.selectPage(condition,userVOPager);
        PagerResult<UserVO> userVOPagerResult = new PagerResult<>();
        userVOPagerResult.setRows(ObjectConvertUtil.poListToVOList(userPagerResult.getRows(),UserVO.class));
        userVOPagerResult.setTotal(userPagerResult.getTotal());
        return userVOPagerResult;
    }

    @Override
    public UserVO getUser(String account) {
        Condition condition = new Condition(User.class);
        condition.or().andEqualTo("account",account);
        List<User> userList  = userDao.selectByCondition(condition);
        if(CollectionUtil.isNullOrEmpty(userList)){
            return null;
        }
        return ObjectConvertUtil.poToVO(userList.get(0),UserVO.class);
    }


}
