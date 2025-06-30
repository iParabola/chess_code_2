package com.ruoyi.business.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 棋子阵营视图对象 biz_chess_pieces_camp
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class BizChessPiecesCampVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 棋子阵营名称
     */
    @ExcelProperty(value = "棋子阵营名称")
    private String name;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 扩展字段
     */
    @ExcelProperty(value = "扩展字段")
    private String ext;

    @TableField(exist = false)
    private String verdictRecordId;
    @TableField(exist = false)
    private Boolean isChooseCampDone;


}
