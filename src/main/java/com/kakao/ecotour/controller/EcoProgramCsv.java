package com.kakao.ecotour.controller;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Getter
public class EcoProgramCsv {

    @CsvBindByName(column = "번호", required = true)
    private long prgmSeq;

    @CsvBindByName(column = "프로그램명", required = true)
    private String prgmName;

    @CsvBindByName(column = "테마별 분류", required = true)
    private String theme;

    @CsvBindByName(column = "서비스 지역", required = true)
    private String region;

    @CsvBindByName(column = "프로그램 소개")
    private String prgmInfo = "";

    @CsvBindByName(column = "프로그램 상세 소개")
    private String prgmDetailInfo = "";

}
