package com.chen.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.chen.springboot.entity.Admin;
import com.chen.springboot.service.IAdminService;
import com.chen.springboot.utils.Constants;
import com.chen.springboot.utils.R;
import com.chen.springboot.utils.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public R saveAdmin(@RequestBody Admin admin) {
        if (adminService.saveOrUpdateAdmin(admin)){
            return new R(Constants.CODE_200,"保存成功!",null);
        }
        else {
            return new R(Constants.CODE_500,"保存失败",null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public R deleteAdminById(@PathVariable Integer id) {
        if ( adminService.removeAdminById(id) ){
            return new R(Constants.CODE_200,"删除成功!",null);
        }
        else {
            return new R(Constants.CODE_500,"删除失败",null);
        }
    }

    @GetMapping
    public R findAllAdmin() {
        return new R(Constants.CODE_200,"查询成功", adminService.list());
    }

    @GetMapping("/{id}")
    public R findOneAdminById(@PathVariable("id") Integer id) {
        return new R(Constants.CODE_200,"查询成功", adminService.getById(id));
    }

    @GetMapping("/page")
    public R findAdminByPage(@RequestParam("currentPage") Integer currentPage,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam(value = "adminName",defaultValue = "") String adminName) {
        return new R(Constants.CODE_200,"查询成功", adminService.getAdminByPage(currentPage,pageSize,adminName));
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

