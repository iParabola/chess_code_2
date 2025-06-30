package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.business.domain.BizScenarioCreate;
import com.ruoyi.business.domain.BizVerdictRecord;
import com.ruoyi.business.domain.bo.BizProductBo;
import com.ruoyi.business.domain.vo.BizUserVo;
import com.ruoyi.business.domain.vo.BizVerdictRecordVo;
import com.ruoyi.business.mapper.BizVerdictRecordMapper;
import com.ruoyi.business.service.IBizUserService;
import com.ruoyi.common.core.service.UserService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.domain.bo.BizScenarioBo;
import com.ruoyi.business.domain.vo.BizScenarioVo;
import com.ruoyi.business.domain.BizScenario;
import com.ruoyi.business.mapper.BizScenarioMapper;
import com.ruoyi.business.service.IBizScenarioService;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 想定管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizScenarioServiceImpl implements IBizScenarioService {

    private final BizScenarioMapper baseMapper;



    private final BizVerdictRecordMapper verdictRecordMapper;

    /**
     * 查询想定管理
     */
    @Override
    public BizScenarioVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询想定管理列表
     */
    @Override
    public TableDataInfo<BizScenarioVo> queryPageList(BizScenarioBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizScenario> lqw = buildQueryWrapper(bo);
        Page<BizScenarioVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询想定管理列表
     */
    @Override
    public List<BizScenarioVo> queryList(BizScenarioBo bo) {
        LambdaQueryWrapper<BizScenario> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizScenario> buildQueryWrapper(BizScenarioBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizScenario> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getScenarioName()), BizScenario::getScenarioName, bo.getScenarioName());
        return lqw;
    }

    /**
     * 新增想定管理
     */
    @Override
    public Boolean insertByBo(BizScenarioBo bo) {
        BizScenario add = BeanUtil.toBean(bo, BizScenario.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改想定管理
     */
    @Override
    public Boolean updateByBo(BizScenarioBo bo) {
        BizScenario update = BeanUtil.toBean(bo, BizScenario.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizScenario entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除想定管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<BizScenarioVo> queryVerdictScenarioVos(BizProductBo queryArmoryDetailDto) {
        LambdaQueryWrapper<BizScenario> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizScenario::getProductId,queryArmoryDetailDto.getId());
        return baseMapper.selectVoList(lqw);
    }

}
