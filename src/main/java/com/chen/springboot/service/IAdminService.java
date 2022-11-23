package com.chen.springboot.service;

import com.chen.springboot.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.springboot.utils.R;
import com.chen.springboot.utils.dto.AdminDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-23
 */
public interface IAdminService extends IService<Admin> {

    R login(AdminDTO adminDTO);
    Boolean saveOrUpdateAdmin(Admin admin);
    Boolean removeAdminById(Integer id);
    IPage<Admin> getAdminByPage(Integer currentPage, Integer pageSize, String adminCount);
}
