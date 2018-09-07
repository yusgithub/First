package com.thy.service;

import java.util.List;

import com.thy.pojo.BaseDict;
import com.thy.pojo.Customer;
import com.thy.pojo.QueryVo;

public interface CustomerService {
	public List<BaseDict> findDictByCode(String code);
	public List<Customer> findCustomerByVo(QueryVo vo);
	public Integer findCountByVo(QueryVo vo);
	
	public Customer findCustomerById(Long id);
	public void updateCusById(Customer customer);
}
