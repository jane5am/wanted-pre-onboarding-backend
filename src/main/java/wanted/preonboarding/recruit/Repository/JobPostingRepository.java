package wanted.preonboarding.recruit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.recruit.domain.JobPosting;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {

    @Query("SELECT j FROM JobPosting j WHERE " +
            "(LOWER(j.country) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND j.country <> '') OR " +
            "(LOWER(j.region) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND j.region <> '') OR " +
            "(LOWER(j.position) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND j.position <> '') OR " +
            "(LOWER(j.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND j.description <> '') OR " +
            "(LOWER(j.skill) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND j.skill <> '')")
    List<JobPosting> searchJobPostings(@Param("searchTerm") String searchTerm);

}
