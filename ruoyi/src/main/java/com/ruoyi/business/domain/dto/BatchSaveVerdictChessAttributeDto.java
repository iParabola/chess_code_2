package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.bo.BizChessPiecesAttributeBo;
import lombok.Data;

import java.util.List;

/**
 * @author HeroWang
 * @date 2024/09/02 15:32
 */
@Data
public class BatchSaveVerdictChessAttributeDto {

    private String chessTypeId;

    private List<BizChessPiecesAttributeBo> detailList;

}
