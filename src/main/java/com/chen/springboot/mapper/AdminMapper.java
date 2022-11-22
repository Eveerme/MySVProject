package com.chen.springboot.mapper;

import com.chen.springboot.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-23
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
