package com.plout.mypicture.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plout.mypicture.model.dto.user.UserLoginRequest;
import com.plout.mypicture.model.dto.user.UserQueryRequest;
import com.plout.mypicture.model.dto.user.UserRegisterRequest;
import com.plout.mypicture.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plout.mypicture.model.vo.LoginUserVO;
import com.plout.mypicture.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 万帮帮
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-01-12 16:18:55
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求
     * @return 用户id
     */
    long UserRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     * @param userLoginRequest 用户登录请求
     * @return 登录用户信息
     */
    LoginUserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息列表
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @param user
     * @return
     */
    LoginUserVO getLoginUserVO(User user);


    /**
     * 获取加密后的密码
     * @param password 密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String password);

    /**
     * 获取查询条件
     * @param userQueryRequest 用户查询请求
     * @return 查询条件
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

}
