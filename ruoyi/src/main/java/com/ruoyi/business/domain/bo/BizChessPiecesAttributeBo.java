package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 棋子属性定义业务对象 biz_chess_pieces_attribute
 *
 * @author ruoyi
 * @date 2024-09-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizChessPiecesAttributeBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 棋子属性名称
     */
    @NotBlank(message = "棋子属性名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 棋子属性编码
     */
    @NotBlank(message = "棋子属性编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * product表id
     */
    @NotNull(message = "product表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;


}
