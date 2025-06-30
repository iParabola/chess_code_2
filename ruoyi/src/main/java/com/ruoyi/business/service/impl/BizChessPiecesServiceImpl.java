package com.ruoyi.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.BizChessPieces;
import com.ruoyi.business.domain.bo.BizChessPiecesBo;
import com.ruoyi.business.domain.vo.BizChessPiecesVo;
import com.ruoyi.business.domain.vo.BizProductVo;
import com.ruoyi.business.mapper.BizChessPiecesMapper;
import com.ruoyi.business.service.IBizChessPiecesService;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysOssMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 棋子管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
public class BizChessPiecesServiceImpl implements IBizChessPiecesService {

    private final BizChessPiecesMapper baseMapper;

    private final SysOssMapper ossMapper;

    /**
     * 查询棋子管理
     */
    @Override
    public BizChessPiecesVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询棋子管理列表
     */
    @Override
    public TableDataInfo<BizChessPiecesVo> queryPageList(BizChessPiecesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizChessPieces> lqw = buildQueryWrapper(bo);
        Page<BizChessPiecesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询棋子管理列表
     */
    @Override
    public List<BizChessPiecesVo> queryList(BizChessPiecesBo bo) {
        LambdaQueryWrapper<BizChessPieces> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizChessPieces> buildQueryWrapper(BizChessPiecesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizChessPieces> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getChessPiecesName()), BizChessPieces::getChessPiecesName, bo.getChessPiecesName());
        lqw.orderByDesc(BizChessPieces::getId);
        return lqw;
    }

    /**
     * 新增棋子管理
     */
    @Override
    public Boolean insertByBo(BizChessPiecesBo bo) {
        BizChessPieces add = BeanUtil.toBean(bo, BizChessPieces.class);
//        validEntityBeforeSave(add);
//        if(ObjectUtil.isNotNull(bo.getChessPiecesCover())){
//            SysOss oss = ossMapper.selectById(add.getChessPiecesCover());
//            add.setChessPiecesCover(oss.getUrl());
//        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改棋子管理
     */
    @Override
    public Boolean updateByBo(BizChessPiecesBo bo) {
        BizChessPieces update = BeanUtil.toBean(bo, BizChessPieces.class);
//        if(ObjectUtil.isNotNull(bo.getChessPiecesCover())&& !bo.getChessPiecesCover().contains("http")){
//            SysOss oss = ossMapper.selectById(update.getChessPiecesCover());
//            update.setChessPiecesCover(oss.getUrl());
//        }
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizChessPieces entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除棋子管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


    @Override
    public List<BizChessPiecesVo> queryVerdictChessPiecesVos(BizProductVo queryArmoryDetailDto) {
        LambdaQueryWrapper<BizChessPieces> lqw = Wrappers.lambdaQuery();
        lqw.eq(BizChessPieces::getProductId, queryArmoryDetailDto.getId());
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public BizChessPieces queryByChessPiecesNumber(String chessPiecesNumber) {
        return baseMapper.selectOne(new LambdaQueryWrapper<BizChessPieces>().eq(BizChessPieces::getChessPiecesNumber, chessPiecesNumber).last("limit 1"));

    }


    @Override
    public List<BizChessPieces> queryByProductId(Long productId) {
        return baseMapper.selectList(new LambdaQueryWrapper<BizChessPieces>().eq(BizChessPieces::getProductId, productId));
    }
}
