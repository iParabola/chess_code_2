package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizUserVerifyCode;
import com.ruoyi.business.domain.vo.BizUserVerifyCodeVo;
import com.ruoyi.business.domain.bo.BizUserVerifyCodeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户验证码Service接口
 *
 * @author ruoyi
 * @date 2024-02-20
 */
public interface IBizUserVerifyCodeService {

    /**
     * 查询用户验证码
     */
    BizUserVerifyCodeVo queryById(Long id);

    /**
     * 查询用户验证码列表
     */
    TableDataInfo<BizUserVerifyCodeVo> queryPageList(BizUserVerifyCodeBo bo, PageQuery pageQuery);

    /**
     * 查询用户验证码列表
     */
    List<BizUserVerifyCodeVo> queryList(BizUserVerifyCodeBo bo);

    /**
     * 新增用户验证码
     */
    Boolean insertByBo(BizUserVerifyCodeBo bo);

    /**
     * 修改用户验证码
     */
    Boolean updateByBo(BizUserVerifyCodeBo bo);

    /**
     * 校验并批量删除用户验证码信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
