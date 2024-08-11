package wanted.preonboarding.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.preonboarding.dto.CreateCompanyDTO;
import wanted.preonboarding.dto.ResponseMessage;
import wanted.preonboarding.recruit.domain.Company;
import wanted.preonboarding.recruit.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createCompany(@RequestBody CreateCompanyDTO createCompanyDTO) {
        Company company = companyService.createCompany(createCompanyDTO);
        ResponseMessage response = ResponseMessage.builder()
                .data(company)
                .statusCode(201)
                .resultMessage("Company created successfully")
                .build();
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateCompany(@PathVariable int id, @RequestBody CreateCompanyDTO createCompanyDTO) {
        Company updatedCompany = companyService.updateCompany(id, createCompanyDTO);
        ResponseMessage response = ResponseMessage.builder()
                .data(updatedCompany)
                .statusCode(200)
                .resultMessage("Company updated successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        ResponseMessage response = ResponseMessage.builder()
                .statusCode(200)
                .resultMessage("Company deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getCompanyById(@PathVariable int id) {
        Company company = companyService.getCompanyById(id);
        ResponseMessage response = ResponseMessage.builder()
                .data(company)
                .statusCode(200)
                .resultMessage("Company retrieved successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        ResponseMessage response = ResponseMessage.builder()
                .data(companies)
                .statusCode(200)
                .resultMessage("All companies retrieved successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
