package cn.mrcsh;

import com.cui.utils.Format.FormatUtil;
import com.cui.utils.Msg.Prints;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
class EmManagementApplicationTests {

    @Test
    void contextLoads() throws IOException {
        Prints.error(FormatUtil.FormatToBinary(123));
    }
}
