package com.tia.springbootserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootServerApplicationTests {


    @Test
    public void contextLoads() {
    }
//
//    @Test
//    public void imageTest() throws IOException {
//        String readFilePath = "/Users/fuhy/IdeaProjects/ServerSide-springboot/target/classes/image/IMG_5796.jpg";
//        String saveFilePath = "/Users/fuhy/IdeaProjects/ServerSide-springboot/target/classes/image/test.jpg";
//        imageService.uploadImage(1,imageService.readPicture(readFilePath));
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageService.downloadImage(1).getBytes());
//        imageService.savePicture(saveFilePath, ImageIO.read(byteArrayInputStream));
//    }

}
