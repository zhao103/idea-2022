package cn.baise.toolkit;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Paging {
    private int aa = 2;
    private int bb = 3;
    //token令牌
    private String token;
    //页数
    private Integer pageNum = aa;
    //每页展示的数据
    private Integer curPage = bb;

    private Map<String,Object> map = new HashMap<>();
}
