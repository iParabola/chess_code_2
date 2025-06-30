package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizScenario;
import com.ruoyi.business.domain.bo.BizProductBo;
import com.ruoyi.business.domain.vo.BizScenarioVo;
import com.ruoyi.business.domain.bo.BizScenarioBo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 想定管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizScenarioService {

    /**
     * 查询想定管理
     */
    BizScenarioVo queryById(Long id);

    /**
     * 查询想定管理列表
     */
    TableDataInfo<BizScenarioVo> queryPageList(BizScenarioBo bo, PageQuery pageQuery);

    /**
     * 查询想定管理列表
     */
    List<BizScenarioVo> queryList(BizScenarioBo bo);

    /**
     * 新增想定管理
     */
    Boolean insertByBo(BizScenarioBo bo);

    /**
     * 修改想定管理
     */
    Boolean updateByBo(BizScenarioBo bo);

    /**
     * 校验并批量删除想定管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    List<BizScenarioVo> queryVerdictScenarioVos( BizProductBo queryArmoryDetailDto);


}
