package com.ruoyi.business.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author kyc
 * @date 2024/2/27 16:29
 */
@Data
public class QueryAllChessPiecesInfoDto {

    private String verdictRecordId;

    private Long chessRound;

    private String campId;

    private String stageParam;

    private Long roundPeriod;

}
