package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.business.domain.BizVerdictRecordHistory;
import com.ruoyi.business.domain.BizVerdictRule;
import com.ruoyi.business.domain.bo.*;
import com.ruoyi.business.domain.dto.*;
import com.ruoyi.business.domain.vo.BizIndirectBlowEffectVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordHistoryVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 裁决记录历史复盘Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizVerdictRecordHistoryService {

    /**
     * 查询裁决记录历史复盘
     */
    BizVerdictRecordHistoryVo queryById(Long id);

    /**
     * 查询裁决记录历史复盘列表
     */
    TableDataInfo<BizVerdictRecordHistoryVo> queryPageList(BizVerdictRecordHistoryBo bo, PageQuery pageQuery);

    /**
     * 查询裁决记录历史复盘列表
     */
    List<BizVerdictRecordHistoryVo> queryList(BizVerdictRecordHistoryBo bo);

    /**
     * 新增裁决记录历史复盘
     */
    Boolean insertByBo(BizVerdictRecordHistoryBo bo);

    /**
     * 修改裁决记录历史复盘
     */
    Boolean updateByBo(BizVerdictRecordHistoryBo bo);

    /**
     * 校验并批量删除裁决记录历史复盘信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    String deployChessPieces(BizVerdictRecordHistoryBo dto);


    String endDeploy(BizVerdictRecordHistoryBo dto);

    Boolean isOwnRound(BizVerdictRecordBo dto);

    BizIndirectBlowEffectVo chessPiecesAction(BizVerdictRecordDetailBo dto);


    void chessPiecesActionNew(BizVerdictRecordDetailBo dto);

    void chessPiecesActionContinue(BizVerdictRuleBo dto);

    /**
     * 根据裁决id 和 回合数 查询记录
     *
     * @param verdictRecordId
     * @param chessRound
     * @return
     */
    List<BizVerdictRecordHistory> queryByVerdictRecordIdAndChessRound(Long verdictRecordId, Long chessRound);


    QueryEachActionVo queryEachAction( BizVerdictRecordBo dto);


    ChessRoundActionEffectVo queryActionEffect( BizVerdictRecordBo dto);

    QueryVerdictResultVo queryVerdictResult( BizVerdictRecordBo dto);

    String endRound( BizVerdictRecordBo dto);


    QueryPromptVo queryPrompt( BizVerdictRecordBo dto);


    String endGame(  BizVerdictRecordBo dto);

    Map<Long, List<BizVerdictRecordHistory>> chessPiecesActionHistory( BizVerdictRecordBo dto);


    List<JudgeVo> judge(BizVerdictRecordRoundBo bo);

    List<BizVerdictRecordHistoryVo> getAwaitJudge(BizVerdictRecordRoundBo bo);



    Long confirmJudge(BizVerdictRecordHistoryBo bo);

    List<BizVerdictRecordHistoryBo> selectChessPieceByVerdictRecordId(BizVerdictRecordHistoryBo dto);

    String calculateRoundScore(BizVerdictRecordHistoryBo bo);
}
