package com.thy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.utils.Page;

import com.thy.pojo.BaseDict;
import com.thy.pojo.Customer;
import com.thy.pojo.QueryVo;
import com.thy.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
			
	@Value("${customer.dict.resource}")
	private String source;
	@Value("${customer.dict.industry}")
	private String industry;
	@Value("${customer.dict.level}")
	private String level;
	
	@RequestMapping("/list")
	public String list(Model model , QueryVo vo) throws Exception{
		//客户来源
		List<BaseDict> sourceList = customerService.findDictByCode(source);
		//所属行业
		List<BaseDict> industryList = customerService.findDictByCode(industry);
		//客户级别
		List<BaseDict> typeList = customerService.findDictByCode(level);
		//将名字转码  不判断为空的话  回空指针异常
		if(vo.getCustName()!=null){
			vo.setCustName(new String(vo.getCustName().getBytes("iso8859-1"),"utf-8"));
		}
		
		if(vo.getPage() == null)
		{
			vo.setPage(1);
		}
		//设置查询的起始记录条数
		vo.setStart((vo.getPage()-1) * vo.getSize());
		
		//查询数据列表和总数据数
		List<Customer> resultList = customerService.findCustomerByVo(vo);
		Integer count = customerService.findCountByVo(vo);
		
		Page<Customer> page = new Page<Customer>();
		page.setTotal(count); //数据总数
		page.setPage(vo.getPage()); //当前页
		page.setRows(resultList);//数据列表
		page.setSize(vo.getSize());//每页显示条数
		
		model.addAttribute("page",page);
		
		//高级查询下拉表显示
		model.addAttribute("fromType",sourceList);
		model.addAttribute("industryType",industryList);
		model.addAttribute("levelType",typeList);
		
		//高级查询选中数据回显
		model.addAttribute("custName",vo.getCustName());
		model.addAttribute("custSource",vo.getCustSource());
		model.addAttribute("custIndustry",vo.getCustIndustry());
		model.addAttribute("custLevel",vo.getCustLevel());
		return "customer";
	}
	@RequestMapping("/detail")
	@ResponseBody
	public  Customer detail(Long id){
		Customer customer = customerService.findCustomerById(id);
		return customer;
	}
	@RequestMapping("/update")
	public String update(Customer customer) throws Exception{
		
		customerService.updateCusById(customer);
		return "customer";
	}
	
}
