package com.ldongxu;

import com.ldongxu.config.AppConfig;
import com.ldongxu.config.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**spring集成测试类
 * Created by 刘东旭 on 2017/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@ActiveProfiles("dbcp")
public class Spring_Test {
}
