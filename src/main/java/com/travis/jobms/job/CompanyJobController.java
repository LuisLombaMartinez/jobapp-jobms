package com.travis.jobms.job;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}/jobs")
public class CompanyJobController {

    private JobService jobService;

    public CompanyJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getByCompanyId(@PathVariable UUID companyId) {
        return ResponseEntity.ok(jobService.getByCompanyId(companyId));
    }
}
