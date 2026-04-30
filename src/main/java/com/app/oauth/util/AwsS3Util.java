package com.app.oauth.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3Util {
    private final S3Client s3Client;
    private final String bucket = "testapp-gyuhoroh213589";

    public String getPath(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
    }

    // 1개 업로드
    public String uploadFile(MultipartFile uploadFile) throws IOException {
        String dataPath = getPath();
        String url = null;

        // 이미지면 저장
        if(uploadFile.getContentType().startsWith("image")){

            String uuid = UUID.randomUUID().toString();

            String originalFilename = uploadFile.getOriginalFilename();
            String uploadFileName = uuid + "-" + originalFilename;

            String key = dataPath + uploadFileName;


            // 이미지 추가
            s3Client.putObject(
                    PutObjectRequest
                            .builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(uploadFile.getContentType())
                            .build(),
                    RequestBody.fromInputStream(uploadFile.getInputStream(), uploadFile.getSize())
            );
            // 이미지 경로를 urls에 추가
            url = "/" + key;

            // 썸네일 제작
            String thumbName = "t_" + uploadFileName;
            String thumbKey = dataPath + thumbName;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Thumbnailator.createThumbnail(
                    uploadFile.getInputStream(),
                    byteArrayOutputStream,
                    100,
                    100);

            byte[] thumbBytes = byteArrayOutputStream.toByteArray();
            // 썸네일 등록
            s3Client.putObject(
                    PutObjectRequest
                            .builder()
                            .bucket(bucket)
                            .key(thumbKey)
                            .contentType(uploadFile.getContentType())
                            .build(),
                    RequestBody.fromBytes(thumbBytes)
            );

            byteArrayOutputStream.close();
        }
        return url;
    }

    // 여러개 업로드
    public List<String> uploadFiles(List<MultipartFile> uploadFiles) throws IOException{
        String dataPath = getPath();
        List<String> urls = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){

            // 이미지면 저장
            if(uploadFile.getContentType().startsWith("image")){

                String uuid = UUID.randomUUID().toString();

                String originalFilename = uploadFile.getOriginalFilename();
                String uploadFileName = uuid + "-" + originalFilename;

                String key = dataPath + uploadFileName;
                String url = "/" + key;

                // 이미지 추가
                s3Client.putObject(
                        PutObjectRequest
                                .builder()
                                .bucket(bucket)
                                .key(key)
                                .contentType(uploadFile.getContentType())
                                .build(),
                        RequestBody.fromInputStream(uploadFile.getInputStream(), uploadFile.getSize())
                );
                // 이미지 경로를 urls에 추가
                urls.add(url);

                // 썸네일 제작
                String thumbName = "t_" + uploadFileName;
                String thumbKey = dataPath + thumbName;

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Thumbnailator.createThumbnail(
                        uploadFile.getInputStream(),
                        byteArrayOutputStream,
                        100,
                        100);

                byte[] thumbBytes = byteArrayOutputStream.toByteArray();
                // 썸네일 등록
                s3Client.putObject(
                        PutObjectRequest
                                .builder()
                                .bucket(bucket)
                                .key(thumbKey)
                                .contentType(uploadFile.getContentType())
                                .build(),
                        RequestBody.fromBytes(thumbBytes)
                );

                byteArrayOutputStream.close();
            }
        }
        return urls;
    }

    // key -> image(byte[]) 리턴해주는 값이 필요
    public byte[] display(String key){
        ResponseBytes objectAsBytes = s3Client.getObjectAsBytes(
                GetObjectRequest
                        .builder()
                        .bucket(bucket)
                        .key(key)
                        .build()
        );

        return objectAsBytes.asByteArray();
    }









}
