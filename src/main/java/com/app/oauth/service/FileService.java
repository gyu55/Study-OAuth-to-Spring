package com.app.oauth.service;

import com.app.oauth.domain.dto.response.ApiResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    // 파일 1개 업로드
    public ApiResponseDTO upload(MultipartFile uploadFile);

    // 파일 여러개 업로드
    public ApiResponseDTO uploads(List<MultipartFile> uploadFiles);

    // 절대 경로를 숨겨서 display
    public byte[] getDisplayPath(String fileName);
}
