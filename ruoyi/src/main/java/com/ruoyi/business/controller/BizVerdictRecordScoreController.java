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
import com.ruoyi.business.domain.vo.BizVerdictRecordScoreVo;
import com.ruoyi.business.domain.bo.BizVerdictRecordScoreBo;
import com.ruoyi.business.service.IBizVerdictRecordScoreService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁决记录分数管理
 *
 * @author ruoyi
 * @date 2024-03-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/verdictRecordScore")
public class BizVerdictRecordScoreController extends BaseController {

    private final IBizVerdictRecordScoreService iBizVerdictRecordScoreService;

    /**
     * 查询裁决记录分数管理列表
     */
    @SaCheckPermission("business:verdictRecordScore:list")
    @GetMapping("/list")
    public TableDataInfo<BizVerdictRecordScoreVo> list(BizVerdictRecordScoreBo bo, PageQuery pageQuery) {
        return iBizVerdictRecordScoreService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁决记录分数管理列表
     */
    @SaCheckPermission("business:verdictRecordScore:export")
    @Log(title = "裁决记录分数管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizVerdictRecordScoreBo bo, HttpServletResponse response) {
        List<BizVerdictRecordScoreVo> list = iBizVerdictRecordScoreService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁决记录分数管理", BizVerdictRecordScoreVo.class, response);
    }

    /**
     * 获取裁决记录分数管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:verdictRecordScore:query")
    @GetMapping("/{id}")
    public R<BizVerdictRecordScoreVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizVerdictRecordScoreService.queryById(id));
    }

    /**
     * 新增裁决记录分数管理
     */
    @SaCheckPermission("business:verdictRecordScore:add")
    @Log(title = "裁决记录分数管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizVerdictRecordScoreBo bo) {
        return toAjax(iBizVerdictRecordScoreService.insertByBo(bo));
    }

    /**
     * 修改裁决记录分数管理
     */
    @SaCheckPermission("business:verdictRecordScore:edit")
    @Log(title = "裁决记录分数管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizVerdictRecordScoreBo bo) {
        return toAjax(iBizVerdictRecordScoreService.updateByBo(bo));
    }

    /**
     * 删除裁决记录分数管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:verdictRecordScore:remove")
    @Log(title = "裁决记录分数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizVerdictRecordScoreService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
