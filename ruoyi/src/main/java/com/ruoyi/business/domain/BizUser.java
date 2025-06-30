package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 biz_user
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_user")
public class BizUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
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
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
