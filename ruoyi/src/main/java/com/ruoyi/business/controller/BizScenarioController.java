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
import com.ruoyi.business.domain.vo.BizScenarioVo;
import com.ruoyi.business.domain.bo.BizScenarioBo;
import com.ruoyi.business.service.IBizScenarioService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 想定管理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/scenario")
public class BizScenarioController extends BaseController {

    private final IBizScenarioService iBizScenarioService;

    /**
     * 查询想定管理列表
     */
    @SaCheckPermission("business:scenario:list")
    @GetMapping("/list")
    public TableDataInfo<BizScenarioVo> list(BizScenarioBo bo, PageQuery pageQuery) {
        return iBizScenarioService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/queryScenarioList")
    public List<BizScenarioVo> queryScenarioList(BizScenarioBo bo) {
        return iBizScenarioService.queryList(bo);
    }


    /**
     * 导出想定管理列表
     */
    @SaCheckPermission("business:scenario:export")
    @Log(title = "想定管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizScenarioBo bo, HttpServletResponse response) {
        List<BizScenarioVo> list = iBizScenarioService.queryList(bo);
        ExcelUtil.exportExcel(list, "想定管理", BizScenarioVo.class, response);
    }

    /**
     * 获取想定管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:scenario:query")
    @GetMapping("/{id}")
    public R<BizScenarioVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizScenarioService.queryById(id));
    }

    /**
     * 新增想定管理
     */
    @SaCheckPermission("business:scenario:add")
    @Log(title = "想定管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizScenarioBo bo) {
        return toAjax(iBizScenarioService.insertByBo(bo));
    }

    /**
     * 修改想定管理
     */
    @SaCheckPermission("business:scenario:edit")
    @Log(title = "想定管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizScenarioBo bo) {
        return toAjax(iBizScenarioService.updateByBo(bo));
    }

    /**
     * 删除想定管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:scenario:remove")
    @Log(title = "想定管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizScenarioService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
