package com.ruoyi.app.verdictChess.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessDto;
import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.app.verdictChess.domain.bo.BizVerdictChessBo;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessVo;
import com.ruoyi.app.verdictChess.domain.BizVerdictChess;
import com.ruoyi.app.verdictChess.mapper.BizVerdictChessMapper;
import com.ruoyi.app.verdictChess.service.IBizVerdictChessService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决棋子表Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@RequiredArgsConstructor
@Service
public class BizVerdictChessServiceImpl implements IBizVerdictChessService {

    private final BizVerdictChessMapper baseMapper;

    /**
     * 查询裁决棋子表
     */
    @Override
    public BizVerdictChessVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决棋子表列表
     */
    @Override
    public TableDataInfo<BizVerdictChessVo> queryPageList(BizVerdictChessBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictChess> lqw = buildQueryWrapper(bo);
        Page<BizVerdictChessVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决棋子表列表
     */
    @Override
    public List<BizVerdictChessVo> queryList(BizVerdictChessBo bo) {
        LambdaQueryWrapper<BizVerdictChess> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询裁决棋子表列表
     */
    @Override
    public List<BizVerdictChessDto> queryDtoList(BizVerdictChessBo bo) {
        QueryWrapper<BizVerdictChess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != bo.getProductId(), "bvc.product_id", bo.getProductId());
        queryWrapper.eq(null != bo.getChessTypeId(), "bvc.chess_type_id", bo.getChessTypeId());
        queryWrapper.eq("bvc.del_flag", "0");
        //queryWrapper.orderByDesc("bvr.create_time");
        return baseMapper.queryList(queryWrapper);
    }

    private LambdaQueryWrapper<BizVerdictChess> buildQueryWrapper(BizVerdictChessBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictChess> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, BizVerdictChess::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), BizVerdictChess::getProductName, bo.getProductName());
        lqw.eq(bo.getVerdictTypeId() != null, BizVerdictChess::getVerdictTypeId, bo.getVerdictTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getVerdictTypeCode()), BizVerdictChess::getVerdictTypeCode, bo.getVerdictTypeCode());
        lqw.eq(bo.getChessTypeId() != null, BizVerdictChess::getChessTypeId, bo.getChessTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getChessTypeName()), BizVerdictChess::getChessTypeName, bo.getChessTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictChess::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增裁决棋子表
     */
    @Override
    public Boolean insertByBo(BizVerdictChessBo bo) {
        BizVerdictChess add = BeanUtil.toBean(bo, BizVerdictChess.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决棋子表
     */
    @Override
    public Boolean updateByBo(BizVerdictChessBo bo) {
        BizVerdictChess update = BeanUtil.toBean(bo, BizVerdictChess.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictChess entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决棋子表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
