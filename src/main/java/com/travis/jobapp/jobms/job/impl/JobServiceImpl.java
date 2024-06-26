package com.travis.jobapp.jobms.job.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.travis.jobapp.jobms.exception.ResourceNotFoundException;
import com.travis.jobapp.jobms.job.Job;
import com.travis.jobapp.jobms.job.JobRepository;
import com.travis.jobapp.jobms.job.JobService;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public UUID create(Job job) {
        if (job.getId() != null) {
            throw new IllegalArgumentException("A new job cannot already have an ID");
        }
        if (job.getCompanyId() == null) {
            throw new IllegalArgumentException("A job must have a company");
        }
        return jobRepository.save(job).getId();
    }

    @Override
    public Job getById(UUID id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));
    }

    @Override
    public void deleteById(UUID id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Job not found with id " + id);
        }
    }

    @Override
    public Job update(UUID id, Job updatedJob) {
        return jobRepository.findById(id)
                .map(job -> {
                    job.updateFrom(updatedJob);
                    return jobRepository.save(job);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));
    }

    @Override
    public List<Job> getByCompanyId(UUID companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

}
