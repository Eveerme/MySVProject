package com.chen.springboot.service;

import com.chen.springboot.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-23
 */
public interface IAdminService extends IService<Admin> {
    public Boolean saveOrUpdateAdmin(Admin admin);
    public Boolean removeAdminById(Integer id);
    public IPage<Admin> getAdminByPage(Integer currentPage, Integer pageSize, String adminname);
}
