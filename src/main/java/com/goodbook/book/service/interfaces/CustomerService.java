package com.goodbook.book.service.interfaces;

import com.goodbook.book.model.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    void create(CustomerDto customerDto) throws Exception;


}
