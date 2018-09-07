package com.thy.dao;

import java.util.List;

import com.thy.pojo.Customer;
import com.thy.pojo.QueryVo;

public interface CustomerMapper {
	public List<Customer> findCusByVo(QueryVo vo);
	public Integer findCountByVo(QueryVo vo);
	
	public Customer findCustomerById(Long id);
	public void updateCusById(Customer customer);
}
