package com.ruoyi.business.api;

import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.business.domain.bo.BizUserBo;
import com.ruoyi.business.domain.vo.BizUserVo;
import com.ruoyi.business.service.IBizUserService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.UserPermissionEnum;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.common.helper.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author kyc
 * @date 2024/2/27 11:21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    private final IBizUserService userService;


    /**
     * 查询权限选项
     *
     * @return
     */
    @GetMapping("/queryPermissionSelections")
    public R<List<Map<String, String>>> queryPermissionSelections() {
        return R.ok(UserPermissionEnum.queryPermissionSelections());
    }

    /**
     * 注册
     *
     * @param registerDto
     * @return
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody BizUserBo registerDto) {
        return R.ok(userService.register(registerDto));
    }

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody BizUserBo loginDto) {
        return userService.login(loginDto);
    }

    /**
     * 重置密码-app
     *
     * @param resetPasswordDto
     * @return
     */
    @PostMapping("/resetPassword")
    public R<String> resetPassword(@RequestBody BizUserBo resetPasswordDto) {
        return R.ok(userService.resetPassword(resetPasswordDto));
    }

    /**
     * 刷新token
     *
     * @param userId
     * @return
     */
    @PostMapping("/refreshToken/{userId}")
    public String refreshToken(@PathVariable("userId") String userId) {
        try {
            BizUserVo user = userService.queryById(Long.valueOf(userId));
            // 此处可根据登录用户的数据不同 自行创建 loginUser
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(user.getId());
            loginUser.setUsername(user.getUserName());
            loginUser.setUserType(UserType.APP_USER.getUserType());
            // 生成token
            LoginHelper.loginByDevice(loginUser, DeviceType.APP);
            return StpUtil.getTokenValue();
        } catch (Exception e) {
            return "";
        }
    }


}
