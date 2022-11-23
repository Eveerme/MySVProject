package com.chen.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springboot.entity.Admin;
import com.chen.springboot.exception.ServiceException;
import com.chen.springboot.mapper.AdminMapper;
import com.chen.springboot.service.IAdminService;
import com.chen.springboot.utils.Constants;
import com.chen.springboot.utils.R;
import com.chen.springboot.utils.TokenUtils;
import com.chen.springboot.utils.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final Log LOG = Log.get();
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public R login(AdminDTO adminDTO) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("admin_count", adminDTO.getAdminCount());
        qw.eq("password", adminDTO.getPassword());
        try {
            Admin a = getOne(qw);
            BeanUtil.copyProperties(a,adminDTO,true);
            String token = TokenUtils.getToken(a.getId().toString(), adminDTO.getPassword());
            adminDTO.setToken(token);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统查询错误");
        }
        if (adminDTO == null){
            throw new ServiceException(Constants.CODE_600,"用户名或者密码错误");
        }else {
            return new R(Constants.CODE_200,"登录成功!",adminDTO);
        }
    }

    @Override
    public R register(Admin admin) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("admin_count", admin.getAdminCount());
        Admin one ;
        try {
            one = getOne(qw);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统查询错误");
        }
        if (one==null){
            save(admin);
            return new R(Constants.CODE_200,"注册成功",null);
        }else {
            return new R(Constants.CODE_600,"已经存在该账号了！",null);
        }
    }

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
    public IPage<Admin> getAdminByPage(Integer currentPage, Integer pageSize, String adminCount){
        IPage<Admin> page = new Page<>(currentPage,pageSize);
        if (adminCount==""){
            return adminMapper.selectPage(page,null);
        }
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("admin_count",adminCount);
        queryWrapper.orderByDesc("id");
        return adminMapper.selectPage(page,queryWrapper);
    }

}
