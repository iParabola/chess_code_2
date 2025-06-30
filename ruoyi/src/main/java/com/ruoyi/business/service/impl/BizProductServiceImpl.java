package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.business.domain.vo.BizScenarioVo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizProductBo;
import com.ruoyi.business.domain.vo.BizProductVo;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.mapper.BizProductMapper;
import com.ruoyi.business.service.IBizProductService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 产品管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
public class BizProductServiceImpl implements IBizProductService {

    private final BizProductMapper baseMapper;

    /**
     * 查询产品管理
     */
    @Override
    public BizProductVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询产品管理列表
     */
    @Override
    public TableDataInfo<BizProductVo> queryPageList(BizProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizProduct> lqw = buildQueryWrapper(bo);
        Page<BizProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询产品管理列表
     */
    @Override
    public List<BizProductVo> queryList(BizProductBo bo) {
        LambdaQueryWrapper<BizProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizProduct> buildQueryWrapper(BizProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizProduct> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), BizProduct::getProductName, bo.getProductName());
        lqw.eq(bo.getCreateTime() != null, BizProduct::getCreateTime, bo.getCreateTime());
        lqw.orderByDesc(BizProduct::getId);
        return lqw;
    }

    /**
     * 新增产品管理
     */
    @Override
    public Boolean insertByBo(BizProductBo bo) {
        BizProduct add = BeanUtil.toBean(bo, BizProduct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改产品管理
     */
    @Override
    public Boolean updateByBo(BizProductBo bo) {
        BizProduct update = BeanUtil.toBean(bo, BizProduct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizProduct entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除产品管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }



}
