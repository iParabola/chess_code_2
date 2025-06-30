package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizUser;
import com.ruoyi.business.domain.vo.BizUserVo;
import com.ruoyi.business.domain.bo.BizUserBo;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizUserService {

    /**
     * 查询用户管理
     */
    BizUserVo queryById(Long id);

    /**
     * 查询用户管理列表
     */
    TableDataInfo<BizUserVo> queryPageList(BizUserBo bo, PageQuery pageQuery);

    /**
     * 查询用户管理列表
     */
    List<BizUserVo> queryList(BizUserBo bo);

    /**
     * 新增用户管理
     */
    Boolean insertByBo(BizUserBo bo);

    /**
     * 修改用户管理
     */
    Boolean updateByBo(BizUserBo bo);

    /**
     * 校验并批量删除用户管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    String register(BizUserBo registerDto);


    R<Map<String, Object>> login(@RequestBody BizUserBo loginDto);


    String resetPassword( @RequestBody BizUserBo resetPasswordDto);
}
