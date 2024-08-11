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
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class RecruitService {

    private final JobPostingRepository jobPostingRepository;

    public JobPosting createJobposting(CreateJobPostingtDto createJobPostingtDto) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setCompanyId(createJobPostingtDto.getCompanyId()); // 로그인 기능 구현 시 로그인되어 있는 ID값 가져오게 로직 수정 필요
        jobPosting.setCountry(createJobPostingtDto.getCountry());
        jobPosting.setRegion(createJobPostingtDto.getRegion());
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
        jobPosting.setCountry(updateJobPostingtDto.getCountry());
        jobPosting.setRegion(updateJobPostingtDto.getRegion());
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

    public void deleteJobPosting(int id){
        // 특정 ID의 JobPosting이 존재하지 않을 경우 NoSuchElementException을 발생시킴
        jobPostingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Job posting with ID " + id + " not found"));

        try {
            // JobPosting을 삭제 시도
            jobPostingRepository.deleteById(id);
        } catch (Exception e) {
            // 삭제 중 발생한 예외를 처리하고, 사용자에게 알림
            throw new RuntimeException("Failed to delete job posting with ID " + id, e);
        }
    }

    public List<JobPosting> getAllJobPostings(){

       return jobPostingRepository.findAll();

    }

    public List<JobPosting> searchJobPostings(String searchTerm) {
        
        return jobPostingRepository.searchJobPostings(searchTerm);

    }


}
