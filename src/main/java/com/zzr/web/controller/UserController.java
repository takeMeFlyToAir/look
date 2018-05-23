package com.zzr.web.controller;

import com.zzr.business.entity.User;
import com.zzr.business.service.UserService;
import com.zzr.util.common.JsonResult;
import com.zzr.util.common.JsonResultToValid;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import com.zzr.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sjgtw-zzr on 2018/3/8.
 */
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public String userList(){
        return "user/user_list";
    }

    @RequestMapping(value = "loadUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PagerResult<UserVO> loadUser(Pager<UserVO> userVOPager, UserVO userVO){
        userVOPager.setCondition(userVO);
        return userService.findPage(userVOPager);
    }

    @RequestMapping(value = "/edit/{userId}",method = RequestMethod.GET)
    public String userEdit(@PathVariable Long userId,Model model){
        if(userId == null || userId == 0){
            model.addAttribute("user",new UserVO());
            return "user/user_edit";
        }
        UserVO user = userService.getById(userId);
        model.addAttribute("user",user);
        return "user/user_edit";
    }

    @RequestMapping(value = "/checkAccountIsExist",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JsonResultToValid checkAccountIsExist(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "param")String account,
            @RequestParam(value = "name")String fieldId){
        UserVO userVO = userService.getUser(account);
        Boolean isRepeat = (userVO != null && id == null) || (id != null && !userVO.getId().equals(id));
        if(isRepeat){
            return new JsonResultToValid("用户名重复");
        }else{
            return new JsonResultToValid();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public JsonResult userEdit(UserVO user){
        try {
            if(user.getId() == null || user.getId() == 0){
                UserVO result = userService.add(user);
                if(result == null || result.getId() == null || result.getId() == 0){
                    log.error("添加用户失败，用户{}",result);
                    return new JsonResult(false,"用户添加失败");
                }
                return new JsonResult(true,"添加用户成功");
            }else{
                UserVO userVO = userService.getById(user.getId());
                user.setPassword(userVO.getPassword());
                userService.update(user);
                return new JsonResult(true,"用户更新成功");
            }
        }catch (Exception e){
            log.error("用户更新或者添加出错",e);
        }
        return new JsonResult(false,"操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public JsonResult deleteUser(@RequestParam(value = "ids") List<Long> ids){
        try {
            if(ids.isEmpty()){
                return new JsonResult(false,"请选择要删除的数据");
            }
            userService.delList(ids);
            return new JsonResult(true,"用户删除成功");
        }catch (Exception e){
            log.error("用户更新或者添加出错",e);
        }
        return new JsonResult(false,"操作失败");
    }
}

