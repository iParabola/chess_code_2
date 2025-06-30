package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 棋子阵营对象 biz_chess_pieces_camp
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_chess_pieces_camp")
public class BizChessPiecesCamp extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 棋子阵营名称
     */
    private String name;
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
