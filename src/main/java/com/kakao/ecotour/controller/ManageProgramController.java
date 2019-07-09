package com.kakao.ecotour.controller;

import com.kakao.ecotour.service.ManageProgramService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Api(value = "생태 관광 프로그램 관리")
@RestController
@RequestMapping("/programs")
public class ManageProgramController {

    private final ManageProgramService manageProgramService;

    public ManageProgramController(ManageProgramService manageProgramService) {
        this.manageProgramService = manageProgramService;
    }

    @PostMapping
    @ApiOperation(value = "생태 관광 프로그램 추가")
    public void saveEcoProgram(@RequestBody EcoProgramCSV ecoProgramCSV) {
        manageProgramService.saveEcoProgram(ecoProgramCSV);
    }

    @PutMapping("/{prgmSeq}")
    @ApiOperation(value = "생태 관광 프로그램 수정")
    public void updateEcoProgram(@RequestBody EcoProgramCSV ecoProgramCSV) {
        manageProgramService.saveEcoProgram(ecoProgramCSV);
    }

    @PostMapping("/load")
    @ApiOperation(value = "생태 관광 프로그램 데이터셋(csv) 저장")
    public void loadCsvFile(@RequestParam("fileName") String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        //final String fileName = "C:\\Users\\whrudcks\\Desktop\\ecotour.csv";

        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "euc-kr");
        CSVReader reader = new CSVReader(isr);

        List<EcoProgramCSV> ecoProgramCSVList = new CsvToBeanBuilder<EcoProgramCSV>(reader)
                .withType(EcoProgramCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();

        ecoProgramCSVList.forEach(manageProgramService::saveEcoProgram);

    }
}


