package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.business.domain.dto.BizChessPiecesAttributeDto;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizChessPiecesAttributeBo;
import com.ruoyi.business.domain.vo.BizChessPiecesAttributeVo;
import com.ruoyi.business.domain.BizChessPiecesAttribute;
import com.ruoyi.business.mapper.BizChessPiecesAttributeMapper;
import com.ruoyi.business.service.IBizChessPiecesAttributeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 棋子属性定义Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@RequiredArgsConstructor
@Service
public class BizChessPiecesAttributeServiceImpl implements IBizChessPiecesAttributeService {

    private final BizChessPiecesAttributeMapper baseMapper;

    /**
     * 查询棋子属性定义
     */
    @Override
    public BizChessPiecesAttributeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询棋子属性定义列表
     */
    @Override
    public TableDataInfo<BizChessPiecesAttributeVo> queryPageList(BizChessPiecesAttributeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizChessPiecesAttribute> lqw = buildQueryWrapper(bo);
        Page<BizChessPiecesAttributeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询棋子属性定义列表
     */
    @Override
    public List<BizChessPiecesAttributeVo> queryList(BizChessPiecesAttributeBo bo) {
        LambdaQueryWrapper<BizChessPiecesAttribute> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询裁决棋子表列表
     */
    @Override
    public List<BizChessPiecesAttribute> queryDtoList(BizChessPiecesAttributeDto bo) {
        Long productId = bo.getProductId();
        Long chessTypeId = bo.getChessTypeId();
        return baseMapper.queryList(productId,chessTypeId);
    }

    private LambdaQueryWrapper<BizChessPiecesAttribute> buildQueryWrapper(BizChessPiecesAttributeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizChessPiecesAttribute> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizChessPiecesAttribute::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getProductId().toString()),BizChessPiecesAttribute::getProductId,bo.getProductId());
//        lqw.like(StringUtils.isNotBlank(bo.getProductName()), BizChessPiecesAttribute::getProductName, bo.getProductName());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), BizChessPiecesAttribute::getType, bo.getType());
        return lqw;
    }

    /**
     * 新增棋子属性定义
     */
    @Override
    public Boolean insertByBo(BizChessPiecesAttributeBo bo) {
        BizChessPiecesAttribute add = BeanUtil.toBean(bo, BizChessPiecesAttribute.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改棋子属性定义
     */
    @Override
    public Boolean updateByBo(BizChessPiecesAttributeBo bo) {
        BizChessPiecesAttribute update = BeanUtil.toBean(bo, BizChessPiecesAttribute.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizChessPiecesAttribute entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除棋子属性定义
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
