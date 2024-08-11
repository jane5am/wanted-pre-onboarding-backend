package wanted.preonboarding.recruit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanted.preonboarding.recruit.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
