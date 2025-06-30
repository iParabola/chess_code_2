package com.ruoyi.app.verdictRuleJudgeCriteria.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.bo.BizVerdictRuleJudgeCriteriaBo;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.vo.BizVerdictRuleJudgeCriteriaVo;
import com.ruoyi.app.verdictRuleJudgeCriteria.domain.BizVerdictRuleJudgeCriteria;
import com.ruoyi.app.verdictRuleJudgeCriteria.mapper.BizVerdictRuleJudgeCriteriaMapper;
import com.ruoyi.app.verdictRuleJudgeCriteria.service.IBizVerdictRuleJudgeCriteriaService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 裁决判断前置条件Service业务层处理
 *
 * @author ccc
 * @date 2024-09-18
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRuleJudgeCriteriaServiceImpl implements IBizVerdictRuleJudgeCriteriaService {

    private final BizVerdictRuleJudgeCriteriaMapper baseMapper;

    /**
     * 查询裁决判断前置条件
     */
    @Override
    public BizVerdictRuleJudgeCriteriaVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     *根据裁决类型ID 查询裁决判断前置条件
     */
    @Override
    public BizVerdictRuleJudgeCriteriaVo queryByVerdictTypeId(Long verdictTypeId){
            LambdaQueryWrapper<BizVerdictRuleJudgeCriteria> lqw = Wrappers.lambdaQuery();
            lqw.eq(verdictTypeId != null, BizVerdictRuleJudgeCriteria::getVerdictTypeId,verdictTypeId);
            return baseMapper.selectVoOne(lqw);
        }

    /**
     * 查询裁决判断前置条件列表
     */
    @Override
    public TableDataInfo<BizVerdictRuleJudgeCriteriaVo> queryPageList(BizVerdictRuleJudgeCriteriaBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictRuleJudgeCriteria> lqw = buildQueryWrapper(bo);
        Page<BizVerdictRuleJudgeCriteriaVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决判断前置条件列表
     */
    @Override
    public List<BizVerdictRuleJudgeCriteriaVo> queryList(BizVerdictRuleJudgeCriteriaBo bo) {
        LambdaQueryWrapper<BizVerdictRuleJudgeCriteria> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictRuleJudgeCriteria> buildQueryWrapper(BizVerdictRuleJudgeCriteriaBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictRuleJudgeCriteria> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerdictTypeId() != null, BizVerdictRuleJudgeCriteria::getVerdictTypeId, bo.getVerdictTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getStage()), BizVerdictRuleJudgeCriteria::getStage, bo.getStage());
        lqw.eq(StringUtils.isNotBlank(bo.getFormula()), BizVerdictRuleJudgeCriteria::getFormula, bo.getFormula());
        return lqw;
    }

    /**
     * 新增裁决判断前置条件
     */
    @Override
    public Boolean insertByBo(BizVerdictRuleJudgeCriteriaBo bo) {
        baseMapper.physicalDeleteById(bo.getVerdictTypeId());
        BizVerdictRuleJudgeCriteria add = BeanUtil.toBean(bo, BizVerdictRuleJudgeCriteria.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决判断前置条件
     */
    @Override
    public Boolean updateByBo(BizVerdictRuleJudgeCriteriaBo bo) {
        BizVerdictRuleJudgeCriteria update = BeanUtil.toBean(bo, BizVerdictRuleJudgeCriteria.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRuleJudgeCriteria entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决判断前置条件
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
