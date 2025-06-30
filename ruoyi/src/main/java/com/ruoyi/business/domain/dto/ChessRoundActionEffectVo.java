package com.ruoyi.business.domain.dto;

import lombok.Data;

@Data
public class ChessRoundActionEffectVo {

    private Long selfAttackScore;

    private Long selfDiceScore;

    private Long targetAttackScore;

    private Long targetDiceScore;

}
