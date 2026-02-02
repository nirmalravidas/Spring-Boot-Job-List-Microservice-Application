package com.nirmalravidas.company.service;

import com.nirmalravidas.company.clients.ReviewClient;
import com.nirmalravidas.company.dto.ReviewMessage;
import com.nirmalravidas.company.model.Company;
import com.nirmalravidas.company.repository.CompanyRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id){
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow();

        if (company.isDeleted()) {
            return false;
        }

        company.setDeleted(true);
        companyRepository.save(company);

        return true;
    }

    @Override
    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(()-> new NotFoundException("Company Not found"+ reviewMessage.getCompanyId()));

        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
