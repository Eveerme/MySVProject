package com.chen.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
  public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
   * 账号
   */
    private String adminCount;

    /**
   * 昵称
   */
    private String adminNickname;

    /**
   * 密码
   */
    private String password;

    /**
   * 电话
   */
    private String phone;

  private String avatar;

  @ApiModelProperty("创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime createTime;

}
