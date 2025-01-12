package com.plout.mypicture.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plout.mypicture.annotation.AuthCheck;
import com.plout.mypicture.common.BaseResponse;
import com.plout.mypicture.common.DeleteRequest;
import com.plout.mypicture.common.ResultUtils;
import com.plout.mypicture.constant.UserConstant;
import com.plout.mypicture.exception.ErrorCode;
import com.plout.mypicture.exception.ThrowUtils;
import com.plout.mypicture.model.dto.user.*;
import com.plout.mypicture.model.entity.User;
import com.plout.mypicture.model.vo.LoginUserVO;
import com.plout.mypicture.model.vo.UserVO;
import com.plout.mypicture.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 用户注册请求
     * @return 用户id
     */
    @PostMapping("/register")
    public BaseResponse<Long> UserRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        ThrowUtils.throwIf(ObjectUtil.isEmpty(userRegisterRequest), ErrorCode.PARAMS_ERROR);
        long result = this.userService.UserRegister(userRegisterRequest);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest 用户登入请求
     * @return 登录用户
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        ThrowUtils.throwIf(ObjectUtil.isEmpty(userLoginRequest), ErrorCode.PARAMS_ERROR);
        LoginUserVO loginUserVO = this.userService.userLogin(userLoginRequest, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 获取当前登录用户
     * @param request 请求
     * @return 当前登录用户
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

    /**
     * 退出登录
     * @param request 请求
     * @return 退出登录
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (ObjectUtil.isEmpty(request)) {
            throw new RuntimeException("请求为空");
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }


    /**
     * 创建用户
     * @param userAddRequest 创建用户请求(仅管理员)
     * @return 创建用户id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest){
        ThrowUtils.throwIf(ObjectUtil.isEmpty(userAddRequest), ErrorCode.PARAMS_ERROR);
        User user = new User();
        user.setUserAccount(userAddRequest.getUserAccount());
        final String password = "12345678";
        String encryptPassword = this.userService.getEncryptPassword(password);
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(user.getId());
    }

    /* *
     * 根据id获取用户(仅管理员)
     * @author plout
     **/
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(long id){
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isEmpty(user), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(user);
    }

    /* *
     * 根据id获取保证类
     *
     * @author plout
     **/
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id){
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isEmpty(user), ErrorCode.NOT_FOUND_ERROR);
        UserVO userVO = userService.getUserVO(user);
        return ResultUtils.success(userVO);
    }

    /* *
     * 根据id删除用户(仅管理员)
     *
     * @author plout
     **/
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest){
        ThrowUtils.throwIf(ObjectUtil.isEmpty(deleteRequest) || deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);
        boolean result = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(result);
    }

    /* *
     * 根据id更新用户(仅管理员)
     *
     * @author plout
     **/
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        ThrowUtils.throwIf(ObjectUtil.isEmpty(userUpdateRequest) || userUpdateRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest,user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result,ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(result);
    }

    /* *
     * 分页获取用户封装列表(仅管理员)
     *
     * @author plout
     */
    @GetMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserVO>> listUserVOByPage(UserQueryRequest userQueryRequest){
        ThrowUtils.throwIf(ObjectUtil.isEmpty(userQueryRequest),ErrorCode.PARAMS_ERROR);
        long current = userQueryRequest.getCurrent();
        long pageSize = userQueryRequest.getPageSize();
        Page<User> page = userService.page(new Page<>(current, pageSize), this.userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current,pageSize,page.getTotal());
        List<UserVO> userVOList = userService.getUserVOList(page.getRecords());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }
}
