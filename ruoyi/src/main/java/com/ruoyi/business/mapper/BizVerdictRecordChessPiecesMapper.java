package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.business.domain.BizVerdictRecordChessPieces;
import com.ruoyi.business.domain.BizVerdictRecordHistory;
import com.ruoyi.business.domain.bo.BizVerdictRecordChessPiecesBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordDetailBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordChessPiecesVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordHistoryVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 裁决记录棋子Mapper接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface BizVerdictRecordChessPiecesMapper extends BaseMapperPlus<BizVerdictRecordChessPiecesMapper, BizVerdictRecordChessPieces, BizVerdictRecordChessPiecesVo> {
    List<BizVerdictRecordHistoryVo>  queryChessRecordByRound(@Param(Constants.WRAPPER) QueryWrapper<BizVerdictRecordHistory> queryWrapper);

    void setActionPointFalse(@Param("bizVerdictRecordChessPiecesBo") BizVerdictRecordChessPiecesBo dto);

    void resetActionPointFalse(@Param("bizVerdictRecordChessPiecesBo") BizVerdictRecordChessPiecesBo dto);
    Long updateFromLocation(Long id);
}
