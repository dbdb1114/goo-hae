package com.kdt.goohae.service;

import com.kdt.goohae.config.cloudinary.DetailPageCloudinary;
import com.kdt.goohae.controller.admin.AdminCloudinaryController;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@SpringBootTest
class AdminCloudinaryServiceTest {


    @Autowired private DetailPageCloudinary detailPageCloudinary;
    @Autowired private AdminCloudinaryController adminCloudinaryController;

    public MultipartFile makeNewFile(int i) throws IOException {
        //given
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        File file;

        g2d.setColor(Color.red);
        g2d.fillRect(0,0,200,200);

        g2d.dispose();

        //when
        try{
            file = new File("output.png"+1);
            ImageIO.write(bufferedImage,"png",file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileItem fileItem = new DiskFileItem("originFile" + i, Files.probeContentType(file.toPath()), false,
                file.getName(), (int) file.length(), file.getParentFile());
        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        //jpa.png -> multipart 변환
        MultipartFile mFile = new CommonsMultipartFile(fileItem);
        return mFile;
    }


    @Test
    public void DItest() {
        Assertions.assertThat(detailPageCloudinary).isNotNull();
    }

    @Test
    void 업로드테스트() throws IOException {

        //when
        Map map;
        MultipartFile file = makeNewFile(1);

        //given
        map = detailPageCloudinary.upload(file.getBytes(),"Chair-1");

        //then
        Assertions.assertThat(map).isNotNull();
    }

    @Test
    void 이미지묶음_업로드() throws IOException {

        // when
        ArrayList<MultipartFile> fileArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fileArrayList.add(makeNewFile(i));
        }

        //given
        Map<String,String> imgUrlList = adminCloudinaryController.uploadDetailImages(fileArrayList);
        Set<String> stringSet = imgUrlList.keySet();
        Iterator<String> iterator = stringSet.iterator();
        while(iterator.hasNext()){
            String name = iterator.next();
            System.out.println(name + " : " + imgUrlList.get(name));
        }

        //then
        Assertions.assertThat(imgUrlList.size()).isEqualTo(3);
    }


}
