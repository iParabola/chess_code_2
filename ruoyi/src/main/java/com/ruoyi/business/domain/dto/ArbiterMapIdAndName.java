package com.ruoyi.business.domain.dto;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class ArbiterMapIdAndName {
    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 地图名称
     */
    @ExcelProperty(value = "地图名称")
    private String mapName;


}
