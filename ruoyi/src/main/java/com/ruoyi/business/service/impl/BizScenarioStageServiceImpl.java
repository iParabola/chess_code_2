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
import com.ruoyi.business.domain.bo.BizScenarioStageBo;
import com.ruoyi.business.domain.vo.BizScenarioStageVo;
import com.ruoyi.business.domain.BizScenarioStage;
import com.ruoyi.business.mapper.BizScenarioStageMapper;
import com.ruoyi.business.service.IBizScenarioStageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 阶段管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-01
 */
@RequiredArgsConstructor
@Service
public class BizScenarioStageServiceImpl implements IBizScenarioStageService {

    private final BizScenarioStageMapper baseMapper;

    /**
     * 查询阶段管理
     */
    @Override
    public BizScenarioStageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询阶段管理列表
     */
    @Override
    public TableDataInfo<BizScenarioStageVo> queryPageList(BizScenarioStageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizScenarioStage> lqw = buildQueryWrapper(bo);
        Page<BizScenarioStageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询阶段管理列表
     */
    @Override
    public List<BizScenarioStageVo> queryList(BizScenarioStageBo bo) {
        LambdaQueryWrapper<BizScenarioStage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizScenarioStage> buildQueryWrapper(BizScenarioStageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizScenarioStage> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增阶段管理
     */
    @Override
    public Boolean insertByBo(BizScenarioStageBo bo) {
        BizScenarioStage add = BeanUtil.toBean(bo, BizScenarioStage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改阶段管理
     */
    @Override
    public Boolean updateByBo(BizScenarioStageBo bo) {
        BizScenarioStage update = BeanUtil.toBean(bo, BizScenarioStage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizScenarioStage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除阶段管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
