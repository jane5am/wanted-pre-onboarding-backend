package wanted.preonboarding.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.preonboarding.dto.CreateJobPostingtDto;
import wanted.preonboarding.dto.ResponseMessage;
import wanted.preonboarding.recruit.domain.JobPosting;
import wanted.preonboarding.recruit.service.RecruitService;

@RestController
@RequestMapping("/api/v1/recruit")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    // 채용 공고 등록
    @PostMapping
    public ResponseEntity<ResponseMessage> createJobPosting(@RequestBody CreateJobPostingtDto createJobPostingtDto) {
        JobPosting jobposting = recruitService.createJobposting(createJobPostingtDto);
        ResponseMessage response = ResponseMessage.builder()
                .data(jobposting)
                .statusCode(201)
                .resultMessage("Product created successfully")
                .build();
        return ResponseEntity.status(201).body(response);
    }

}
