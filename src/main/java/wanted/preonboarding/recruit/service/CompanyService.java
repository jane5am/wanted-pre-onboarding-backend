package wanted.preonboarding.recruit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import wanted.preonboarding.dto.CreateCompanyDTO;
import wanted.preonboarding.recruit.Repository.CompanyRepository;
import wanted.preonboarding.recruit.domain.Company;
import wanted.preonboarding.recruit.domain.JobPosting;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(CreateCompanyDTO createCompanyDTO) {

        Company company = new Company();
        company.setName(createCompanyDTO.getName());
        try {
            return companyRepository.save(company);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to save job posting", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while saving job posting", e);
        }
    }

    public Company updateCompany(int id, CreateCompanyDTO createCompanyDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setName(createCompanyDTO.getName());
        return companyRepository.save(company);
    }

    public void deleteCompany(int id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.delete(company);
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
