package com.nirmalravidas.job.clients;

import com.nirmalravidas.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY")
public interface CompanyClient {

    @GetMapping("/api/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
