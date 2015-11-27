package com.aat.dxfy.base.web.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 系统用户的过滤器
 * @info java shiro filter
 * @update 无
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(CommonConstant.CURRENT_USER, userService.queryUserLogin(username));
        return true;
    }
}
