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
import com.ruoyi.business.domain.vo.BizVerdictRecordRoundVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordRoundBo;
import com.ruoyi.business.service.IBizVerdictRecordRoundService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决记录回合管理
 *
 * @author ruoyi
 * @date 2024-03-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRecordRound")
public class BizVerdictRecordRoundController extends BaseController {

    private final IBizVerdictRecordRoundService iBizVerdictRecordRoundService;

    /**
     * 查询裁决记录回合管理列表
     */
    @SaCheckPermission("business:verdictRecordRound:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRecordRoundVo> list(BizVerdictRecordRoundBo bo, PageQuery pageQuery) {
        return iBizVerdictRecordRoundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决记录回合管理列表
     */
    @SaCheckPermission("business:verdictRecordRound:export")
    @Log(title = "裁决记录回合管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRecordRoundBo bo, HttpServletResponse response) {
        List<BizVerdictRecordRoundVo> list = iBizVerdictRecordRoundService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决记录回合管理", BizVerdictRecordRoundVo.class, response);
    }

    /**
     * 获取裁决记录回合管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRecordRound:query")
    @GetMapping("/{id}")
    public R<BizVerdictRecordRoundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRecordRoundService.queryById(id));
    }

    /**
     * 新增裁决记录回合管理
     */
    @SaCheckPermission("business:verdictRecordRound:add")
    @Log(title = "裁决记录回合管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRecordRoundBo bo) {
        return toAjax(iBizVerdictRecordRoundService.insertByBo(bo));
    }

    /**
     * 修改裁决记录回合管理
     */
    @SaCheckPermission("business:verdictRecordRound:edit")
    @Log(title = "裁决记录回合管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRecordRoundBo bo) {
        return toAjax(iBizVerdictRecordRoundService.updateByBo(bo));
    }

    /**
     * 删除裁决记录回合管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRecordRound:remove")
    @Log(title = "裁决记录回合管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRecordRoundService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
