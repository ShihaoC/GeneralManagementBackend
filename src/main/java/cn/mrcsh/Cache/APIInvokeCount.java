package cn.mrcsh.Cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class APIInvokeCount {

    public static Map<String,Map<String,Integer>> count = new HashMap<>();

    public static int AllCount = 0;
    public static int AllLoginCount = 0;

    public static List<Integer> counts = new ArrayList<>();
    public static List<Integer> LoginCount = new ArrayList<>();

}
