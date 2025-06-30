package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizWeapon;
import com.ruoyi.business.domain.vo.BizWeaponVo;
import com.ruoyi.business.domain.bo.BizWeaponBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 武器管理Service接口
 *
 * @author ruoyi
 * @date 2024-02-19
 */
public interface IBizWeaponService {

    /**
     * 查询武器管理
     */
    BizWeaponVo queryById(Long id);

    /**
     * 查询武器管理列表
     */
    TableDataInfo<BizWeaponVo> queryPageList(BizWeaponBo bo, PageQuery pageQuery);

    /**
     * 查询武器管理列表
     */
    List<BizWeaponVo> queryList(BizWeaponBo bo);

    /**
     * 新增武器管理
     */
    Boolean insertByBo(BizWeaponBo bo);

    /**
     * 修改武器管理
     */
    Boolean updateByBo(BizWeaponBo bo);

    /**
     * 校验并批量删除武器管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
