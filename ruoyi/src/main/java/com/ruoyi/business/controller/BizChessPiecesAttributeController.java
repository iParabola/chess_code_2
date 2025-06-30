package com.ruoyi.business.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.app.verdictChess.domain.bo.BizVerdictChessBo;
import com.ruoyi.business.domain.BizChessPiecesAttribute;
import com.ruoyi.business.domain.dto.BizChessPiecesAttributeDto;
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
import com.ruoyi.business.domain.vo.BizChessPiecesAttributeVo;
import com.ruoyi.business.domain.bo.BizChessPiecesAttributeBo;
import com.ruoyi.business.service.IBizChessPiecesAttributeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 棋子属性定义
 *
 * @author ruoyi
 * @date 2024-09-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/chessPiecesAttribute")
public class BizChessPiecesAttributeController extends BaseController {

    private final IBizChessPiecesAttributeService iBizChessPiecesAttributeService;

    /**
     * 查询棋子属性定义列表
     */
    @SaCheckPermission("business:chessPiecesAttribute:list")
    @GetMapping("/list")
    public TableDataInfo<BizChessPiecesAttributeVo> list(BizChessPiecesAttributeBo bo, PageQuery pageQuery) {
        return iBizChessPiecesAttributeService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询裁决棋子表列表
     */
    @SaCheckPermission("business:chessPiecesAttribute:list")
    @GetMapping("/queryList")
    public TableDataInfo<BizChessPiecesAttribute> queryList(BizChessPiecesAttributeDto bo) {
        return TableDataInfo.build(iBizChessPiecesAttributeService.queryDtoList(bo));
    }

    /**
     * 导出棋子属性定义列表
     */
    @SaCheckPermission("business:chessPiecesAttribute:export")
    @Log(title = "棋子属性定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BizChessPiecesAttributeBo bo, HttpServletResponse response) {
        List<BizChessPiecesAttributeVo> list = iBizChessPiecesAttributeService.queryList(bo);
        ExcelUtil.exportExcel(list, "棋子属性定义", BizChessPiecesAttributeVo.class, response);
    }

    /**
     * 获取棋子属性定义详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("business:chessPiecesAttribute:query")
    @GetMapping("/{id}")
    public R<BizChessPiecesAttributeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iBizChessPiecesAttributeService.queryById(id));
    }

    /**
     * 新增棋子属性定义
     */
    @SaCheckPermission("business:chessPiecesAttribute:add")
    @Log(title = "棋子属性定义", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizChessPiecesAttributeBo bo) {
        return toAjax(iBizChessPiecesAttributeService.insertByBo(bo));
    }

    /**
     * 修改棋子属性定义
     */
    @SaCheckPermission("business:chessPiecesAttribute:edit")
    @Log(title = "棋子属性定义", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizChessPiecesAttributeBo bo) {
        return toAjax(iBizChessPiecesAttributeService.updateByBo(bo));
    }

    /**
     * 删除棋子属性定义
     *
     * @param ids 主键串
     */
    @SaCheckPermission("business:chessPiecesAttribute:remove")
    @Log(title = "棋子属性定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iBizChessPiecesAttributeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
