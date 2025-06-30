package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizIndirectRecord;
import com.ruoyi.business.domain.vo.BizIndirectRecordVo;
import com.ruoyi.business.domain.bo.BizIndirectRecordBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 间瞄记录Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizIndirectRecordService {

    /**
     * 查询间瞄记录
     */
    BizIndirectRecordVo queryById(Long id);

    /**
     * 查询间瞄记录列表
     */
    TableDataInfo<BizIndirectRecordVo> queryPageList(BizIndirectRecordBo bo, PageQuery pageQuery);

    /**
     * 查询间瞄记录列表
     */
    List<BizIndirectRecordVo> queryList(BizIndirectRecordBo bo);

    /**
     * 新增间瞄记录
     */
    Boolean insertByBo(BizIndirectRecordBo bo);

    /**
     * 修改间瞄记录
     */
    Boolean updateByBo(BizIndirectRecordBo bo);

    /**
     * 校验并批量删除间瞄记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
