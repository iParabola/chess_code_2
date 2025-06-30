package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 棋子类型业务对象 biz_chess_pieces_type
 *
 * @author ruoyi
 * @date 2024-08-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizChessPiecesTypeBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 棋子类型名称
     */
    @NotBlank(message = "棋子类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

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
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
    private String ext;


}
