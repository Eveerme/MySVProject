package com.chen.springboot.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springboot.entity.User;
import com.chen.springboot.mapper.UserMapper;
import com.chen.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eveerme
 * @since 2022-11-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean saveOrUpdateUser(User user){
        if (user.getId() == null){
        return save(user);
        } else {
        return updateById(user);
        }
    }

    @Override
    public Boolean removeUserById(Integer id){
        return userMapper.deleteById(id) > 0;
    }


    @Override
    public IPage<User> getUserByPage(Integer currentPage, Integer pageSize, String username){
        IPage<User> page = new Page<>(currentPage,pageSize);
        if (username==""){
            return userMapper.selectPage(page,null);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        queryWrapper.orderByDesc("id");
        return userMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Boolean deleteBatchUserById(List<Integer> ids){
        return userMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public void importXlsxFile(HttpServletResponse response) throws Exception {
        //从数据库中查出数据
        List<User> list = userMapper.selectList(null);
        //  通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("avatarUrl","头像地址");
        //一次性写出list内的对象到excel，使用默认央视，强制输出标题
        writer.write(list,true);
        //设置浏览器的响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

}
