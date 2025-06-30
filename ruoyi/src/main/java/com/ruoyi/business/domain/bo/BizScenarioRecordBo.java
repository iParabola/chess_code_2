package com.ruoyi.business.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BizScenarioRecordBo {

    /**
     * 想定id
     */
    @NotNull(message = "想定id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long scenarioId;

    /**
     * chess_pieces表关联id
     */
    @NotBlank(message = "chess_pieces表关联id不能为空", groups = {AddGroup.class, EditGroup.class})
    private String chessPiecesIds;


}
