package com.ruoyi.business.domain.dto;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class ArbiterMapEditInfo {
    Long arbiterMapId;
    Long terrainId;
    String offset ;
    String terrainCover;
}
