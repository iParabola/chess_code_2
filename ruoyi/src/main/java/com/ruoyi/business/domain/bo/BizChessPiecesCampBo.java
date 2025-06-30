package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 棋子阵营业务对象 biz_chess_pieces_camp
 *
 * @author ruoyi
 * @date 2024-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BizChessPiecesCampBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 棋子阵营名称
     */
    @NotBlank(message = "棋子阵营名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

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


    private Long verdictRecordId;


    private long userId;

    private Integer userType;
}
