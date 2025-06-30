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
import com.ruoyi.business.domain.bo.BizIndirectRecordBo;
import com.ruoyi.business.domain.vo.BizIndirectRecordVo;
import com.ruoyi.business.domain.BizIndirectRecord;
import com.ruoyi.business.mapper.BizIndirectRecordMapper;
import com.ruoyi.business.service.IBizIndirectRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 间瞄记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizIndirectRecordServiceImpl implements IBizIndirectRecordService {

    private final BizIndirectRecordMapper baseMapper;

    /**
     * 查询间瞄记录
     */
    @Override
    public BizIndirectRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询间瞄记录列表
     */
    @Override
    public TableDataInfo<BizIndirectRecordVo> queryPageList(BizIndirectRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizIndirectRecord> lqw = buildQueryWrapper(bo);
        Page<BizIndirectRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询间瞄记录列表
     */
    @Override
    public List<BizIndirectRecordVo> queryList(BizIndirectRecordBo bo) {
        LambdaQueryWrapper<BizIndirectRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizIndirectRecord> buildQueryWrapper(BizIndirectRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizIndirectRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictRecordId() != null, BizIndirectRecord::getVerdictRecordId, bo.getVerdictRecordId());
        lqw.eq(bo.getTargetCampId() != null, BizIndirectRecord::getTargetCampId, bo.getTargetCampId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetCoordinate()), BizIndirectRecord::getTargetCoordinate, bo.getTargetCoordinate());
        lqw.eq(bo.getVerdictRecordHistoryId() != null, BizIndirectRecord::getVerdictRecordHistoryId, bo.getVerdictRecordHistoryId());
        lqw.eq(bo.getIndirectVerdictRuleId() != null, BizIndirectRecord::getIndirectVerdictRuleId, bo.getIndirectVerdictRuleId());
        lqw.eq(bo.getEffectChessRound() != null, BizIndirectRecord::getEffectChessRound, bo.getEffectChessRound());
        lqw.eq(bo.getStatus() != null, BizIndirectRecord::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizIndirectRecord::getExt, bo.getExt());
        lqw.eq(StringUtils.isNotBlank(bo.getCreatedBy()), BizIndirectRecord::getCreatedBy, bo.getCreatedBy());
        return lqw;
    }

    /**
     * 新增间瞄记录
     */
    @Override
    public Boolean insertByBo(BizIndirectRecordBo bo) {
        BizIndirectRecord add = BeanUtil.toBean(bo, BizIndirectRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改间瞄记录
     */
    @Override
    public Boolean updateByBo(BizIndirectRecordBo bo) {
        BizIndirectRecord update = BeanUtil.toBean(bo, BizIndirectRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizIndirectRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除间瞄记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
