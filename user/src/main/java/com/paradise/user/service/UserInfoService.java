package com.paradise.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.paradise.user.entity.po.UserInfo;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author jiangrenfeng
 * @since 2023-03-30
 */
public interface UserInfoService extends IService<UserInfo> {

   UserInfo getByUser(String id);

   void updateUser(String id);

}
