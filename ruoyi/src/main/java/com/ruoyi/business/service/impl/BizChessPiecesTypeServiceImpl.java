package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizChessPiecesTypeBo;
import com.ruoyi.business.domain.vo.BizChessPiecesTypeVo;
import com.ruoyi.business.domain.BizChessPiecesType;
import com.ruoyi.business.mapper.BizChessPiecesTypeMapper;
import com.ruoyi.business.service.IBizChessPiecesTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 棋子类型Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizChessPiecesTypeServiceImpl implements IBizChessPiecesTypeService {

    private final BizChessPiecesTypeMapper baseMapper;

    /**
     * 查询棋子类型
     */
    @Override
    public BizChessPiecesTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询棋子类型列表
     */
    @Override
    public TableDataInfo<BizChessPiecesTypeVo> queryPageList(BizChessPiecesTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizChessPiecesType> lqw = buildQueryWrapper(bo);
        Page<BizChessPiecesTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询棋子类型列表
     */
    @Override
    public List<BizChessPiecesTypeVo> queryList(BizChessPiecesTypeBo bo) {
        LambdaQueryWrapper<BizChessPiecesType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizChessPiecesType> buildQueryWrapper(BizChessPiecesTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizChessPiecesType> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizChessPiecesType::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getProductId().toString()),BizChessPiecesType::getProductId,bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizChessPiecesType::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增棋子类型
     */
    @Override
    public Boolean insertByBo(BizChessPiecesTypeBo bo) {
        BizChessPiecesType add = BeanUtil.toBean(bo, BizChessPiecesType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改棋子类型
     */
    @Override
    public Boolean updateByBo(BizChessPiecesTypeBo bo) {
        BizChessPiecesType update = BeanUtil.toBean(bo, BizChessPiecesType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizChessPiecesType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除棋子类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
