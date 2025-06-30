package com.ruoyi.app.deployChess.controller;

import com.ruoyi.app.verdictChess.domain.bo.BizVerdictChessBo;
import com.ruoyi.app.verdictChess.domain.vo.BizVerdictChessVo;
import com.ruoyi.business.domain.bo.BizScenarioRecordHistoryBo;
import com.ruoyi.business.domain.bo.BizVerdictRecordHistoryBo;
import com.ruoyi.app.deployChess.service.*;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/deployChess")
public class MapChessController {

    private final IMapChessService mapChessService;
    @PostMapping ("/delChessPiece")
    public R<String> deployChessPieces(@RequestBody BizVerdictRecordHistoryBo dto) {
        return R.ok(mapChessService.delByDto(dto));
    }

    @PostMapping("/delScenarioChessPiece")
    public R<String> delScenarioChessPieces(@RequestBody BizScenarioRecordHistoryBo dto) {
        return R.ok(mapChessService.delByDtoC(dto));
    }

}
