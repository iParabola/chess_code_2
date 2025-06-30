package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户验证码业务对象 biz_user_verify_code
 *
 * @author ruoyi
 * @date 2024-02-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizUserVerifyCodeBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mobile;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String verifyCode;

    /**
     * 失效时间
     */
    @NotNull(message = "失效时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expireTime;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 扩展字段
     */
    @NotBlank(message = "扩展字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ext;


}
