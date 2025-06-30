package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.BizVerdictRecordChessPieces;
import lombok.Data;

import java.util.List;

@Data
public class QueryEachActionDetail {

    private List<BizVerdictRecordChessPieces> pieceList;

    private String tips;

}
