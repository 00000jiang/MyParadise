package com.paradise.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paradise.user.entity.po.UserInfo;
import com.paradise.user.mapper.UserInfoMapper;
import com.paradise.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author jiangrenfeng
 * @since 2023-03-30
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public UserInfo getByUser(String id) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unique_no",id);
        return getOne(queryWrapper);
    }

    @Override
    public void updateUser(String id) {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("nick_name","你爷爷");
        updateWrapper.eq("unique_no",id);
        update(updateWrapper);
    }
}
