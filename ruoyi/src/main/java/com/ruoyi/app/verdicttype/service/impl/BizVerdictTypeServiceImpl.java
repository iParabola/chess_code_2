package com.ruoyi.app.verdicttype.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.TreeBuildUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.app.verdicttype.domain.bo.BizVerdictTypeBo;
import com.ruoyi.app.verdicttype.domain.vo.BizVerdictTypeVo;
import com.ruoyi.app.verdicttype.domain.BizVerdictType;
import com.ruoyi.app.verdicttype.mapper.BizVerdictTypeMapper;
import com.ruoyi.app.verdicttype.service.IBizVerdictTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决类型Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-10
 */
@RequiredArgsConstructor
@Service
public class BizVerdictTypeServiceImpl implements IBizVerdictTypeService {

    private final BizVerdictTypeMapper baseMapper;

    /**
     * 查询裁决类型
     */
    @Override
    public BizVerdictTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }


    /**
     * 查询裁决类型列表
     */
    @Override
    public List<BizVerdictTypeVo> queryList(BizVerdictTypeBo bo) {
        LambdaQueryWrapper<BizVerdictType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictType> buildQueryWrapper(BizVerdictTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictType> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizVerdictType::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), BizVerdictType::getCode, bo.getCode());
        lqw.eq(BizVerdictType::getDelFlag, "0");
        lqw.eq(bo.getProductId() != null, BizVerdictType::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), BizVerdictType::getProductName, bo.getProductName());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictType::getExt, bo.getExt());
        lqw.eq(bo.getParentId() != null, BizVerdictType::getParentId, bo.getParentId());
        return lqw;
    }

    /**
     * 新增裁决类型
     */
    @Override
    public Boolean insertByBo(BizVerdictTypeBo bo) {
        BizVerdictType add = BeanUtil.toBean(bo, BizVerdictType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决类型
     */
    @Override
    public Boolean updateByBo(BizVerdictTypeBo bo) {
        BizVerdictType update = BeanUtil.toBean(bo, BizVerdictType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<Tree<Long>> selectBizVerdictTreeList(BizVerdictType bizVerdictType) {
        List<BizVerdictType> bizVerdictTypes = this.selectBizVerdictList(bizVerdictType);
        return buildBizVerdictSelect(bizVerdictTypes);
    }
    @Override
    public List<BizVerdictType> selectBizVerdictList(BizVerdictType bizVerdictType){
        LambdaQueryWrapper<BizVerdictType> lqw = new LambdaQueryWrapper<BizVerdictType>();
                lqw.eq(BizVerdictType::getDelFlag, "0");
                lqw.like(StringUtils.isNotBlank(bizVerdictType.getName()), BizVerdictType::getName,bizVerdictType.getName());
                lqw.eq(StringUtils.isNotBlank(bizVerdictType.getProductId().toString()),BizVerdictType::getProductId,bizVerdictType.getProductId());
                return baseMapper.selectBizVerdictList(lqw);
    }
    /**
        * 构建前端所需要下拉树结构
        * @param bizVerdictTypes 裁决类型列表
        * @return 下拉树结构列表
    */
    @Override
    public List<Tree<Long>> buildBizVerdictSelect(List<BizVerdictType> bizVerdictTypes) {
           if (CollUtil.isEmpty(bizVerdictTypes)) {
               return CollUtil.newArrayList();
           }
           return TreeBuildUtils.build(bizVerdictTypes, (bizVerdictType, tree) ->
               tree.setId(bizVerdictType.getId())
                   .setParentId(bizVerdictType.getParentId())
                   .setName(bizVerdictType.getName()));
       }
}
