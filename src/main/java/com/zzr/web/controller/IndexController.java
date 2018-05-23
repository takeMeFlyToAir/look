package com.zzr.web.controller;

import com.zzr.util.common.JsonResult;
import com.zzr.util.shiro.auth.ShiroUser;
import com.zzr.util.shiro.auth.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sjgtw-zzr on 2018/3/8.
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping("")
    public String index(){
        ShiroUser shiroUser = UserUtil.getCurrentUser();
        System.out.println(shiroUser);
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 用户登陆
     *
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxLoginCheck(
            HttpServletRequest request,
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password)
    {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("用户登录：",e);
            return new JsonResult(false, e.getMessage());
        } catch (LockedAccountException e) {
            log.error("用户登录：",e);
            return new JsonResult(false, e.getMessage());
        } catch (AuthenticationException e) {
            log.error("用户登录：",e);
            return new JsonResult(false, "用户名或密码错误");
        } catch(Exception e) {
            log.error("用户登录：",e);
            return new JsonResult(false, "用户名或密码错误");
        }
        return new JsonResult("登录成功");
    }

    /**
     * 获取用户权限"
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<ShiroUser> logoutAjax() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new JsonResult("退出成功");
    }
}
