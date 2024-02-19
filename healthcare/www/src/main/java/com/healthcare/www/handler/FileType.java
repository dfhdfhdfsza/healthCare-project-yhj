package com.healthcare.www.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileType { // 파일업로드 경로선택 용도
    PRODUCT, USER, COMMUNITY;
}
