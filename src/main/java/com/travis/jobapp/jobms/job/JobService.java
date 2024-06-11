package com.travis.jobapp.jobms.job;

import java.util.List;
import java.util.UUID;

public interface JobService {

    List<Job> getAll();

    UUID create(Job job);

    Job getById(UUID id);

    void deleteById(UUID id);

    Job update(UUID id, Job updatedJob);

    List<Job> getByCompanyId(UUID companyId);
}
