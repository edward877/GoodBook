package com.goodbook.book.service.impl;

import com.goodbook.book.dao.CustomerDao;
import com.goodbook.book.model.CustomerDto;
import com.goodbook.book.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;


    @Override
    public void create(CustomerDto customerDto) throws Exception{
        if(checkCustomer(customerDto)){
            customerDao.save(customerDto);
        }
    }

    private boolean checkCustomer(CustomerDto customerDto) throws Exception {
        if (!customerDao.existsByEmail(customerDto.getEmail())){
            if (!customerDao.existsByPhoneNumber(customerDto.getPhoneNumber())) {
                return true;
            } else {
                throw new Exception("phone number already exist");
            }
        }else {
            throw new Exception("email already exist");
        }
    }
}
