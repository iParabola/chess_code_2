package com.ruoyi.app.verdicttype.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.app.verdicttype.domain.BizVerdictType;
import com.ruoyi.app.verdicttype.domain.vo.BizVerdictTypeVo;
import com.ruoyi.app.verdicttype.domain.bo.BizVerdictTypeBo;

import java.util.Collection;
import java.util.List;

/**
 * 裁决类型Service接口
 *
 * @author ruoyi
 * @date 2024-08-10
 */
public interface IBizVerdictTypeService {

    /**
     * 查询裁决类型
     */
    BizVerdictTypeVo queryById(Long id);


    /**
     * 查询裁决类型列表
     */
    List<BizVerdictTypeVo> queryList(BizVerdictTypeBo bo);

    /**
     * 新增裁决类型
     */
    Boolean insertByBo(BizVerdictTypeBo bo);

    /**
     * 修改裁决类型
     */
    Boolean updateByBo(BizVerdictTypeBo bo);

    /**
     * 校验并批量删除裁决类型信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /***
     * 获取裁决树
     */
    List<Tree<Long>> selectBizVerdictTreeList(BizVerdictType bizVerdictType);

    /***
     * 查询裁决数据
     * @param bizVerdictType
     * @return
     */
    public List<BizVerdictType> selectBizVerdictList(BizVerdictType bizVerdictType);

    /***
     * 构建裁决树
     * @param bizVerdictTypes
     * @return
     */
    public List<Tree<Long>> buildBizVerdictSelect(List<BizVerdictType> bizVerdictTypes);
}
