package com.ruoyi.app.verdictrule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.app.verdictRuleDimension.domain.BizVerdictRuleDimension;
import com.ruoyi.app.verdictRuleDimension.mapper.BizVerdictRuleDimensionMapper;
import com.ruoyi.app.verdictRuleValue.domain.BizVerdictRuleValue;
import com.ruoyi.app.verdictRuleValue.mapper.BizVerdictRuleValueMapper;
import com.ruoyi.app.verdictrule.category.ResourcesType;
import com.ruoyi.app.verdictrule.domain.BizVerdictRuleExtend;
import com.ruoyi.app.verdictrule.domain.bo.BizVerdictRuleExtendBo;
import com.ruoyi.app.verdictrule.domain.vo.BizVerdictRuleExtendVo;
import com.ruoyi.app.verdictrule.mapper.BizVerdictRuleExtendMapper;
import com.ruoyi.app.verdictrule.service.IBizVerdictRuleExtendService;
import com.ruoyi.business.domain.BizChessPiecesAttribute;
import com.ruoyi.business.domain.BizVerdictDimension;
import com.ruoyi.business.domain.BizVerdictDimensionValue;
import com.ruoyi.business.domain.vo.BizChessPiecesAttributeVo;
import com.ruoyi.business.domain.vo.BizVerdictDimensionValueVo;
import com.ruoyi.business.domain.vo.BizVerdictDimensionVo;
import com.ruoyi.business.mapper.BizChessPiecesAttributeMapper;
import com.ruoyi.business.mapper.BizVerdictDimensionMapper;
import com.ruoyi.business.mapper.BizVerdictDimensionValueMapper;
import com.ruoyi.common.utils.TreeBuildUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 裁决表管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-18
 */
@RequiredArgsConstructor
@Service
public class BizVerdictRuleExtendServiceImpl implements IBizVerdictRuleExtendService {

    private final BizVerdictRuleExtendMapper baseMapper;

    private final BizVerdictRuleDimensionMapper bizVerdictRuleDimensionMapper;

    private final BizVerdictRuleValueMapper bizVerdictRuleValueMapper;

    private final BizChessPiecesAttributeMapper bizChessPiecesAttributeMapper;  //棋子属性

    private final BizVerdictDimensionMapper bizVerdictDimensionMapper;   //裁决维度

    private final BizVerdictDimensionValueMapper bizVerdictDimensionValueMapper;//裁决维度值

    /**
     * 查询裁决表管理
     */
    @Override
    public BizVerdictRuleExtendVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决表管理列表
     */
    @Override
    public List<BizVerdictRuleExtendVo> queryList(BizVerdictRuleExtendBo bo) {
        LambdaQueryWrapper<BizVerdictRuleExtend> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public List<BizVerdictRuleExtendVo> queryAllList(BizVerdictRuleExtendBo bo) {
        List<BizVerdictRuleExtendVo> bizVerdictRuleExtendVoList = new ArrayList<BizVerdictRuleExtendVo>();
        LambdaQueryWrapper<BizVerdictRuleExtend> lqw = buildQueryWrapper(bo);

        bizVerdictRuleExtendVoList = baseMapper.selectVoList(lqw);
        //棋子属性
        LambdaQueryWrapper<BizChessPiecesAttribute> attrLqw = Wrappers.lambdaQuery();
        attrLqw.eq(BizChessPiecesAttribute::getDelFlag, "0");
        attrLqw.eq(bo.getProductId()!=null,BizChessPiecesAttribute::getProductId, bo.getProductId());
        List<BizChessPiecesAttributeVo> bizChessPiecesAttributeVoList = bizChessPiecesAttributeMapper.selectVoList(attrLqw);
        for (BizChessPiecesAttributeVo bizChessPiecesAttributeVo : bizChessPiecesAttributeVoList) {
            BizVerdictRuleExtendVo entity = new BizVerdictRuleExtendVo();
            entity.setId(bizChessPiecesAttributeVo.getId());
            entity.setParamName(bizChessPiecesAttributeVo.getName());
            entity.setParentId(0l);
            entity.setStage("06");
            bizVerdictRuleExtendVoList.add(entity);
        }
        //裁决维度
        LambdaQueryWrapper<BizVerdictDimension> dimensionLqw = Wrappers.lambdaQuery();
        dimensionLqw.eq(BizVerdictDimension::getDelFlag, "0");
        dimensionLqw.eq(bo.getProductId()!=null,BizVerdictDimension::getProductId, bo.getProductId());
        List<BizVerdictDimensionVo> bizVerdictDimensionVoList = bizVerdictDimensionMapper.selectVoList(dimensionLqw);
        LambdaQueryWrapper<BizVerdictDimensionValue> dimensionValueLqw = Wrappers.lambdaQuery();
        dimensionValueLqw.eq(BizVerdictDimensionValue::getDelFlag, "0");
        List<BizVerdictDimensionValueVo> bizVerdictDimensionValueVoList = bizVerdictDimensionValueMapper.selectVoList(dimensionValueLqw);
        for (BizVerdictDimensionVo bizVerdictDimensionVo : bizVerdictDimensionVoList) {
            BizVerdictRuleExtendVo entity = new BizVerdictRuleExtendVo();
            entity.setId(bizVerdictDimensionVo.getId());
            entity.setParamName(bizVerdictDimensionVo.getName());
            entity.setParentId(0l);
            entity.setStage("05");
            bizVerdictRuleExtendVoList.add(entity);
        }
        for (BizVerdictDimensionValueVo bizVerdictDimensionValueVo : bizVerdictDimensionValueVoList) {
            BizVerdictRuleExtendVo entity = new BizVerdictRuleExtendVo();
            entity.setId(bizVerdictDimensionValueVo.getId());
            entity.setParamName(bizVerdictDimensionValueVo.getDimensionValue());
            entity.setParentId(bizVerdictDimensionValueVo.getDimensionId());
            entity.setStage("05");
            bizVerdictRuleExtendVoList.add(entity);
        }
        return bizVerdictRuleExtendVoList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param bizVerdictRuleExtendVos 参数信息
     * @return 下拉树结构列表
     */
    @Override
    public List<Tree<Long>> buildBizVerdictRuleSelect(List<BizVerdictRuleExtendVo> bizVerdictRuleExtendVos) {
        if (CollUtil.isEmpty(bizVerdictRuleExtendVos)) {
            return CollUtil.newArrayList();
        }
        return TreeBuildUtils.build(bizVerdictRuleExtendVos, (bizVerdictRuleExtendVo, tree) ->
            tree.setId(bizVerdictRuleExtendVo.getId())
                .setParentId(bizVerdictRuleExtendVo.getParentId())
                .setName(bizVerdictRuleExtendVo.getParamName()));
    }

    private LambdaQueryWrapper<BizVerdictRuleExtend> buildQueryWrapper(BizVerdictRuleExtendBo bo) {
        LambdaQueryWrapper<BizVerdictRuleExtend> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizVerdictRuleExtend::getDelFlag, "0");
        lqw.eq(bo.getVerdictTypeId() != null, BizVerdictRuleExtend::getVerdictTypeId, bo.getVerdictTypeId());
        lqw.eq(bo.getProductId() != null, BizVerdictRuleExtend::getProductId, bo.getProductId());
        lqw.eq(bo.getStage() != null, BizVerdictRuleExtend::getStage, bo.getStage());
        lqw.orderByDesc(BizVerdictRuleExtend::getCreateTime);
        return lqw;
    }

    public BizVerdictRuleExtendBo insertOrUpdateDataChange(BizVerdictRuleExtendBo bo) {
        if (ResourcesType.RULE.getDictValue().equals(bo.getResource())) {//裁决规则
            this.bizVerdictRuleDimensionMapper.physicalDeleteById(bo.getId());
            this.bizVerdictRuleValueMapper.physicalDeleteById(bo.getId());
            BizVerdictRuleDimension bizVerdictRuleDimensionX = new BizVerdictRuleDimension();
            bizVerdictRuleDimensionX.setVerdictRuleId(bo.getId());
            bizVerdictRuleDimensionX.setVerdictTypeId(bo.getVerdictTypeId());
            bizVerdictRuleDimensionX.setVerdictParamName(bo.getParamValue());
            String verdictRuleInfo = bo.getVerdictRuleInfo();
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<String, Object> verdictRuleInfoMap = mapper.readValue(verdictRuleInfo, Map.class);
                if (verdictRuleInfoMap != null) {
                    List<Map<String, Object>> tableHeader = (List<Map<String, Object>>) verdictRuleInfoMap.get("tableHeader");
                    List<Map<String, Object>> tableData = (List<Map<String, Object>>) verdictRuleInfoMap.get("tableData");
                    List columnY = new ArrayList();
                    tableData.forEach((item) -> {
                        String dimensionName = String.valueOf(item.get("dimensionName"));
                        columnY.add(dimensionName);
                    });
                    bizVerdictRuleDimensionX.setDimensionName(String.valueOf(verdictRuleInfoMap.get("dimensionNameX")));
                    bizVerdictRuleDimensionX.setDimensionCode(String.valueOf(verdictRuleInfoMap.get("dimensionCodeX")));
                    bizVerdictRuleDimensionX.setDimensionId(String.valueOf(verdictRuleInfoMap.get("dimensionValueX")));
                    bizVerdictRuleDimensionX.setDimensionDirect("01");
                    bizVerdictRuleDimensionX.setDimensionValue(mapper.writeValueAsString(tableHeader));
                    BizVerdictRuleDimension bizVerdictRuleDimensionY = BeanUtil.toBean(bizVerdictRuleDimensionX, BizVerdictRuleDimension.class);
                    bizVerdictRuleDimensionY.setDimensionName(String.valueOf(verdictRuleInfoMap.get("dimensionNameY")));
                    bizVerdictRuleDimensionY.setDimensionCode(String.valueOf(verdictRuleInfoMap.get("dimensionCodeY")));
                    bizVerdictRuleDimensionY.setDimensionId(String.valueOf(verdictRuleInfoMap.get("dimensionValueY")));
                    bizVerdictRuleDimensionY.setDimensionDirect("02");
                    bizVerdictRuleDimensionY.setDimensionValue(mapper.writeValueAsString(columnY));
                    BizVerdictRuleValue bizVerdictRuleValue = new BizVerdictRuleValue();
                    bizVerdictRuleValue.setVerdictRuleId(bo.getId());
                    bizVerdictRuleValue.setVerdictTypeId(bo.getVerdictTypeId());
                    bizVerdictRuleValue.setVerdictParamName(bo.getParamValue());
                    bizVerdictRuleValue.setVerdicRuleDimensionValue(mapper.writeValueAsString(tableData));
                    bizVerdictRuleDimensionMapper.insert(bizVerdictRuleDimensionX);
                    bizVerdictRuleDimensionMapper.insert(bizVerdictRuleDimensionY);
                    bizVerdictRuleValueMapper.insert(bizVerdictRuleValue);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return bo;
    }

    /**
     * 新增裁决表管理
     */
    @Override
    public Boolean insertByBo(BizVerdictRuleExtendBo bo) {
        BizVerdictRuleExtend add = BeanUtil.toBean(bo, BizVerdictRuleExtend.class);
        // String dictcode = bo.getDictcode();//取值来源：01  字典选项 02 人工填写 03 设备属性* 04 裁决规则* 05 计算公式* 06 算子
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
            insertOrUpdateDataChange(bo);
        }
        return flag;
    }

    /**
     * 修改裁决表管理
     */
    @Override
    public Boolean updateByBo(BizVerdictRuleExtendBo bo) {
        BizVerdictRuleExtend update = BeanUtil.toBean(bo, BizVerdictRuleExtend.class);
        validEntityBeforeSave(update);
        insertOrUpdateDataChange(bo);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictRuleExtend entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决表管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /***
     * 根据规则id 获取裁决维度及裁决维度表体内容
     * @param verdictRuleId
     * @return
     */
    @Override
    public Map<String, Object> getDimensionWithValueByVerdictRuleId(Long verdictRuleId) {
        Map<String, Object> resultMap = new HashMap<>();
        LambdaQueryWrapper<BizVerdictRuleDimension> dimensionLqw = Wrappers.lambdaQuery();
        dimensionLqw.eq(BizVerdictRuleDimension::getVerdictRuleId, verdictRuleId);
        LambdaQueryWrapper<BizVerdictRuleValue> ruleValueLqw = Wrappers.lambdaQuery();
        ruleValueLqw.eq(BizVerdictRuleValue::getVerdictRuleId, verdictRuleId);
        List<BizVerdictRuleDimension> dimensions = bizVerdictRuleDimensionMapper.selectList(dimensionLqw);
        List<BizVerdictRuleValue> ruleValues = bizVerdictRuleValueMapper.selectList(ruleValueLqw);
        if (dimensions != null && ruleValues != null && ruleValues.size() > 0 && dimensions.size() > 0) {
            resultMap.put("dimensionsList", dimensions);
            resultMap.put("ruleValueObj", ruleValues.get(0));
        }
        return resultMap;
    }
}
