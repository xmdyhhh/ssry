package com.ruoyi.ssry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 管理员实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 登录账号（如 admin, dean）
     */
    private String username;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 登录密码（BCrypt加密）
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
