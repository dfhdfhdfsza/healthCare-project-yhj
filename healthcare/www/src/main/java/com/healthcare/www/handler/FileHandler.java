package com.healthcare.www.handler;

import com.healthcare.www.dto.ProductFileDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private String uploadDir; // 파일경로 application-file.properties 에서 주입

    public List<ProductFileDTO> uploadFile(MultipartFile[] files) throws RuntimeException {
        List<ProductFileDTO> productFileList = new ArrayList<>();
        // 업로드 디렉토리가 존재하지 않으면 생성합니다.
        LocalDate date = LocalDate.now(); // 오늘날짜 추출
        String today = date.toString();
        today = today.replace("-", File.separator); // "-" => "/" 변경
        File directory = new File(uploadDir, today); // 경로에 날짜를 더해서 폴더 경로 설정
        if (!directory.exists()) { // 폴더 경로가 존재 하지 않으면...
            directory.mkdirs();
        }

        // 파일의 중복을 방지하기 위해 고유한 파일명을 생성합니다.
        try {
            for(MultipartFile file : files) {
                UUID uuid = UUID.randomUUID();
                String fileName = uuid.toString() + "_" + Objects.requireNonNull(file.getOriginalFilename());

                // 파일을 저장할 경로를 설정합니다.
                Path targetLocation = Path.of(directory.getPath(), fileName);

                // 파일을 저장합니다.
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                ProductFileDTO productFileDTO = ProductFileDTO.builder()
                        .productFileName(file.getOriginalFilename()) // 이미지파일 이름
                        .productUUID(uuid.toString()) // uuid
                        .productFileSize(file.getSize()) // 이미지파일 크기
                        .productFileSaveDir(directory.getPath()) // 파일 경로 => c:/fileUpload/2024/01/25
                        .productFileType(file.getContentType()) // 파일 타입
                        .build();


                if(isImageFile(new File(directory, fileName))) {
                    File thumbNail = new File(directory, uuid.toString()+"_th_"+fileName);
                    Thumbnails.of(file.getInputStream()).size(75, 75).toFile(thumbNail);
                }
                productFileList.add(productFileDTO); // 파일객체 리스트 추가(DB저장용도)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productFileList;
    }
    private boolean isImageFile(File file) throws IOException {
        String mimeType = new Tika().detect(file);
        return mimeType.startsWith("image") ? true : false;
    }

}
