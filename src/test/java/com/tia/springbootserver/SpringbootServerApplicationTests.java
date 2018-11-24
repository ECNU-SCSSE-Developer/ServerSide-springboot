package com.tia.springbootserver;

import com.tia.springbootserver.service.ImageService;
import com.tia.springbootserver.service.MatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootServerApplicationTests {

    @Autowired
    ImageService imageService;


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
