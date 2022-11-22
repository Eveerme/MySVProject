package com.chen.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.chen.springboot.service.IAdminService;
import com.chen.springboot.entity.Admin;


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
                                               @RequestParam(value = "adminname",defaultValue = "") String adminname) {
        return adminService.getAdminByPage(currentPage,pageSize,adminname);
    }

}

