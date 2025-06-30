package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.domain.vo.BizProductVo;
import com.ruoyi.business.domain.bo.BizProductBo;
import com.ruoyi.business.domain.vo.BizScenarioVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

/**
 * 产品管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizProductService {

    /**
     * 查询产品管理
     */
    BizProductVo queryById(Long id);

    /**
     * 查询产品管理列表
     */
    TableDataInfo<BizProductVo> queryPageList(BizProductBo bo, PageQuery pageQuery);

    /**
     * 查询产品管理列表
     */
    List<BizProductVo> queryList(BizProductBo bo);

    /**
     * 新增产品管理
     */
    Boolean insertByBo(BizProductBo bo);

    /**
     * 修改产品管理
     */
    Boolean updateByBo(BizProductBo bo);

    /**
     * 校验并批量删除产品管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
