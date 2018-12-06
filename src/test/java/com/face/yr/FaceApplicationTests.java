package com.face.yr;

import com.face.yr.service.FaceRecognitionService;
import com.face.yr.utils.Base64Util;
import com.face.yr.utils.FaceUtil;
import com.face.yr.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @Test
    public void addTest(){
        FaceUtil.add();
    }


    @Test
    public void addTest1() throws IOException {
        String image = Base64Util.encode(FileUtil.readFileByBytes("C:\\Users\\zhengql\\Pictures\\zhegngong.jpg"));
        FaceUtil.addFace(image,"14098125","zql");
    }
    @Test
    public void checkTest1() throws IOException {
        String image = Base64Util.encode(FileUtil.readFileByBytes("C:\\Users\\zhengql\\Pictures\\timg.jpg"));
        FaceUtil.search(image);
    }

    @Test
    public void searchFace() throws IOException {
        String image = Base64Util.encode(FileUtil.readFileByBytes("C:\\Users\\zhengql\\Pictures\\timg.jpg"));
        String res = faceRecognitionService.search(image,"140981251");
        System.out.println("------------"+res);
    }



    @Test
    public void addFace() throws IOException {
        String image = Base64Util.encode(FileUtil.readFileByBytes("C:\\Users\\zhengql\\Pictures\\aaa.png"));
        String res = faceRecognitionService.addUser(image,"14098125","zql");
        System.out.println("------------"+res);
    }



}
