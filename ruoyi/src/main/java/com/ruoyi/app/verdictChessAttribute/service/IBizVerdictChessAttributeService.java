package com.ruoyi.app.verdictChessAttribute.service;

import com.ruoyi.app.verdictChessAttribute.domain.vo.BizVerdictChessAttributeVo;
import com.ruoyi.app.verdictChessAttribute.domain.bo.BizVerdictChessAttributeBo;
import com.ruoyi.business.domain.dto.BatchSaveVerdictChessAttributeDto;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 裁决棋子属性表Service接口
 *
 * @author ruoyi
 * @date 2024-08-21
 */
public interface IBizVerdictChessAttributeService {

    /**
     * 查询裁决棋子属性表
     */
    BizVerdictChessAttributeVo queryById(Long id);

    /**
     * 查询裁决棋子属性表列表
     */
    TableDataInfo<BizVerdictChessAttributeVo> queryPageList(BizVerdictChessAttributeBo bo, PageQuery pageQuery);

    /**
     * 查询裁决棋子属性表列表
     */
    List<BizVerdictChessAttributeVo> queryList(BizVerdictChessAttributeBo bo);

    /**
     * 新增裁决棋子属性表
     */
    Boolean insertByBo(BizVerdictChessAttributeBo bo);

    /**
     * 修改裁决棋子属性表
     */
    Boolean updateByBo(BizVerdictChessAttributeBo bo);

    /**
     * 校验并批量删除裁决棋子属性表信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 批量引用棋子属性定义
     * @param batchSaveVerdictChessAttributeDto
     * @return
     */
    String batchSaveVerdictChessAttribute(BatchSaveVerdictChessAttributeDto batchSaveVerdictChessAttributeDto);
}
