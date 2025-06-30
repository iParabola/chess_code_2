package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizArbiterMap;
import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.domain.bo.BizChessPiecesCampBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordBo;
import com.ruoyi.business.domain.dto.QueryVerdictRecordDetail;
import com.ruoyi.business.domain.vo.BizChessPiecesCampVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 裁决记录Service接口
 *
 * @author ruoyi
 * @date 2024-02-21
 */
public interface IBizVerdictRecordService {

    /**
     * 查询裁决记录
     */
    BizVerdictRecordVo queryById(Long id);

    /**
     * 查询裁决记录列表
     */
    TableDataInfo<BizVerdictRecordVo> queryPageList(BizVerdictRecordBo bo, PageQuery pageQuery);

    /**
     * 查询裁决记录列表
     */
    List<BizVerdictRecordVo> queryList(BizVerdictRecordBo bo);

    /**
     * 新增裁决记录
     */
    Boolean insertByBo(BizVerdictRecordBo bo);

    /**
     * 修改裁决记录
     */
    Boolean updateByBo(BizVerdictRecordBo bo);

    /**
     * 校验并批量删除裁决记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    BizVerdictRecordVo saveVerdictScenario(BizVerdictRecordBo saveVerdictScenarioDto);

    String saveVerdictChessPieces(BizChessPiecesBo saveVerdictChessPiecesDto);


    String inviteFriend(@RequestBody BizVerdictRecordBo inviteFriendDto);

    BizVerdictRecordVo responseInvite(BizVerdictRecordBo responseInviteDto);

    List<BizChessPiecesCampVo> queryCampList(@RequestBody BizChessPiecesCampBo queryCampDto);

    BizChessPiecesCampVo chooseCamp(@RequestBody BizChessPiecesCampBo chooseCampDto);

    String startGame(BizVerdictRecordBo startGameDto);

    BizVerdictRecordVo queryGameStatus(BizVerdictRecordBo dto);


    BizVerdictRecordVo queryRoomUser(BizVerdictRecordBo dto);

    BizVerdictRecordVo queryVerdictId(BizVerdictRecordBo dto);


    BizVerdictRecordVo queryChessRound(BizVerdictRecordBo dto);


    TableDataInfo<QueryVerdictRecordDetail> queryVerdictRecordList(BizVerdictRecordBo search, PageQuery pageQuery);

    TableDataInfo<QueryVerdictRecordDetail> queryUnfinishedList(BizVerdictRecordBo search, PageQuery pageQuery);

    String generateInviteCode(Long verdictRecordId);

    String joinRoom(String inviteCode, String type);

    BizVerdictRecord queryVerdictRecordByInviteCode(String inviteCode);

    int[] getDice(Integer diceCount);

    void saveToCache(Long verdictRecordId);

    void nextStage(Long  id);

    Long getMapId(Long verdictRecordId);

    BizArbiterMap getMap(Long verdictRecordId);

    BizArbiterMap getMapC(String ScenarioId);

    String setScore(Long id, Long firstScore, Long secondScore);

    Long getScoreByCampId(Long id, Long campId);

    Long queryScenarioIdByVerdictRecordId(Long verdictRecordId);
}
