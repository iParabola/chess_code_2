package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictRecordRound;
import com.ruoyi.business.domain.vo.BizVerdictRecordRoundVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordRoundBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 裁决记录回合管理Service接口
 *
 * @author ruoyi
 * @date 2024-03-07
 */
public interface IBizVerdictRecordRoundService {


    Long getIdByRecordIdAndCampId(Long verdictRecordId, Long campId);
    /**
     * 查询裁决记录回合管理
     */
    BizVerdictRecordRoundVo queryById(Long id);

    /**
     * 查询裁决记录回合管理列表
     */
    TableDataInfo<BizVerdictRecordRoundVo> queryPageList(BizVerdictRecordRoundBo bo, PageQuery pageQuery);

    /**
     * 查询裁决记录回合管理列表
     */
    List<BizVerdictRecordRoundVo> queryList(BizVerdictRecordRoundBo bo);

    /**
     * 新增裁决记录回合管理
     */
    Boolean insertByBo(BizVerdictRecordRoundBo bo);

    /**
     * 修改裁决记录回合管理
     */
    Boolean updateByBo(BizVerdictRecordRoundBo bo);

    /**
     * 校验并批量删除裁决记录回合管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 如果没有操作步骤，则跳过裁决
     */
    String stepJudge(BizVerdictRecordRoundBo bo);


    List<BizVerdictRecordRoundVo> getRoundStatus(BizVerdictRecordRoundBo bo);


    Boolean saveRoundCover(BizVerdictRecordRoundBo bo);


    List<BizVerdictRecordRoundVo> getHistoryTreeByRound(BizVerdictRecordRoundBo bo);

    Boolean saveTextInstruction(BizVerdictRecordRoundBo bo);
}
