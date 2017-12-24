package com.upic;

import com.upic.common.fdfs.FastDFSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaWebApplication.class)
public class TestFastDfs {
    @Test
    public void testFastDfs() {
        FastDFSClient fastDFSClient = new FastDFSClient();
        File file = new File("/Users/zhubuqing/Desktop/fuck.jpeg");
        System.out.println(file.getAbsoluteFile());
        String upload = fastDFSClient.uploadFile(file, "fuck.jpeg");
        System.out.print(upload);
    }
}
