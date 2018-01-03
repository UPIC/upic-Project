package com.upic;

import com.upic.common.fdfs.FastDFSClient;
import com.upic.dto.UserInfo;

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
    
    public static void main(String[] args) {
//		UserInfo u=new UserInfo();
		System.out.println(UserInfo.class.getSimpleName());
	}
}
