package com.quang.spring.demo.service;

import com.quang.spring.demo.utils.ProjectObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericService {
   @Autowired
   ProjectObjectMapper mapper;
}
