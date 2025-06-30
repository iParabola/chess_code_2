package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理业务对象 biz_user
 *
 * @author ruoyi
 * @date 2024-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizUserBo extends BaseEntity {

    /**
     *
     */
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 单位
     */
    private String institution;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 加盐后密码
     */
    private String password;

    /**
     * 权限
     */
    private String permission;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;


    private String confirmPassword;

    private String loginName;

}
