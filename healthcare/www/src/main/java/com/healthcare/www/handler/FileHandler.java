package com.healthcare.www.handler;

import com.healthcare.www.product.domain.ProductImageFile;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class FileHandler {
    @Value("${product.file.upload-dir}")
    private String uploadDir;

    public List<ProductImageFile> uploadFile(MultipartFile[] files) throws IOException {
        List<ProductImageFile> productImageFileList = new ArrayList<>();
        // 업로드 디렉토리가 존재하지 않으면 생성합니다.
        LocalDate date = LocalDate.now(); // 오늘날짜 추출
        String today = date.toString();
        today = today.replace("-", File.separator); // "-" => "/" 변경
        File directory = new File(uploadDir, today); // 경로에 날짜를 더해서 폴더 경로 설정
        if (!directory.exists()) { // 폴더 경로가 존재 하지 않으면...
            directory.mkdirs();
        }

        // 파일의 중복을 방지하기 위해 고유한 파일명을 생성합니다.
        for(MultipartFile file : files) {
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString() + "_" + Objects.requireNonNull(file.getOriginalFilename());

            // 파일을 저장할 경로를 설정합니다.
            Path targetLocation = Path.of(uploadDir, fileName);

            // 파일을 저장합니다.
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            ProductImageFile productImageFile = ProductImageFile.builder()
                    .productImageName(file.getName()) // 이미지파일 이름
                    .productUUID(uuid.toString()) // uuid
                    .productImageSize(file.getSize()) // 이미지파일 크기
                    .productImageSaveDir(today) // 파일 경로
                    .build();

            try {
                if(isImageFile(file.getInputStream())) {
                    File thumNail = new File(directory, uuid.toString()+"_th_"+fileName);
                    Thumbnails.of(file.getInputStream()).size(75, 75).toFile(thumNail);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            productImageFileList.add(productImageFile);
        }
        return productImageFileList;
    }
    private boolean isImageFile(InputStream storeFile) throws IOException {
        String mimeType = new Tika().detect(storeFile);
        return mimeType.startsWith("image") ? true : false;
    }

}
