package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.BizScenarioCreate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ruoyi.business.domain.vo.BizScenarioCreateVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface BizScenarioCreateMapper extends BaseMapper<BizScenarioCreate> {
    @Select("SELECT arbiter_map_id FROM biz_scenario_create WHERE id = #{id}")
    Long selectArbiterMapIdById(Long id);

    @Select("SELECT scenario_name, id, chess_pieces_ids,arbiter_map_id FROM biz_scenario_create")
    List<BizScenarioCreateVo> selectVoList(@Param("ew") LambdaQueryWrapper<BizScenarioCreate> lqw);


    @Select("SELECT chess_round_limit FROM biz_scenario_create WHERE id = #{id}")
    Long selectChessRoundLimitById(@Param("id") Long id);

    @Select("SELECT stage_config FROM biz_scenario_create WHERE id = #{id}")
    String selectStageConfigById(@Param("id") Long id);

    void delete(BizScenarioCreate scenarioCreate);
}
