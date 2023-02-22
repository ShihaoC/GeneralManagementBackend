package cn.mrcsh.Controller;

import cn.mrcsh.Cache.APIInvokeCount;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiCountController {
    @GetMapping("/count")
    public List<Integer> getApiCount(){
        List<Integer> counts = APIInvokeCount.counts;
        return counts;
    }
}
