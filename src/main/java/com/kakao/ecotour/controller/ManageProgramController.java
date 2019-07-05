package com.kakao.ecotour.controller;

import com.kakao.ecotour.dto.EcoProgramDto;
import com.kakao.ecotour.service.ManageProgramService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/programs")
public class ManageProgramController {

    private final ManageProgramService manageProgramService;

    public ManageProgramController(ManageProgramService manageProgramService) {
        this.manageProgramService = manageProgramService;
    }


    @PostMapping
    public @ResponseBody EcoProgramDto saveEcoProgram(@RequestBody EcoProgramDto ecoProgramDto) {
        return manageProgramService.saveEcoProgram(ecoProgramDto);
    }

    @PutMapping("/{prgmSeq}")
    public @ResponseBody EcoProgramDto updateEcoProgram(@PathVariable("prgmSeq") long prgmSeq, @RequestBody EcoProgramDto ecoProgramDto) {
        return manageProgramService.saveEcoProgram(ecoProgramDto);
    }


    @PostMapping("/load")
    public void loadCsvFile(@RequestParam("fileName") String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        //final String fileName = "C:\\Users\\whrudcks\\Desktop\\ecotour.csv";

        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "euc-kr");
        CSVReader reader = new CSVReader(isr);

        List<EcoProgramDto> ecoProgramDtoList = new CsvToBeanBuilder<EcoProgramDto>(reader)
                .withType(EcoProgramDto.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build().parse();

        ecoProgramDtoList.forEach(manageProgramService::saveEcoProgram);

    }

}


