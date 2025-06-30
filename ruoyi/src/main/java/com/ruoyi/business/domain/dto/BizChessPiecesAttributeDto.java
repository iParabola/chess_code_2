package com.ruoyi.business.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 棋子属性定义视图对象 biz_chess_pieces_attribute
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@Data
public class BizChessPiecesAttributeDto implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
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
     * chess_pieces_type表id
     */
    private Long chessTypeId;
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
