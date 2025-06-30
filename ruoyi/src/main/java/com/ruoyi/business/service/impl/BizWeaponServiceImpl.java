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
import com.ruoyi.business.domain.bo.BizWeaponBo;
import com.ruoyi.business.domain.vo.BizWeaponVo;
import com.ruoyi.business.domain.BizWeapon;
import com.ruoyi.business.mapper.BizWeaponMapper;
import com.ruoyi.business.service.IBizWeaponService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 武器管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
public class BizWeaponServiceImpl implements IBizWeaponService {

    private final BizWeaponMapper baseMapper;

    /**
     * 查询武器管理
     */
    @Override
    public BizWeaponVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询武器管理列表
     */
    @Override
    public TableDataInfo<BizWeaponVo> queryPageList(BizWeaponBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizWeapon> lqw = buildQueryWrapper(bo);
        Page<BizWeaponVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询武器管理列表
     */
    @Override
    public List<BizWeaponVo> queryList(BizWeaponBo bo) {
        LambdaQueryWrapper<BizWeapon> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizWeapon> buildQueryWrapper(BizWeaponBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizWeapon> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getWeaponName()), BizWeapon::getWeaponName, bo.getWeaponName());
        return lqw;
    }

    /**
     * 新增武器管理
     */
    @Override
    public Boolean insertByBo(BizWeaponBo bo) {
        BizWeapon add = BeanUtil.toBean(bo, BizWeapon.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改武器管理
     */
    @Override
    public Boolean updateByBo(BizWeaponBo bo) {
        BizWeapon update = BeanUtil.toBean(bo, BizWeapon.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizWeapon entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除武器管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
