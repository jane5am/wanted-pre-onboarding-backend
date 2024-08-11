package wanted.preonboarding.recruit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import wanted.preonboarding.dto.CreateJobPostingtDto;
import wanted.preonboarding.dto.UpdateJobPostingtDto;
import wanted.preonboarding.recruit.Repository.JobPostingRepository;
import wanted.preonboarding.recruit.domain.JobPosting;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class RecruitService {

    private final JobPostingRepository jobPostingRepository;

    public JobPosting createJobposting(CreateJobPostingtDto createJobPostingtDto) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setCompanyId(createJobPostingtDto.getCompanyId());
        jobPosting.setPosition(createJobPostingtDto.getPosition());
        jobPosting.setCompensation(createJobPostingtDto.getCompensation());
        jobPosting.setDescription(createJobPostingtDto.getDescription());
        jobPosting.setSkill(createJobPostingtDto.getSkill());


        try {
            return jobPostingRepository.save(jobPosting);
        } catch (DataAccessException e) {
            // 데이터베이스 관련 예외를 잡아 새로운 예외를 던짐
            throw new RuntimeException("Failed to save job posting", e);
        } catch (Exception e) {
            // 그 외의 예외를 잡아 처리
            throw new RuntimeException("An unexpected error occurred while saving job posting", e);
        }
    }

    public JobPosting updateJobPosting(int id, UpdateJobPostingtDto updateJobPostingtDto) {
        JobPosting jobPosting = jobPostingRepository.findById(id).orElseThrow(() -> new RuntimeException("jobPosting not found"));
        jobPosting.setPosition(updateJobPostingtDto.getPosition());
        jobPosting.setCompensation(updateJobPostingtDto.getCompensation());
        jobPosting.setDescription(updateJobPostingtDto.getDescription());
        jobPosting.setSkill(updateJobPostingtDto.getSkill());

        try {
            return jobPostingRepository.save(jobPosting);
        } catch (DataAccessException e) {
            // 데이터베이스 관련 예외를 잡아 새로운 예외를 던짐
            throw new RuntimeException("Failed to save job posting", e);
        } catch (Exception e) {
            // 그 외의 예외를 잡아 처리
            throw new RuntimeException("An unexpected error occurred while saving job posting", e);
        }
    }


}
