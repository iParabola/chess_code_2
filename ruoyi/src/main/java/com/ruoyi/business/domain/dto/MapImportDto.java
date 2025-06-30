package com.ruoyi.business.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author kyc
 * @date 2024/3/14 16:10
 */
@Data
public class MapImportDto {
    @ExcelProperty(value = "偏移坐标")
    private String offset;
    @ExcelProperty(value = "地图编号")
    private String coordinate;
    @ExcelProperty(value = "高程")
    private Long elevation;
    @ExcelProperty(value = "地形")
    private String terrainName;
    @ExcelProperty(value = "是否桥梁")
    private String bridgeFlag;
    @ExcelProperty(value = "道路类型")
    private String roadTypeName;
    @ExcelProperty(value = "道路指向点编号")
    private String toRoadNumber;
    @ExcelProperty(value = "道路名称")
    private String roadName;

}
