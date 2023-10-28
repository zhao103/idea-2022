package cn.baise.controller;

import cn.baise.entity.Register;
import cn.baise.entity.Storage;
import cn.baise.entity.User;
import cn.baise.service.StorageService;
import cn.baise.toolkit.Paging;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 赵航
 * @since 2023-10-28
 */
@RestController
@RequestMapping("/zhaohang")
public class StorageController {
    @Autowired
    private StorageService storageService;
    @PostMapping("/adduser")//添加
    public Boolean addHandle(@RequestBody Storage storage){
        return storageService.save(storage);
    }
    //删除
    @DeleteMapping("/{id}")
    public Boolean deleteHandle(@PathVariable("id") Integer id){
        return storageService.removeById(id);
    }
    //修改
    @PostMapping("/upuser")
    public Boolean updataHandle(@RequestBody Storage storage){

        return storageService.updateById(storage);
    }
    //全表查询
    @GetMapping ("/list")
    public IPage<Storage> getHandleAll(Paging paging){
        List<Storage> list = storageService.list();
        System.out.println(paging.getCurPage());
        //模糊查询的子类
        LambdaQueryWrapper<Storage> lam = new LambdaQueryWrapper<>();
        //paging.getMap().get("name")获取前端传入的数据

        String student = (String) paging.getMap().get("input");
        String roleId = (String)paging.getMap().get("roleId");
        LambdaQueryWrapper<Storage> wrapper =
                lam.like(student != null,Storage::getName, student);

        //分页
        IPage<Storage> page = new Page<>(paging.getCurPage(),paging.getPageNum());

        IPage<Storage> pa= storageService.page(page,wrapper);
        System.out.println(pa);
        return pa;
    }
    //id查询
    @GetMapping("/{id}")
    public Storage getHandleById(@PathVariable("id") Integer id){
        return storageService.getById(id);
    }


}
