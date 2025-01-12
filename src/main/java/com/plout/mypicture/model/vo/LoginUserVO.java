package com.plout.mypicture.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/* *
 * 用户视图(脱敏)
 * @author plout
 **/
@Data
public class LoginUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

}
