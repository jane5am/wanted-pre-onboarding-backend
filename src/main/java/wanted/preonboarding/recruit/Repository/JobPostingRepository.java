package wanted.preonboarding.recruit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.recruit.domain.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
}
