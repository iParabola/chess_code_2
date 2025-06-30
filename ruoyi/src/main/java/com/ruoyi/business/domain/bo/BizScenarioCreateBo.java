package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
public class BizScenarioCreateBo extends BaseEntity {
    private String scenarioId;

    @Getter
    private String scenarioName;

    private String chessPiecesIds;

    private Long arbiterMapId;

    private Long productId;

    private Long chessRoundLimit;
}
