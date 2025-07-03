package com.ruoyi.business.api;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSON;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.bo.*;
import com.ruoyi.business.domain.dto.*;
import com.ruoyi.business.domain.vo.*;
import com.ruoyi.business.service.*;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kyc
 * @date 2024/2/23 14:26
 */
@Validated
@RequiredArgsConstructor
@RestController
@SaIgnore
@Slf4j
@RequestMapping("/api/armory")
public class ApiArmoryController extends BaseController {

    private final IBizScenarioCreateService bizScenarioCreateService;

    private final IBizProductService productService;

    private final IBizScenarioService scenarioService;

    private final IBizScenarioCreateService scenarioCreateService;

    private final IBizChessPiecesService chessPiecesService;

    private final IBizVerdictRecordService verdictRecordService;

    private final IBizScenarioCreateService scenarioRecordService;

    private final IBizVerdictRecordDetailService verdictRecordDetailService;

    private final IBizVerdictRecordHistoryService verdictRecordHistoryService;

    private final IBizVerdictRecordChessPiecesService verdictRecordChessPiecesService;

    private final IBizVerdictRuleService verdictRuleService;

    private final IBizVerdictBlowEffectService verdictBlowEffectService;

    private final IBizVerdictRecordScoreService verdictRecordScoreService;

    private final IBizVerdictRecordRoundService verdictRecordRoundService;

    private final IBizArbiterMapService arbiterMapService;

    /**
     * 兵棋库
     *
     * @param bo
     * @param pageQuery
     * @return
     */
    @GetMapping("/queryArmoryList")
    public TableDataInfo<BizProductVo> queryArmoryList(BizProductBo bo, PageQuery pageQuery) {
        return productService.queryPageList(bo, pageQuery);
    }

    /**
     * 兵棋详情
     *
     * @param queryArmoryDetailDto
     * @return
     */

    @PostMapping("/queryArmoryDetail")
    public R<BizProductVo> queryArmoryDetail(@RequestBody BizProductBo queryArmoryDetailDto) {
        return R.ok(productService.queryById(queryArmoryDetailDto.getId()));
    }

    /**
     * 规则了解
     *
     * @param queryArmoryDetailDto
     * @return
     */

    @PostMapping("/queryArmoryRuleDetail")
    public R<BizProductVo> queryArmoryRuleDetail(@RequestBody BizProductBo queryArmoryDetailDto) {
        return R.ok(productService.queryById(queryArmoryDetailDto.getId()));
    }

    /**
     * 智能裁决-想定选择列表
     *
     * @param queryArmoryDetailDto
     * @return
     */
    @PostMapping("/queryVerdictScenarioVos")
    public R<List<BizScenarioVo>> queryVerdictScenarioVos(@RequestBody BizProductBo queryArmoryDetailDto) {
        return R.ok(scenarioService.queryVerdictScenarioVos(queryArmoryDetailDto));
    }

    @GetMapping("/queryScenarios")
    public R<List<BizScenarioCreateVo>> queryScenarios() {
        return R.ok(scenarioCreateService.queryScenarios());
    }


    /**
     * 智能裁决-想定选择详情
     *
     * @param queryVerdictScenarioDetailDto
     * @return
     */
    @PostMapping("/queryVerdictScenarioDetail")
    public R<BizScenarioVo> queryVerdictScenarioDetail(@RequestBody BizScenarioBo queryVerdictScenarioDetailDto) {
        return R.ok(scenarioService.queryById(queryVerdictScenarioDetailDto.getId()));
    }

    /**
     * 智能裁决-选择想定
     *
     * @param saveVerdictScenarioDto
     * @return
     */

    @PostMapping("/saveVerdictScenario")
    public R<BizVerdictRecordVo> saveVerdictScenario(@RequestBody BizVerdictRecordBo saveVerdictScenarioDto) {
        return R.ok(verdictRecordService.saveVerdictScenario(saveVerdictScenarioDto));
    }

    /**
     * 智能裁决-棋子选择列表
     *
     * @param queryArmoryDetailDto
     * @return
     */
    @PostMapping("/queryVerdictChessPiecesVos")
    public R<List<BizChessPiecesVo>> queryVerdictChessPiecesVos(@RequestBody BizProductVo queryArmoryDetailDto) {
        return R.ok(chessPiecesService.queryVerdictChessPiecesVos(queryArmoryDetailDto));
    }

    /**
     * 智能裁决-棋子选择详情
     *
     * @param queryVerdictChessPiecesDetailDto
     * @return
     */
    @PostMapping("/queryVerdictChessPiecesDetail")
    public R<BizChessPiecesVo> queryVerdictChessPiecesDetail(@RequestBody BizChessPiecesBo queryVerdictChessPiecesDetailDto) {
        return R.ok(chessPiecesService.queryById(queryVerdictChessPiecesDetailDto.getId()));
    }

    /**
     * 智能裁决-选择棋子
     *
     * @param saveVerdictChessPiecesDto
     * @return
     */
    @PostMapping("/saveVerdictChessPieces")
    public R<String> saveVerdictChessPieces(@RequestBody BizChessPiecesBo saveVerdictChessPiecesDto) {
        return R.ok(verdictRecordService.saveVerdictChessPieces(saveVerdictChessPiecesDto));
    }

    @PostMapping("/saveScenarioChessPieces")
    public R<String> saveScenarioChessPieces(@RequestBody BizChessPiecesBo saveScenarioChessPiecesDto) {
        return R.ok(scenarioRecordService.saveScenarioChessPieces(saveScenarioChessPiecesDto));
    }

    /**
     * 生成邀请码
     *
     * @return
     */
    @GetMapping("/generateInviteCode/{verdictRecordId}")
    public R<String> generateInviteCode(@PathVariable("verdictRecordId") Long verdictRecordId) {
        String inviteCode = verdictRecordService.generateInviteCode(verdictRecordId);
        return R.ok(inviteCode);
    }

    /**
     * 加入房间
     *
     * @return
     */
    @PostMapping("/joinRoom/{inviteCode}/{type}")
    public R<BizVerdictRecordVo> joinRoom(@PathVariable("inviteCode") String inviteCode, @PathVariable("type") String type) {
        String verdictRecordId = verdictRecordService.joinRoom(inviteCode, type);
        return R.ok(verdictRecordId);
    }

    @PostMapping("/queryVerdictRecordByInviteCode")
    public R<BizVerdictRecord> queryVerdictRecordByInviteCode(@RequestParam("inviteCode") String inviteCode) {
        return R.ok(verdictRecordService.queryVerdictRecordByInviteCode(inviteCode));
    }

    /**
     * 邀请人输入邀请码-用以邀请他人参赛
     *
     * @param inviteFriendDto
     * @return
     */
    @PostMapping("/inviteFriend")
    public R<String> inviteFriend(@RequestBody BizVerdictRecordBo inviteFriendDto) {
        return R.ok(verdictRecordService.inviteFriend(inviteFriendDto));
    }

    /**
     * 响应参赛邀请
     *
     * @param responseInviteDto
     * @return
     */
    @PostMapping("/responseInvite")
    public R<BizVerdictRecordVo> responseInvite(@RequestBody BizVerdictRecordBo responseInviteDto) {
        return R.ok(verdictRecordService.responseInvite(responseInviteDto));
    }

    /**
     * 角色选择-查询棋子阵营
     *
     * @param queryCampDto
     * @return
     */
    @PostMapping("/queryCampList")
    public R<List<BizChessPiecesCampVo>> queryCampList(@RequestBody BizChessPiecesCampBo queryCampDto) {
        return R.ok(verdictRecordService.queryCampList(queryCampDto));
    }

    /**
     * 选择阵营
     *
     * @param chooseCampDto
     * @return
     */
    @PostMapping("/chooseCamp")
    public R<BizChessPiecesCampVo> chooseCamp(@RequestBody BizChessPiecesCampBo chooseCampDto) {
        return R.ok(verdictRecordService.chooseCamp(chooseCampDto));
    }

    /**
     * 进入裁决详情
     *
     * @param dto
     * @return
     */
    @PostMapping("/goVerdictRecordDetail")
    public R<List<BizVerdictRecordDetailVo>> goVerdictRecordDetail(@RequestBody BizVerdictRecordDetailBo dto) {
        return R.ok(verdictRecordDetailService.goVerdictRecordDetail(dto));
    }

    /**
     * 目标棋子编号列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/targetChessPiecesNumberList")
    public R<List<String>> targetChessPiecesNumberList(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordDetailService.targetChessPiecesNumberList(dto));
    }

    /**
     * 目标棋子详情
     *
     * @param dto
     * @return
     */
    @PostMapping("/targetChessPiecesDetail")
    public R<BizChessPiecesVo> targetChessPiecesDetail(@RequestBody BizChessPiecesBo dto) {
        return R.ok(verdictRecordDetailService.targetChessPiecesDetail(dto));
    }

    /**
     * 裁决
     *
     * @param dto
     * @return
     */
    @PostMapping("/verdict")
    public R<BizVerdictRecordDetailVo> verdict(@RequestBody BizVerdictRecordDetailBo dto) {
        return R.ok(verdictRecordDetailService.verdict(dto));
    }

    /**
     * 开始游戏
     *
     * @param startGameDto
     * @return
     */
    @PostMapping("/startGame")
    public R<String> startGame(@RequestBody BizVerdictRecordBo startGameDto) {
        return R.ok(verdictRecordService.startGame(startGameDto));
    }

    /**
     * 查询游戏状态
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryGameStatus")
    public R<BizVerdictRecordVo> queryGameStatus(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordService.queryGameStatus(dto));
    }

    /**
     * 查询房间内用户信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryRoomUser")
    public R<BizVerdictRecordVo> queryRoomUser(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordService.queryRoomUser(dto));
    }

    /**
     * 查询裁决ID
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryVerdictId")
    public R<BizVerdictRecordVo> queryVerdictId(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordService.queryVerdictId(dto));
    }

    /**
     * 部署棋子
     *
     * @param dto
     * @return
     */
    @PostMapping("/deployChessPieces")
    public R<String> deployChessPieces(@RequestBody BizVerdictRecordHistoryBo dto) {
        return R.ok(verdictRecordHistoryService.deployChessPieces(dto));
    }

    /**
     * 想定部署棋子
     *
     * @param dto
     * @return
     */
    @PostMapping("/deployScenarioChessPieces")
    public R<String> deployScenarioChessPieces(@RequestBody BizScenarioRecordHistoryBo dto) {
        return R.ok(scenarioRecordService.deployScenarioChessPieces(dto));
    }


    /**
     * 结束部署
     *
     * @param dto
     * @return
     */
    @PostMapping("/endDeploy")
    public R<String> endDeploy(@RequestBody BizVerdictRecordHistoryBo dto) {
        return R.ok(verdictRecordHistoryService.endDeploy(dto));
    }

    /**
     * 查询回合数
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryChessRound")
    public R<BizVerdictRecordVo> queryChessRound(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordService.queryChessRound(dto));
    }

    /**
     * 是否自己回合
     *
     * @param dto
     * @return
     */
    @PostMapping("/isOwnRound")
    public R<Boolean> isOwnRound(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.isOwnRound(dto));
    }

    /**
     * 棋子行动
     *
     * @param dto
     * @return
     */
    @PostMapping("/chessPiecesAction")
    public R<BizIndirectBlowEffectVo> chessPiecesAction(@RequestBody BizVerdictRecordDetailBo dto) {
        return R.ok(verdictRecordHistoryService.chessPiecesAction(dto));
    }

    @PostMapping("/chessPiecesActionNew")
    public R<Void> chessPiecesActionNew(@RequestBody BizVerdictRecordDetailBo dto) {
        verdictRecordHistoryService.chessPiecesActionNew(dto);
        return R.ok();
    }

    @PostMapping("/changePiecesActionPoint")
    public R<Void> changePiecesActionPoint(@RequestBody BizVerdictRecordHistoryBo dto) {
        log.info("changePiecesActionPoint", JSON.toJSONString(dto));
//        BizVerdictRecordDetailBo tmp=dto;
//        BizVerdictRecordHistoryBo tmp=new BizVerdictRecordHistoryBo();
        List<BizVerdictRecordHistoryBo> bizVerdictRecordHistoryList = verdictRecordHistoryService.selectChessPieceByVerdictRecordId(dto);
        // 要按照回合设置不能只依靠VerdictRecordId
        for (int i = 0; i < bizVerdictRecordHistoryList.size(); i++) {
            BizVerdictRecordChessPiecesBo chessPiece = new BizVerdictRecordChessPiecesBo();
            chessPiece.setVerdictRecordId(bizVerdictRecordHistoryList.get(i).getVerdictRecordId());
            chessPiece.setChessPiecesNumber(bizVerdictRecordHistoryList.get(i).getChessPiecesNumber());
            if (bizVerdictRecordHistoryList.get(i).getActionMode() == 10) {
                chessPiece.setOffset(bizVerdictRecordHistoryList.get(i).getTargetOffset());
            } else {
                chessPiece.setOffset(bizVerdictRecordHistoryList.get(i).getSelfOffset());
            }

            // 移动阶段 selfoffset是原始位置，移动后变成了fromoffset 应该使用targetoffset匹配
            verdictRecordChessPiecesService.actionPointChange(chessPiece);
        }

        return R.ok();
    }

    @PostMapping("/resetPiecesActionPoint")
    public R<Void> resetPiecesActionPoint(@RequestBody BizVerdictRecordChessPiecesBo dto) {
        log.info("resetPiecesActionPoint", JSON.toJSONString(dto));
//        BizVerdictRecordDetailBo tmp=dto;
//        verdictRecordHistoryService.chessPiecesActionNew(dto);
        verdictRecordChessPiecesService.actionPointReset(dto);
        return R.ok();
    }


    @PostMapping("/chessPiecesActionContinue")
    public R<Void> chessPiecesActionContinue(@RequestBody BizVerdictRuleBo dto) {
        verdictRecordHistoryService.chessPiecesActionContinue(dto);
        return R.ok();
    }

    /**
     * 查询每步行动
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryEachAction")
    public R<QueryEachActionVo> queryEachAction(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.queryEachAction(dto));
    }

    /**
     * 查询攻击效果
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryActionEffect")
    public R<ChessRoundActionEffectVo> queryActionEffect(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.queryActionEffect(dto));
    }

    /**
     * 查询比赛结果
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryVerdictResult")
    public R<QueryVerdictResultVo> queryVerdictResult(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.queryVerdictResult(dto));
    }

    /**
     * 结束回合
     *
     * @param dto
     * @return
     */
    @PostMapping("/endRound")
    public R<String> endRound(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.endRound(dto));
    }

    /**
     * 查询提示信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryPrompt")
    public R<QueryPromptVo> queryPrompt(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.queryPrompt(dto));
    }

    /**
     * 结束游戏
     *
     * @param dto
     * @return
     */
    @PostMapping("/endGame")
    public R<String> endGame(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.endGame(dto));
    }

    /**
     * 查询棋子位置及状态等信息-根据单个棋子查询
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryChessPiecesInfo")
    public R<BizVerdictRecordChessPieces> queryChessPiecesInfo(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordChessPiecesService.queryChessPiecesInfo(dto));
    }

    /**
     * 查询裁决中所有棋子位置及状态等信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryAllChessPiecesInfo")
    public R<List<BizVerdictRecordChessPieces>> queryAllChessPiecesInfo(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordChessPiecesService.queryAllChessPiecesInfo(dto));
    }

    /**
     * 插入对应的创建想定所有棋子位置及状态等信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryAllChessPiecesInfoC")
    public R<List<BizScenarioRecordChessPieces>> queryAllChessPiecesInfoC(@RequestBody QueryAllScenarioChessPiecesInfoDto dto) {
        return R.ok(scenarioRecordService.queryAllChessPiecesInfo(dto));
    }

    /**
     * 插入对应的创建想定所有棋子位置及状态等信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/creatVerdictRoom")
    public R<String> creatVerdictRoom(@RequestBody CreatVerdictRoomInfoDto dto) {
        return R.ok(scenarioRecordService.CreatVerdictRoomInfoDto(dto));
    }

    /**
     * 查询裁决中我方棋子信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryUsChessPiecesInfo")
    public R<List<BizVerdictRecordChessPieces>> queryUsChessPiecesInfo(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordChessPiecesService.queryUsChessPiecesInfo(dto));
    }

    @PostMapping("/queryChessRecordByRound")
    public R<List<BizVerdictRecordHistoryVo>> queryChessRecordByRound(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordChessPiecesService.queryChessRecordByRound(dto));
    }


    /**
     * 查询地图信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/queryMapInfo")
    @SaIgnore
    public R<BizArbiterMapVo> queryMapInfo(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordChessPiecesService.queryMapInfo(dto));
    }

    /**
     * 复盘列表
     *
     * @param search
     * @return
     */
    @GetMapping("/queryUnfinishedList")
    public R<TableDataInfo<QueryVerdictRecordDetail>> queryUnfinishedList(BizVerdictRecordBo search, PageQuery pageQuery) {
        return R.ok(verdictRecordService.queryUnfinishedList(search, pageQuery));
    }

    /**
     * 复盘列表
     *
     * @param search
     * @return
     */
    @GetMapping("/queryVerdictRecordList")
    public R<TableDataInfo<QueryVerdictRecordDetail>> queryVerdictRecordList(BizVerdictRecordBo search, PageQuery pageQuery) {
        return R.ok(verdictRecordService.queryVerdictRecordList(search, pageQuery));
    }

    /**
     * 棋子行动历史复盘
     *
     * @param dto
     * @return
     */
    @PostMapping("/chessPiecesActionHistory")
    public R<Map<Long, List<BizVerdictRecordHistory>>> chessPiecesActionHistory(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordHistoryService.chessPiecesActionHistory(dto));
    }

    /**
     * 全局视野
     *
     * @param dto
     * @return
     */
    @PostMapping("/globalVision")
    public R<List<BizVerdictRecordChessPieces>> globalVision(@RequestBody BizVerdictRecordBo dto) {
        return R.ok(verdictRecordChessPiecesService.globalVision(dto));
    }

    @PostMapping("/getRuleList")
    public R<List<BizVerdictRuleVo>> getRuleList(@RequestBody BizVerdictRuleBo dto) {
        return R.ok(verdictRuleService.getRuleList(dto));
    }

    @GetMapping("getDice/{diceCount}")
    public R<int[]> getDice(@PathVariable("diceCount") Integer diceCount) {
        return R.ok(verdictRecordService.getDice(diceCount));
    }

    @GetMapping("nextStage/{id}")
    public R<Void> nextStage(@PathVariable("id") Long id) {
        verdictRecordService.nextStage(id);
        return R.ok();
    }

    @PostMapping("/getBlowEffectList")
    public R<List<BizVerdictBlowEffectVo>> getBlowEffectList(@RequestBody BizVerdictBlowEffectBo dto) {
        return R.ok(verdictBlowEffectService.getBlowEffectList(dto));
    }


    @PostMapping("/submitScore")
    @RepeatSubmit
    public R<Void> submitScore(@RequestBody List<BizVerdictRecordScore> bos) {
        verdictRecordScoreService.submitScore(bos);
        return R.ok();
    }

    /**
     * 获取查分结果
     *
     * @param bo
     * @return
     */
    @PostMapping("/queryScoreList")
    public R<List<BizVerdictRecordScoreVo>> queryScoreList(@RequestBody BizVerdictRecordScoreBo bo) {
        return R.ok(verdictRecordScoreService.queryScoreList(bo));
    }

    /**
     * 查询实时分数
     *
     * @param bo
     * @return
     */
    @PostMapping("/getRealTimeScore")
    public R<List<BizVerdictRecordScoreVo>> getRealTimeScore(@RequestBody BizVerdictRecordScoreBo bo) {
        return R.ok(verdictRecordScoreService.getRealTimeScore(bo));
    }

    @PostMapping("/getSummaryScore")
    public R<List<BizVerdictRecordScoreVo>> getSummaryScore(@RequestBody BizVerdictRecordScoreBo bo) {
        return R.ok(verdictRecordScoreService.getSummaryScore(bo));
    }

    @PostMapping("/getSummaryScoreNew")
    public R<List<BizVerdictRecordScoreVo>> getSummaryScoreNew(@RequestBody BizVerdictRecordScoreBo bo) {
        return R.ok(verdictRecordScoreService.getSummaryScoreNew(bo));
    }

    @PostMapping("/getSummaryTotal")
    public R<List<BizVerdictRecordScoreVo>> getSummaryTotal(@RequestBody BizVerdictRecordScoreBo bo) {
        return R.ok(verdictRecordScoreService.getSummaryTotal(bo));
    }

    /**
     * 查询回合提交状态
     *
     * @param bo
     * @return
     */
    @PostMapping("/judge")
    public R<List<JudgeVo>> judge(@RequestBody BizVerdictRecordRoundBo bo) {
        return R.ok(verdictRecordHistoryService.judge(bo));
    }


    @PostMapping("/confirmJudge")
    public R<Long> confirmJudge(@RequestBody BizVerdictRecordHistoryBo bo) {
        return R.ok(verdictRecordHistoryService.confirmJudge(bo));
    }


    //    计算回合最终评分
    //    根据裁决id以及回合标志以及阵营id查询计算信息
    @PostMapping("/calculateRoundScore")
    public R<Void> calculateRoundScore(@RequestBody BizVerdictRecordHistoryBo bo) {
        return R.ok(verdictRecordHistoryService.calculateRoundScore(bo));
    }


    @PostMapping("/getAwaitJudge")
    public R<List<BizVerdictRecordHistoryVo>> getAwaitJudge(@RequestBody BizVerdictRecordRoundBo bo) {
        return R.ok(verdictRecordHistoryService.getAwaitJudge(bo));
    }

    /**
     * 查询回合提交状态
     *
     * @param bo
     * @return
     */
    @PostMapping("/getRoundStatus")
    public R<List<BizVerdictRecordRoundVo>> getRoundStatus(@RequestBody BizVerdictRecordRoundBo bo) {
        return R.ok(verdictRecordRoundService.getRoundStatus(bo));
    }

    @PostMapping("/saveRoundCover")
    public R<Void> saveRoundCover(@RequestBody BizVerdictRecordRoundBo bo) {
        return toAjax(verdictRecordRoundService.saveRoundCover(bo));
    }

    @PostMapping("/getHistoryTreeByRound")
    public R<List<BizVerdictRecordRoundVo>> getHistoryTreeByRound(@RequestBody BizVerdictRecordRoundBo bo) {
        return R.ok(verdictRecordRoundService.getHistoryTreeByRound(bo));
    }


    @PostMapping("/exportOffsetAndCoordinate")
    @SaIgnore
    public void exportOffsetAndCoordinate(HttpServletResponse response) {
        arbiterMapService.exportOffsetAndCoordinate(response);
    }

    @PostMapping("/getMapCoordinate")
    public R<Map<String, BizArbiterMapCoordinateVo>> getMapCoordinate(@RequestBody QueryAllChessPiecesInfoDto bo) {
        Long mapId = verdictRecordService.getMapId(Long.valueOf(bo.getVerdictRecordId()));
        return R.ok(arbiterMapService.getCoordinateMap(mapId));
    }

    @PostMapping("/getMapCoordinateC")
    public R<Map<String, BizArbiterMapCoordinateVo>> getMapCoordinateC(@RequestBody QueryAllChessPiecesInfoByScenarioIdDto dto) {
        Long mapId = Long.valueOf(dto.getScenarioId());
        return R.ok(arbiterMapService.getCoordinateMap(mapId));
    }


    @PostMapping("/getMapChessImage")
    public R<Map<String, String>> getMapChessImage(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordChessPiecesService.getMapChessImage(Long.valueOf(dto.getVerdictRecordId())));
    }

    @PostMapping("/getMapChessImageC")
    public R<Map<String, String>> getMapChessImageC(@RequestBody QueryAllChessPiecesInfoByScenarioIdDto dto) {
        return R.ok(verdictRecordChessPiecesService.getMapChessImageC(Long.valueOf(dto.getScenarioId())));
    }


    @PostMapping("/getMapId")
    public R<Long> getMapId(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordService.getMapId(Long.valueOf(dto.getVerdictRecordId())));
    }

    @PostMapping("/queryMapByProductId")
    public R<List<BizArbiterMapVo>> queryMapByProductId(@RequestBody BizArbiterMapBo bo) {
        return R.ok(arbiterMapService.queryMapByProductId(bo.getProductId()));
    }

    @PostMapping("/queryMaparbiterMapId")
    public R<Long> queryMaparbiterMapId(@RequestBody queryMaparbiterMapIdDto dto) {
        return R.ok(scenarioCreateService.getArbiterMapIdById(Long.valueOf(dto.getScenarioId())));
    }

    @PostMapping("/queryScenarioIdByVerdictRecordId")
    public R<Long> queryScenarioIdByVerdictRecordId(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordService.queryScenarioIdByVerdictRecordId(Long.valueOf(dto.getVerdictRecordId())));
    }

    @PostMapping("/getMap")
    public R<BizArbiterMap> getMap(@RequestBody QueryAllChessPiecesInfoDto dto) {
        return R.ok(verdictRecordService.getMap(Long.valueOf(dto.getVerdictRecordId())));
    }

    @PostMapping("/getMapC")
    public R<BizArbiterMap> getMapC(@RequestBody QueryAllChessPiecesInfoByScenarioIdDto dto) {
        return R.ok(verdictRecordService.getMapC(dto.getScenarioId()));
    }

    @PostMapping("/changeChessStatus")
    public R<String> changeChessStatus(@RequestBody BizVerdictRecordChessPiecesBo dto) {
        return R.ok(verdictRecordChessPiecesService.changeChessStatus(dto));
    }

    @PostMapping("/stepJudge")
    public R<String> stepJudge(@RequestBody BizVerdictRecordRoundBo bo) {
        return R.ok(verdictRecordRoundService.stepJudge(bo));
    }


    @PostMapping("/createScenario")
    public R<BizScenarioCreate> createScenario(@RequestBody BizScenarioCreateBo bo) {
        BizScenarioCreate scenarioCreate = bizScenarioCreateService.createScenario(bo);
        return R.ok(scenarioCreate);
    }

    @PostMapping("/delScenario")
    public R<String> delScenario(@RequestBody BizScenarioCreateBo bo) {
        BizScenarioCreate scenarioCreate = bizScenarioCreateService.deleteScenario(bo);
        return R.ok();
    }

    @PostMapping("/setScore")
    public R<String> setScore(@RequestParam Long id, @RequestParam Long firstScore, @RequestParam Long secondScore) {
        return R.ok(verdictRecordService.setScore(id, firstScore, secondScore));
    }

    @PostMapping("/getScoreByCampId")
    public R<Long> getScoreByCampId(@RequestParam Long id, @RequestParam Long CampId) {
        return R.ok(verdictRecordService.getScoreByCampId(id, CampId));
    }

    @PostMapping("/moveChess")
    public R<String> moveChess(@RequestBody BizVerdictRecordChessPiecesBo dto) {

//        BizVerdictRecordChessPiecesVo tmp=verdictRecordChessPiecesService.queryById(id);
        BizVerdictRecordChessPiecesBo tmp = new BizVerdictRecordChessPiecesBo();
        tmp.setId(Long.valueOf(dto.getId()));
        tmp.setOffset(dto.getOffset());
        tmp.setCoordinate(dto.getCoordinate());
        verdictRecordChessPiecesService.updateByBo(tmp);
        return R.ok();
    }

    /**
     * 保存文本指令
     *
     * @param bo 包含roundId和textIns的对象
     * @return 成功返回success
     */
    @PostMapping("/saveTextInstruction")
    public R<Void> saveTextInstruction(@RequestBody BizVerdictRecordRoundBo bo) {
        return toAjax(verdictRecordRoundService.saveTextInstruction(bo));
    }

}
