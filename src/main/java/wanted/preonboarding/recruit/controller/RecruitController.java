package wanted.preonboarding.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.preonboarding.dto.CreateJobPostingtDto;
import wanted.preonboarding.dto.JobPostingResponseDto;
import wanted.preonboarding.dto.ResponseMessage;
import wanted.preonboarding.dto.UpdateJobPostingtDto;
import wanted.preonboarding.recruit.domain.JobPosting;
import wanted.preonboarding.recruit.service.RecruitService;

import java.util.List;
import java.util.Optional;

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

    // 채용 공고 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateJobPosting(@PathVariable("id") int id, @RequestBody UpdateJobPostingtDto updateJobPostingtDto) {
        JobPosting jobposting = recruitService.updateJobPosting(id, updateJobPostingtDto);
        ResponseMessage response = ResponseMessage.builder()
                .data(jobposting)
                .statusCode(201)
                .resultMessage("Product created successfully")
                .build();
        return ResponseEntity.status(201).body(response);
    }

    // 채용 공고 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteJobPosting(@PathVariable("id") int id){
        recruitService.deleteJobPosting(id);
        ResponseMessage response = ResponseMessage.builder()
                .data("")
                .statusCode(201)
                .resultMessage("Product created successfully")
                .build();
        return ResponseEntity.status(201).body(response);
    }

    // 채용 공고 전체 조회
    @GetMapping("/jobPostings")
    public ResponseEntity<ResponseMessage> getAllJobPostings() {
        List<JobPostingResponseDto> jobPostings = recruitService.getAllJobPostings();
        ResponseMessage response = ResponseMessage.builder()
                .data(jobPostings)
                .statusCode(200)
                .resultMessage("Success")
                .build();
        return ResponseEntity.ok(response);
    }

    // 채용 공고 검색
    @GetMapping("/search")
    public ResponseEntity<ResponseMessage> searchJobPostings(@RequestParam String search) {
        List<JobPosting> jobPostings = recruitService.searchJobPostings(search);
        ResponseMessage response = ResponseMessage.builder()
                .data(jobPostings)
                .statusCode(200)
                .resultMessage("Success")
                .build();
        return ResponseEntity.ok(response);
    }

    // 체용 공고 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getJobPostingById(@PathVariable("id") int id) throws BadRequestException {

        Optional<JobPosting> jobPosting = recruitService.getJobPostingById(id);
        ResponseMessage response = ResponseMessage.builder()
                .data(jobPosting.get())
                .statusCode(200)
                .resultMessage("Job posting retrieved successfully")
                .build();
        return ResponseEntity.ok(response);

    }

}
