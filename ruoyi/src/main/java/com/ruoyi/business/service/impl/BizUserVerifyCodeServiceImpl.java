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
import com.ruoyi.business.domain.bo.BizUserVerifyCodeBo;
import com.ruoyi.business.domain.vo.BizUserVerifyCodeVo;
import com.ruoyi.business.domain.BizUserVerifyCode;
import com.ruoyi.business.mapper.BizUserVerifyCodeMapper;
import com.ruoyi.business.service.IBizUserVerifyCodeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户验证码Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-20
 */
@RequiredArgsConstructor
@Service
public class BizUserVerifyCodeServiceImpl implements IBizUserVerifyCodeService {

    private final BizUserVerifyCodeMapper baseMapper;

    /**
     * 查询用户验证码
     */
    @Override
    public BizUserVerifyCodeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询用户验证码列表
     */
    @Override
    public TableDataInfo<BizUserVerifyCodeVo> queryPageList(BizUserVerifyCodeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizUserVerifyCode> lqw = buildQueryWrapper(bo);
        Page<BizUserVerifyCodeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户验证码列表
     */
    @Override
    public List<BizUserVerifyCodeVo> queryList(BizUserVerifyCodeBo bo) {
        LambdaQueryWrapper<BizUserVerifyCode> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizUserVerifyCode> buildQueryWrapper(BizUserVerifyCodeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizUserVerifyCode> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getMobile()), BizUserVerifyCode::getMobile, bo.getMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getVerifyCode()), BizUserVerifyCode::getVerifyCode, bo.getVerifyCode());
        lqw.eq(bo.getExpireTime() != null, BizUserVerifyCode::getExpireTime, bo.getExpireTime());
        lqw.eq(StringUtils.isNotBlank(bo.getExt()), BizUserVerifyCode::getExt, bo.getExt());
        return lqw;
    }

    /**
     * 新增用户验证码
     */
    @Override
    public Boolean insertByBo(BizUserVerifyCodeBo bo) {
        BizUserVerifyCode add = BeanUtil.toBean(bo, BizUserVerifyCode.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户验证码
     */
    @Override
    public Boolean updateByBo(BizUserVerifyCodeBo bo) {
        BizUserVerifyCode update = BeanUtil.toBean(bo, BizUserVerifyCode.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizUserVerifyCode entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户验证码
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
