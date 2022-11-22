package com.chen.springboot.service.impl;

import com.chen.springboot.entity.Admin;
import com.chen.springboot.mapper.AdminMapper;
import com.chen.springboot.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Boolean saveOrUpdateAdmin(Admin admin){
        if (admin.getId() == null){
        return save(admin);
        } else {
        return updateById(admin);
        }
    }

    @Override
    public Boolean removeAdminById(Integer id){
        return adminMapper.deleteById(id) > 0;
    }

    @Override
    public IPage<Admin> getAdminByPage(Integer currentPage, Integer pageSize, String adminname){
        IPage<Admin> page = new Page<>(currentPage,pageSize);
        if (adminname==""){
            return adminMapper.selectPage(page,null);
        }
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("adminname",adminname);
        queryWrapper.orderByDesc("id");
        return adminMapper.selectPage(page,queryWrapper);
    }
}
