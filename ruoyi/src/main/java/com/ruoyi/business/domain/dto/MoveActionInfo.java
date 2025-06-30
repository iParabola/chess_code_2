package com.ruoyi.business.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kyc
 * @date 2024/3/22 12:20
 */
@Data
public class MoveActionInfo {

    private Double slope;

    private Double terrain;

    private Double road;

    private Double useUp;

    private Double residue;

    private String offset;

    private String coordinate;
}
