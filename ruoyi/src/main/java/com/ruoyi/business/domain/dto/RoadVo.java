package com.ruoyi.business.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kyc
 * @date 2024/3/15 20:10
 */
@Data
public class RoadVo {
    private String fromOffset;

    private String fromCoordinate;

    private String toOffset;

    private String toCoordinate;

    private String roadColor;

    private String roadName;

    private Integer roadWidth;

    private String roadTypeName;

    private BigDecimal terrainActionValue;
}
