package com.chen.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

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


}
