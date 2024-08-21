package com.paradise.user.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author jiangrenfeng
 * @since 2023-03-30
 */
@Data
public class UserInfoVo extends Model<UserInfoVo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 唯一标识码
     */
    private String uniqueNo;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 身份证正面
     */
    private String idCardFrontPhoto;

    /**
     * 身份证反面
     */
    private String idCardBackPhoto;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别（0：女；1：男；2：未知）
     */
    private String gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 注册日期
     */
    private LocalDate registerTime;

    /**
     * 民族
     */
    private String nation;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 籍贯
     */
    private String hometown;

    /**
     * 是否需要指南（0：否；1：是）
     */
    private String isGuide;

    /**
     * 是否已经实名认证（0：否；1：是,2审核中,3认证失败）
     */
    private String isAuth;

    /**
     * 实名认证时间
     */
    private LocalDateTime authTime;

    /**
     * 账户类型 1个人 2企业
     */
    private String accountType;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 1 表示删除，0 表示未删除
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工友豆
     */
    private Long beanCount;

    /**
     * 企业认证:1认证0未认证2审核中3认证失败
     */
    private Integer enterpriseAuth;

    /**
     * 我的收藏条数
     */
    private Long collectionLimit;

    /**
     * 我的联系条数
     */
    private Long contactLimit;

    /**
     * 我的浏览条数
     */
    private Long browseLimit;

    /**
     * 家乡
     */
    private String hometownAddress;

    /**
     * 我的二维码
     */
    private String qrCode;

    /**
     * 是否完善我的名片1完善，2没完善
     */
    private String businessCard;

    /**
     * 工作状态 1离职,正在找工作  2在职,考虑机会  3在职,暂不考虑
     */
    private String workStatus;

    /**
     * 用户最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 是否修改密码
     */
    private String passwordState;

    /**
     * 是否上传视频简历0否1是
     */
    private Integer videoAuth;

    /**
     * 账户状态0正常1禁用2注销
     */
    private Integer accountStatus;

    /**
     * 微信昵称
     */
    private String wxName;

    /**
     * 极光设备id
     */
    private String registrationId;

    /**
     * 1.工友会2工友惠
     */
    private String jpushType;


}
