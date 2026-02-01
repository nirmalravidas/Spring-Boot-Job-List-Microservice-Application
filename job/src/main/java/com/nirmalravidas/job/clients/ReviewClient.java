package com.nirmalravidas.job.clients;

import com.nirmalravidas.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE", url = "${review-service.url}")
public interface ReviewClient {

    @GetMapping("/api/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
