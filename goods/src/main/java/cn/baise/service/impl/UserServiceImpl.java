package cn.baise.service.impl;

import cn.baise.entity.User;
import cn.baise.mapper.UserMapper;
import cn.baise.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 赵航
 * @since 2023-10-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
