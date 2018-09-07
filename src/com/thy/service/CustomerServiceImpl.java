package com.thy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thy.dao.CustomerMapper;
import com.thy.dao.DictMapper;
import com.thy.pojo.BaseDict;
import com.thy.pojo.Customer;
import com.thy.pojo.QueryVo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public List<BaseDict> findDictByCode(String code) {
		List<BaseDict> list = dictMapper.findDictByCode(code);
		return list;
	}

	@Override
	public List<Customer> findCustomerByVo(QueryVo vo) {
		List<Customer> cusList = customerMapper.findCusByVo(vo);
		return cusList;
	}

	@Override
	public Integer findCountByVo(QueryVo vo) {
		
		Integer count = customerMapper.findCountByVo(vo);
		return count;
	}

	@Override
	public Customer findCustomerById(Long id) {
		Customer customer = customerMapper.findCustomerById(id);
		return customer;
	}

	@Override
	public void updateCusById(Customer customer) {
		customerMapper.updateCusById(customer);
	}

}
