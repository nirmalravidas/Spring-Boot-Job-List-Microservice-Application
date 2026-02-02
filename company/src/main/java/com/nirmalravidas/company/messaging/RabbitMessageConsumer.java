package com.nirmalravidas.company.messaging;

import com.nirmalravidas.company.dto.ReviewMessage;
import com.nirmalravidas.company.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMessageConsumer {
    private final CompanyService companyService;

    public RabbitMessageConsumer(CompanyService companyService){
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
