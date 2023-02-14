package cn.mrcsh;

import cn.mrcsh.Entity.Department;
import cn.mrcsh.Mapper.DepartmentMapper;
import cn.mrcsh.Service.DepartmentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.utils.Format.FormatUtil;
import com.cui.utils.Msg.Prints;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
class EmManagementApplicationTests {
    @Autowired
    private DepartmentMapper mapper;

    @Test
    void contextLoads(){
        Page<Department> page = new Page<>(1,5);
        mapper.selectPage(page,null);
        List<Department> records = page.getRecords();

    }
}
