package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizScenarioStage;
import com.ruoyi.business.domain.vo.BizScenarioStageVo;
import com.ruoyi.business.domain.bo.BizScenarioStageBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 阶段管理Service接口
 *
 * @author ruoyi
 * @date 2024-03-01
 */
public interface IBizScenarioStageService {

    /**
     * 查询阶段管理
     */
    BizScenarioStageVo queryById(Long id);

    /**
     * 查询阶段管理列表
     */
    TableDataInfo<BizScenarioStageVo> queryPageList(BizScenarioStageBo bo, PageQuery pageQuery);

    /**
     * 查询阶段管理列表
     */
    List<BizScenarioStageVo> queryList(BizScenarioStageBo bo);

    /**
     * 新增阶段管理
     */
    Boolean insertByBo(BizScenarioStageBo bo);

    /**
     * 修改阶段管理
     */
    Boolean updateByBo(BizScenarioStageBo bo);

    /**
     * 校验并批量删除阶段管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
