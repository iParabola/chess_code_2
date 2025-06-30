package com.ruoyi.business.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.BizUser;
import com.ruoyi.business.domain.bo.BizUserBo;
import com.ruoyi.business.domain.vo.BizUserVo;
import com.ruoyi.business.mapper.BizUserMapper;
import com.ruoyi.business.service.IBizUserService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.UserPermissionEnum;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.EncryptedPBKDF2;
import com.ruoyi.common.utils.RegexUtil;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-19
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizUserServiceImpl implements IBizUserService {

    private final BizUserMapper baseMapper;

    /**
     * 查询用户管理
     */
    @Override
    public BizUserVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询用户管理列表
     */
    @Override
    public TableDataInfo<BizUserVo> queryPageList(BizUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapper(bo);
        Page<BizUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户管理列表
     */
    @Override
    public List<BizUserVo> queryList(BizUserBo bo) {
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BizUser> buildQueryWrapper(BizUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BizUser> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getNickName()), BizUser::getNickName, bo.getNickName());
        lqw.eq(StringUtils.isNotBlank(bo.getMobile()), BizUser::getMobile, bo.getMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getInstitution()), BizUser::getInstitution, bo.getInstitution());
        lqw.orderByDesc(BizUser::getCreateTime);
        return lqw;
    }

    /**
     * 新增用户管理
     */
    @Override
    public Boolean insertByBo(BizUserBo bo) {
        BizUser add = BeanUtil.toBean(bo, BizUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户管理
     */
    @Override
    public Boolean updateByBo(BizUserBo bo) {
        BizUser update = BeanUtil.toBean(bo, BizUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BizUser entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public String register(BizUserBo registerDto) {
        log.info("register req:{}", JSON.toJSONString(registerDto));
        Boolean flag = RegexUtil.checkMobileFormatIsRight(registerDto.getMobile());
        if (!flag) {
            log.error("register mobile format is wrong,mobile:{}", registerDto.getMobile());
            throw new ServiceException("手机号格式错误");
        }
        BizUserVo dbMobileUser = queryByMobile(registerDto.getMobile());
        if (!ObjectUtils.isEmpty(dbMobileUser)) {
            log.error("The phone number has been registered,mobile:{}", registerDto.getMobile());
            throw new ServiceException("手机号已被注册");
        }
        BizUserVo dbNickNameUser = queryByNickName(registerDto.getNickName());
        if (!ObjectUtils.isEmpty(dbNickNameUser)) {
            log.error("The nick name has been registered,mobile:{}", registerDto.getMobile());
            throw new ServiceException("昵称已被注册");
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            log.error("register the two password entries are inconsistent,mobile:{}", registerDto.getMobile());
            throw new ServiceException("两次密码填写不一致");
        }
        if (registerDto.getPassword().length() < 6) {
            log.error("password length is wrong,mobile:{}", registerDto.getMobile());
            throw new ServiceException("密码长度不能小于6位");
        }
        if (!UserPermissionEnum.queryCodeList().contains(registerDto.getPermission())) {
            log.error("permission is wrong,permission:{}", registerDto.getPermission());
            throw new ServiceException("权限有误");
        }
        //保存用户数据
        BizUser user = new BizUser();
//        user.setId(idWorker.nextId());
        user.setUserName(registerDto.getUserName());
        user.setNickName(registerDto.getNickName());
        user.setMobile(registerDto.getMobile());
        user.setInstitution(registerDto.getInstitution());
        String salt = EncryptedPBKDF2.generateSalt();
        String saltPassword = EncryptedPBKDF2.getEncryptedPwd(registerDto.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(saltPassword);
        user.setPermission(registerDto.getPermission());
        baseMapper.insert(user);
        return "";
    }


    public BizUserVo queryByMobile(String mobile) {
        return baseMapper.selectVoOne(new QueryWrapper<BizUser>().lambda().eq(BizUser::getMobile, mobile));
    }


    public BizUserVo queryByNickName(String nickName) {
        return baseMapper.selectVoOne(new QueryWrapper<BizUser>().lambda().eq(BizUser::getNickName, nickName).orderByDesc(BizUser::getCreateTime).last("limit 1"));
    }


    @Override
    public R<Map<String, Object>> login(BizUserBo loginDto) {
        log.info("login req:{}", JSON.toJSONString(loginDto));
        BizUserVo dbMobileUser = queryByMobile(loginDto.getLoginName());
        BizUserVo dbNickNameUser = queryByNickName(loginDto.getLoginName());
        if (ObjectUtils.isEmpty(dbMobileUser) && ObjectUtils.isEmpty(dbNickNameUser)) {
            log.error("user is not exist,loginName:{}", loginDto.getLoginName());
            throw new ServiceException("请使用手机号或昵称进行登录");
        }
        BizUserVo user = !ObjectUtils.isEmpty(dbMobileUser) ? dbMobileUser : dbNickNameUser;
        String saltPassword = EncryptedPBKDF2.getEncryptedPwd(loginDto.getPassword(), user.getSalt());
        if (!saltPassword.equals(user.getPassword())) {
            log.error("login password is wrong,loginName:{}", loginDto.getLoginName());
            throw new ServiceException("密码错误");
        }
        LoginUser loginUser = buildLoginUser(user);
        // 生成token
        LoginHelper.loginByDevice(loginUser, DeviceType.APP);
        String token = StpUtil.getTokenValue();
        Map<String, Object> ajax = new HashMap<>();
        ajax.put(Constants.TOKEN, token);
        ajax.put("user", user);
        return R.ok(ajax);
    }

    private LoginUser buildLoginUser(BizUserVo user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUsername(user.getUserName());
        loginUser.setUserType(UserType.APP_USER.getUserType());
        return loginUser;
    }


    @Override
    public String resetPassword(BizUserBo resetPasswordDto) {
        log.info("resetPassword req:{}", JSON.toJSONString(resetPasswordDto));
//        Boolean flag = userVerifyCodeService.chekVerifyCodeIsValid(resetPasswordDto.getMobile(), resetPasswordDto.getVerifyCode());
//        if (!flag) {
//            log.error("resetPassword verify code is wrong,mobile:{}", resetPasswordDto.getVerifyCode());
//            return ObjectRestResponse.fail("验证码无效，请再次获取");
//        }
        BizUserVo user = queryByMobile(resetPasswordDto.getMobile());
        if (ObjectUtils.isEmpty(user)) {
            log.error("resetPassword the phone number not registered,mobile:{}", resetPasswordDto.getMobile());
            throw new ServiceException("手机号未注册");
        }
        if (!resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {
            log.error("resetPassword the two password entries are inconsistent,mobile:{}", resetPasswordDto.getMobile());
            throw new ServiceException("两次密码填写不一致");
        }
        String salt = EncryptedPBKDF2.generateSalt();
        String saltPassword = EncryptedPBKDF2.getEncryptedPwd(resetPasswordDto.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(saltPassword);
        baseMapper.updateById(BeanUtil.toBean(user, BizUser.class));
        return "";
    }
}
