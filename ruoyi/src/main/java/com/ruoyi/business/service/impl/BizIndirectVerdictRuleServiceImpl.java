package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.business.domain.BizVerdictRecordChessPieces;
import com.ruoyi.business.mapper.BizVerdictRecordChessPiecesMapper;
import com.ruoyi.common.constant.ArbiterConstant;
import com.ruoyi.common.enums.FireTypeEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizIndirectVerdictRuleBo;
import com.ruoyi.business.domain.vo.BizIndirectVerdictRuleVo;
import com.ruoyi.business.domain.BizIndirectVerdictRule;
import com.ruoyi.business.mapper.BizIndirectVerdictRuleMapper;
import com.ruoyi.business.service.IBizIndirectVerdictRuleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 间瞄裁决规则Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizIndirectVerdictRuleServiceImpl implements IBizIndirectVerdictRuleService {

    private final BizIndirectVerdictRuleMapper baseMapper;

    private final BizVerdictRecordChessPiecesMapper verdictRecordChessPiecesMapper;

    /**
     * 查询间瞄裁决规则
     */
    @Override
    public BizIndirectVerdictRuleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询间瞄裁决规则列表
     */
    @Override
    public TableDataInfo<BizIndirectVerdictRuleVo> queryPageList(BizIndirectVerdictRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizIndirectVerdictRule> lqw = buildQueryWrapper(bo);
        Page<BizIndirectVerdictRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询间瞄裁决规则列表
     */
    @Override
    public List<BizIndirectVerdictRuleVo> queryList(BizIndirectVerdictRuleBo bo) {
        LambdaQueryWrapper<BizIndirectVerdictRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizIndirectVerdictRule> buildQueryWrapper(BizIndirectVerdictRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizIndirectVerdictRule> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getChessPiecesNumber()), BizIndirectVerdictRule::getChessPiecesNumber, bo.getChessPiecesNumber());
        lqw.like(StringUtils.isNotBlank(bo.getChessPiecesName()), BizIndirectVerdictRule::getChessPiecesName, bo.getChessPiecesName());
        lqw.eq(bo.getSupportStatus() != null, BizIndirectVerdictRule::getSupportStatus, bo.getSupportStatus());
        lqw.eq(bo.getFireType() != null, BizIndirectVerdictRule::getFireType, bo.getFireType());
        lqw.eq(bo.getDelayRound() != null, BizIndirectVerdictRule::getDelayRound, bo.getDelayRound());
        lqw.eq(bo.getFallAttackScore() != null, BizIndirectVerdictRule::getFallAttackScore, bo.getFallAttackScore());
        lqw.eq(bo.getNeighborAttackScore() != null, BizIndirectVerdictRule::getNeighborAttackScore, bo.getNeighborAttackScore());
        lqw.eq(bo.getPeripheryAttackScore() != null, BizIndirectVerdictRule::getPeripheryAttackScore, bo.getPeripheryAttackScore());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizIndirectVerdictRule::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增间瞄裁决规则
     */
    @Override
    public Boolean insertByBo(BizIndirectVerdictRuleBo bo) {
        BizIndirectVerdictRule add = BeanUtil.toBean(bo, BizIndirectVerdictRule.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改间瞄裁决规则
     */
    @Override
    public Boolean updateByBo(BizIndirectVerdictRuleBo bo) {
        BizIndirectVerdictRule update = BeanUtil.toBean(bo, BizIndirectVerdictRule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizIndirectVerdictRule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除间瞄裁决规则
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public int countByChessPiecesNumber(String chessPiecesNumber) {
        return Math.toIntExact(baseMapper.selectCount(new LambdaQueryWrapper<BizIndirectVerdictRule>().eq(BizIndirectVerdictRule::getChessPiecesNumber, chessPiecesNumber)));
    }

    @Override
    public Integer queryFireType(Long verdictRecordId, Long chessPiecesCampId, String targetCoordinate, int distance) {
        int count = Math.toIntExact(verdictRecordChessPiecesMapper.selectCount(new LambdaQueryWrapper<BizVerdictRecordChessPieces>()
            .eq(BizVerdictRecordChessPieces::getVerdictRecordId, verdictRecordId)
            .eq(BizVerdictRecordChessPieces::getChessPiecesCampId, chessPiecesCampId)
            .eq(BizVerdictRecordChessPieces::getCoordinate, targetCoordinate)
            .eq(BizVerdictRecordChessPieces::getIsHide, Boolean.FALSE)
        ));
        if (count == 0) {
            return FireTypeEnum.WAIT_FOR_ORDER.getCode();
        }
        if (distance > ArbiterConstant.PREFERRED_TARGET_DISTANCE) {
            return FireTypeEnum.CONTINGENCY_TARGET.getCode();
        } else {
            return FireTypeEnum.PREFERRED_TARGET.getCode();
        }
    }

    @Override
    public BizIndirectVerdictRule queryIndirectRuleRecord(String chessPiecesNumber, Integer fireType) {
        return baseMapper.selectOne(new LambdaQueryWrapper<BizIndirectVerdictRule>()
            .eq(BizIndirectVerdictRule::getChessPiecesNumber, chessPiecesNumber)
            .eq(BizIndirectVerdictRule::getFireType, fireType)
            .last(ArbiterConstant.LIMIT_ONE)
        );
    }
}
