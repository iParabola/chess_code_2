package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 棋子属性定义对象 biz_chess_pieces_attribute
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_chess_pieces_attribute")
public class BizChessPiecesAttribute extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 棋子属性名称
     */
    private String name;
    /**
     * 棋子属性名称
     */
    private String code;
    /**
     * product表id
     */
    private Long productId;
    /**
     * 产品名称
     */
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
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;

}
