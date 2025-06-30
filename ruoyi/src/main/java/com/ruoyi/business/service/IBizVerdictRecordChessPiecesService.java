package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictRecordChessPieces;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordDetailBo;
import com.ruoyi.business.domain.dto.QueryAllChessPiecesInfoDto;
import com.ruoyi.business.domain.vo.BizArbiterMapVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordChessPiecesVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordChessPiecesBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordHistoryVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 裁决记录棋子Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizVerdictRecordChessPiecesService {

    /**
     * 查询裁决记录棋子
     */
    BizVerdictRecordChessPiecesVo queryById(Long id);

    /**
     * 查询裁决记录棋子列表
     */
    TableDataInfo<BizVerdictRecordChessPiecesVo> queryPageList(BizVerdictRecordChessPiecesBo bo, PageQuery pageQuery);

    /**
     * 查询裁决记录棋子列表
     */
    List<BizVerdictRecordChessPiecesVo> queryList(BizVerdictRecordChessPiecesBo bo);

    /**
     * 新增裁决记录棋子
     */
    Boolean insertByBo(BizVerdictRecordChessPiecesBo bo);

    /**
     * 修改裁决记录棋子
     */
    Boolean updateByBo(BizVerdictRecordChessPiecesBo bo);

    /**
     * 校验并批量删除裁决记录棋子信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    BizVerdictRecordChessPiecesVo queryByVerdictRecordIdAndChessPiecesNumber(Long verdictRecordId, String chessPiecesNumber);


    void updateStatusByIds(String status, List<Long> ids);

    List<BizVerdictRecordChessPieces> queryAllChessPiecesInfo(QueryAllChessPiecesInfoDto dto);

    boolean markRemove(Long verdictRecordId);


    BizVerdictRecordChessPieces queryChessPiecesInfo( BizVerdictRecordBo dto);


    List<BizVerdictRecordChessPieces> queryUsChessPiecesInfo(  QueryAllChessPiecesInfoDto dto);
    List<BizVerdictRecordHistoryVo> queryChessRecordByRound(QueryAllChessPiecesInfoDto dto);

    BizArbiterMapVo queryMapInfo( BizVerdictRecordBo dto);

    List<BizVerdictRecordChessPieces> globalVision(BizVerdictRecordBo dto);


    Map<String,String> getMapChessImage(Long verdictRecordId);

    Map<String,String> getMapChessImageC(Long ScenarioId);

    void actionPointChange(BizVerdictRecordChessPiecesBo dto);
    void actionPointReset(BizVerdictRecordChessPiecesBo dto);

    String changeChessStatus(BizVerdictRecordChessPiecesBo dto);
}
