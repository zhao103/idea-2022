package cn.baise.controller;

import cn.baise.entity.Register;
import cn.baise.service.RegisterService;
import cn.baise.service.UserService;
import cn.baise.entity.User;
import cn.baise.toolkit.Paging;
import cn.baise.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 赵航
 * @since 2023-10-12
 */
@RestController
@RequestMapping("/baise")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RegisterService registerService;
    //添加
    @PostMapping("/adduser")
    public Boolean addHandle(@RequestBody User depart){
        return  service.save(depart);
    }
    //删除
    @DeleteMapping("/{id}")
    public Boolean deleteHandle(@PathVariable("id") Integer id){
        return service.removeById(id);
    }
    //修改
    @PostMapping("/upuser")
    public Boolean updataHandle(@RequestBody User depart){

        return service.updateById(depart);
    }
    //全表查询
    @GetMapping ("/list")
    public IPage<User> getHandleAll(Paging paging){
        List<User> list = service.list();
        System.out.println(paging.getCurPage());
        //模糊查询的子类
        LambdaQueryWrapper<User> lam = new LambdaQueryWrapper<>();
        //paging.getMap().get("name")获取前端传入的数据

        String student = (String) paging.getMap().get("input");
        String roleId = (String)paging.getMap().get("roleId");
        System.out.println("这个是id"+roleId);
        LambdaQueryWrapper<User> wrapper =
                lam.like(student != null,User::getStudent, student)
                .like(roleId != null,User::getRoleId,roleId);
            //分页
            IPage<User> page = new Page<>(paging.getCurPage(),paging.getPageNum());

            IPage<User> pa= service.page(page,wrapper);
            System.out.println(pa);
            return pa;
//        }
    }
    //id查询
    @GetMapping("/{id}")
    public User getHandleById(@PathVariable("id") Integer id){
        return service.getById(id);
    }

    @GetMapping("/register")
    @Transactional  //登录判断
    public Object getregister(Paging paging){

        Map<String, Object> map = paging.getMap();
        List<User> registers = service.listByMap(map);
        if (registers.size() == 0){
            return false;
        }else {
            //获取token
            paging.setToken(JwtUtil.createToken());
            Map<String, Object> maptoken = new HashMap<>();
            //返回数据存入
            maptoken.put("registers", registers.get(0));
            //将token
            maptoken.put("token",paging.getToken());
            return maptoken;
        }
    }

    @GetMapping("/regAll/{id}")//模糊查询权限表
    public List<Register> regAllMo(@PathVariable("id") String id){
        //模糊查询
        List<Register> list = registerService.lambdaQuery().like(Register::getRoleId,id).list();
        return list;
    }
    @GetMapping("/checkToken")//验证token
    public Boolean checkToken(String token){
        return JwtUtil.checkToken(token);
    }
}
