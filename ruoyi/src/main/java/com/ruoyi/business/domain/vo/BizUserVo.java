package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 用户管理视图对象 biz_user
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Data
@ExcelIgnoreUnannotated
public class BizUserVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String userName;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickName;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String institution;

    /**
     * 权限
     */
    @ExcelProperty(value = "权限", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "biz_permission")
    private String permission;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


    private String salt;

    /**
     * 加盐后密码
     */
    private String password;


}
