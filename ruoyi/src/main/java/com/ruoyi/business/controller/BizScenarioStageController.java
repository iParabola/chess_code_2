package com.ruoyi.business.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.business.domain.vo.BizScenarioStageVo;
import com.ruoyi.business.domain.bo.BizScenarioStageBo;
import com.ruoyi.business.service.IBizScenarioStageService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 阶段管理
 *
 * @author ruoyi
 * @date 2024-03-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/scenarioStage")
public class BizScenarioStageController extends BaseController {

    private final IBizScenarioStageService iBizScenarioStageService;

    /**
     * 查询阶段管理列表
     */
    @SaCheckPermission("business:scenarioStage:list")
    @GetMapping("/list")
    public TableDataInfo<BizScenarioStageVo> list(BizScenarioStageBo bo, PageQuery pageQuery) {
        return iBizScenarioStageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出阶段管理列表
     */
    @SaCheckPermission("business:scenarioStage:export")
    @Log(title = "阶段管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizScenarioStageBo bo, HttpServletResponse response) {
        List<BizScenarioStageVo> list = iBizScenarioStageService.queryList(bo);
        ExcelUtil.exportExcel(list, "阶段管理", BizScenarioStageVo.class, response);
    }

    /**
     * 获取阶段管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:scenarioStage:query")
    @GetMapping("/{id}")
    public R<BizScenarioStageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizScenarioStageService.queryById(id));
    }

    /**
     * 新增阶段管理
     */
    @SaCheckPermission("business:scenarioStage:add")
    @Log(title = "阶段管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizScenarioStageBo bo) {
        return toAjax(iBizScenarioStageService.insertByBo(bo));
    }

    /**
     * 修改阶段管理
     */
    @SaCheckPermission("business:scenarioStage:edit")
    @Log(title = "阶段管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizScenarioStageBo bo) {
        return toAjax(iBizScenarioStageService.updateByBo(bo));
    }

    /**
     * 删除阶段管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:scenarioStage:remove")
    @Log(title = "阶段管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizScenarioStageService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
