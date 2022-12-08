package com.kgproject;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.kgproject.utils.OssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class KgprojectApplicationTests {

    @Test
    void contextLoads() throws SQLException {
    }

}
