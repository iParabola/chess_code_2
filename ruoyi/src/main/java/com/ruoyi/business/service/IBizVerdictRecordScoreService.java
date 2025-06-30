package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizVerdictRecordScore;
import com.ruoyi.business.domain.vo.BizVerdictRecordScoreVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordScoreBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 裁决记录分数管理Service接口
 *
 * @author ruoyi
 * @date 2024-03-04
 */
public interface IBizVerdictRecordScoreService {

    /**
     * 查询裁决记录分数管理
     */
    BizVerdictRecordScoreVo queryById(Long id);

    /**
     * 查询裁决记录分数管理列表
     */
    TableDataInfo<BizVerdictRecordScoreVo> queryPageList(BizVerdictRecordScoreBo bo, PageQuery pageQuery);

    /**
     * 查询裁决记录分数管理列表
     */
    List<BizVerdictRecordScoreVo> queryList(BizVerdictRecordScoreBo bo);

    /**
     * 新增裁决记录分数管理
     */
    Boolean insertByBo(BizVerdictRecordScoreBo bo);

    /**
     * 修改裁决记录分数管理
     */
    Boolean updateByBo(BizVerdictRecordScoreBo bo);

    /**
     * 校验并批量删除裁决记录分数管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void submitScore(List<BizVerdictRecordScore> bos);

    List<BizVerdictRecordScoreVo> queryScoreList(BizVerdictRecordScoreBo bos);

    List<BizVerdictRecordScoreVo> getRealTimeScore( BizVerdictRecordScoreBo bo);

    List<BizVerdictRecordScoreVo> getSummaryScore( BizVerdictRecordScoreBo bo);

    List<BizVerdictRecordScoreVo> getSummaryScoreNew( BizVerdictRecordScoreBo bo);

    List<BizVerdictRecordScoreVo> getSummaryTotal( BizVerdictRecordScoreBo bo);

}
