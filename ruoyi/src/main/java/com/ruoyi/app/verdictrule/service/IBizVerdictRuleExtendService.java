package com.ruoyi.app.verdictrule.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.app.verdictrule.domain.bo.BizVerdictRuleExtendBo;
import com.ruoyi.app.verdictrule.domain.vo.BizVerdictRuleExtendVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 裁决表管理Service接口
 *
 * @author ruoyi
 * @date 2024-08-18
 */
public interface IBizVerdictRuleExtendService {

    /**
     * 查询裁决表管理
     */
    BizVerdictRuleExtendVo queryById(Long id);


    /**
     * 查询裁决表管理列表
     */
    List<BizVerdictRuleExtendVo> queryList(BizVerdictRuleExtendBo bo);

    /**
     * 查询所有确定、判断、修正、裁决参数、棋子属性、裁决维度
     * */
    List<BizVerdictRuleExtendVo> queryAllList(BizVerdictRuleExtendBo bo);

    /***
     *  确定、判断、修正、裁决参数 树构建
     * @param bizVerdictRuleExtendVos
     * @return
     */
    public List<Tree<Long>> buildBizVerdictRuleSelect( List<BizVerdictRuleExtendVo> bizVerdictRuleExtendVos);

    /**
     * 新增裁决表管理
     */
    Boolean insertByBo(BizVerdictRuleExtendBo bo);

    /**
     * 修改裁决表管理
     */
    Boolean updateByBo(BizVerdictRuleExtendBo bo);

    /**
     * 校验并批量删除裁决表管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
       * 获取已配置裁决规则
       */
      Map<String,Object> getDimensionWithValueByVerdictRuleId(Long verdictRuleId);
}
