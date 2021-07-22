package com.example.demo.repository;

import com.example.demo.model.SendgridMailTemplate;

import java.util.List;

public interface SendgridMailTemplateRepository {
    List<SendgridMailTemplate> findAll();
}
