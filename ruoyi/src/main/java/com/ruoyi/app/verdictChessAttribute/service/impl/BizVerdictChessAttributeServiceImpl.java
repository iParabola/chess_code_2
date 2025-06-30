package com.ruoyi.app.verdictChessAttribute.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ruoyi.business.domain.bo.BizChessPiecesAttributeBo;
import com.ruoyi.business.domain.dto.BatchSaveVerdictChessAttributeDto;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ruoyi.app.verdictChessAttribute.domain.bo.BizVerdictChessAttributeBo;
import com.ruoyi.app.verdictChessAttribute.domain.vo.BizVerdictChessAttributeVo;
import com.ruoyi.app.verdictChessAttribute.domain.BizVerdictChessAttribute;
import com.ruoyi.app.verdictChessAttribute.mapper.BizVerdictChessAttributeMapper;
import com.ruoyi.app.verdictChessAttribute.service.IBizVerdictChessAttributeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;


/**
 * 裁决棋子属性表Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizVerdictChessAttributeServiceImpl implements IBizVerdictChessAttributeService {

    private final BizVerdictChessAttributeMapper baseMapper;

    /**
     * 查询裁决棋子属性表
     */
    @Override
    public BizVerdictChessAttributeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询裁决棋子属性表列表
     */
    @Override
    public TableDataInfo<BizVerdictChessAttributeVo> queryPageList(BizVerdictChessAttributeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizVerdictChessAttribute> lqw = buildQueryWrapper(bo);
        Page<BizVerdictChessAttributeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询裁决棋子属性表列表
     */
    @Override
    public List<BizVerdictChessAttributeVo> queryList(BizVerdictChessAttributeBo bo) {
        LambdaQueryWrapper<BizVerdictChessAttribute> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizVerdictChessAttribute> buildQueryWrapper(BizVerdictChessAttributeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizVerdictChessAttribute> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChessTypeId() != null, BizVerdictChessAttribute::getChessTypeId, bo.getChessTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getAttrName()), BizVerdictChessAttribute::getAttrName, bo.getAttrName());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrCode()), BizVerdictChessAttribute::getAttrCode, bo.getAttrCode());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrType()), BizVerdictChessAttribute::getAttrType, bo.getAttrType());
        lqw.eq(StringUtils.isNotBlank(bo.getRuleDimensionCode()), BizVerdictChessAttribute::getRuleDimensionCode, bo.getRuleDimensionCode());
        lqw.eq(bo.getAttributeId() != null, BizVerdictChessAttribute::getAttributeId, bo.getAttributeId());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrValue()), BizVerdictChessAttribute::getAttrValue, bo.getAttrValue());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizVerdictChessAttribute::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增裁决棋子属性表
     */
    @Override
    public Boolean insertByBo(BizVerdictChessAttributeBo bo) {
        BizVerdictChessAttribute add = BeanUtil.toBean(bo, BizVerdictChessAttribute.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改裁决棋子属性表
     */
    @Override
    public Boolean updateByBo(BizVerdictChessAttributeBo bo) {
        BizVerdictChessAttribute update = BeanUtil.toBean(bo, BizVerdictChessAttribute.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizVerdictChessAttribute entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除裁决棋子属性表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public String batchSaveVerdictChessAttribute(BatchSaveVerdictChessAttributeDto batchSaveVerdictChessAttributeDto){
        String returnStr = "";
        log.info("batchSaveVerdictChessAttribute req:{}", JSON.toJSONString(batchSaveVerdictChessAttributeDto));
        if(CollectionUtils.isEmpty(batchSaveVerdictChessAttributeDto.getDetailList())){
            log.error("选择引用的棋子属性定义数据列表为空");
            throw new ServiceException("批量引用的数据列表为空");
        }
        Long chessTypeId = Long.valueOf(batchSaveVerdictChessAttributeDto.getChessTypeId());
        List<BizChessPiecesAttributeBo> dataList = batchSaveVerdictChessAttributeDto.getDetailList();
        List<BizVerdictChessAttribute> saveList = new ArrayList<>();
        for (BizChessPiecesAttributeBo obj: dataList) {
            BizVerdictChessAttribute saveObj = new BizVerdictChessAttribute();
            saveObj.setChessTypeId(chessTypeId);
            saveObj.setChessAttributeId(obj.getId());
            saveObj.setAttrName(obj.getName());
            saveObj.setAttrCode(obj.getCode());
            saveObj.setAttrType("03");  //默认常规属性
            saveList.add(saveObj);
        }
        try {
            baseMapper.insertBatch(saveList);
            returnStr = "批量引用棋子属性定义保存成功";
        }catch (Exception e){
            throw new ServiceException("数据库批量保存失败！");
        }
        return returnStr;
    }
}
