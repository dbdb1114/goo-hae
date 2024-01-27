package com.kdt.goohae.controller.total;

import com.kdt.goohae.config.cloudinary.DetailPageCloudinary;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudinaryControllerTest {


    @Autowired private DetailPageCloudinary detailPageCloudinary;



    @Test
    public void DItest() {
        Assertions.assertThat(detailPageCloudinary).isNotNull();
    }

    @Test
    void 업로드테스트(){

        //given
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Map map;

        g2d.setColor(Color.red);
        g2d.fillRect(0,0,200,200);

        g2d.dispose();

        //when
        try{
            File file = new File("output.png");
            ImageIO.write(bufferedImage,"png",file);
            System.out.println("png파일 생성");
            map = detailPageCloudinary.upload(file,"Chair-1");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(map).isNotNull();
    }

}
