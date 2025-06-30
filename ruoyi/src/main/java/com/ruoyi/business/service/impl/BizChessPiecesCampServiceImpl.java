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
import com.ruoyi.business.domain.bo.BizChessPiecesCampBo;
import com.ruoyi.business.domain.vo.BizChessPiecesCampVo;
import com.ruoyi.business.domain.BizChessPiecesCamp;
import com.ruoyi.business.mapper.BizChessPiecesCampMapper;
import com.ruoyi.business.service.IBizChessPiecesCampService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 棋子阵营Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
public class BizChessPiecesCampServiceImpl implements IBizChessPiecesCampService {

    private final BizChessPiecesCampMapper baseMapper;

    /**
     * 查询棋子阵营
     */
    @Override
    public BizChessPiecesCampVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询棋子阵营列表
     */
    @Override
    public TableDataInfo<BizChessPiecesCampVo> queryPageList(BizChessPiecesCampBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizChessPiecesCamp> lqw = buildQueryWrapper(bo);
        Page<BizChessPiecesCampVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询棋子阵营列表
     */
    @Override
    public List<BizChessPiecesCampVo> queryList(BizChessPiecesCampBo bo) {
        LambdaQueryWrapper<BizChessPiecesCamp> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizChessPiecesCamp> buildQueryWrapper(BizChessPiecesCampBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizChessPiecesCamp> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BizChessPiecesCamp::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizChessPiecesCamp::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增棋子阵营
     */
    @Override
    public Boolean insertByBo(BizChessPiecesCampBo bo) {
        BizChessPiecesCamp add = BeanUtil.toBean(bo, BizChessPiecesCamp.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改棋子阵营
     */
    @Override
    public Boolean updateByBo(BizChessPiecesCampBo bo) {
        BizChessPiecesCamp update = BeanUtil.toBean(bo, BizChessPiecesCamp.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizChessPiecesCamp entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除棋子阵营
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
