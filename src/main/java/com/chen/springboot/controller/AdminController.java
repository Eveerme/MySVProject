package com.chen.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.springboot.entity.Admin;
import com.chen.springboot.service.IAdminService;
import com.chen.springboot.utils.Constants;
import com.chen.springboot.utils.R;
import com.chen.springboot.utils.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-23
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/save")
    public Boolean saveAdmin(@RequestBody Admin admin) {
        return adminService.saveOrUpdateAdmin(admin);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteAdminById(@PathVariable Integer id) {
        return adminService.removeAdminById(id);
    }

    @GetMapping
    public List<Admin> findAllAdmin() {
        return adminService.list();
    }

    @GetMapping("/{id}")
    public Admin findOneAdminById(@PathVariable("id") Integer id) {
        return adminService.getById(id);
    }

    @GetMapping("/page")
    public IPage<Admin> findAdminByPage(@RequestParam("currentPage") Integer currentPage,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam(value = "adminName",defaultValue = "") String adminName) {
        return adminService.getAdminByPage(currentPage,pageSize,adminName);
    }

    @PostMapping("/login")
    public R login(@RequestBody AdminDTO adminDTO){
        String adminCount = adminDTO.getAdminCount();
        String password = adminDTO.getPassword();
        if (StrUtil.isBlank(adminCount) || StrUtil.isBlank(password)){
            return R.error(Constants.CODE_600,"用户名或者密码为空!");
        }
        return adminService.login(adminDTO);
    }

}

