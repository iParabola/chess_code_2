package com.ruoyi.app.verdicttype.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.app.verdicttype.domain.BizVerdictType;
import com.ruoyi.app.verdicttype.domain.vo.BizVerdictTypeVo;
import com.ruoyi.common.annotation.DataColumn;
import com.ruoyi.common.annotation.DataPermission;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 裁决类型Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-10
 */
public interface BizVerdictTypeMapper extends BaseMapperPlus<BizVerdictTypeMapper, BizVerdictType, BizVerdictTypeVo> {
        /**
         * 查询裁决分类数据
         *
         * @param queryWrapper 查询条件
         * @return 部门信息集合
         */
        List<BizVerdictType> selectBizVerdictList(@Param(Constants.WRAPPER) Wrapper<BizVerdictType> queryWrapper);
}
