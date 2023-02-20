package cn.mrcsh.Cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class APIInvokeCount {

    public static Map<String,Map<String,Integer>> count = new HashMap<>();

}
