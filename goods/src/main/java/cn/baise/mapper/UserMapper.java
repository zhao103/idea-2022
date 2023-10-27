package cn.baise.mapper;

import cn.baise.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 赵航
 * @since 2023-10-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
