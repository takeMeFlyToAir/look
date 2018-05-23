package com.zzr.web.controller;

import com.zzr.business.service.CustomerService;
import com.zzr.util.common.JsonResult;
import com.zzr.util.common.JsonResultToValid;
import com.zzr.util.page.Pager;
import com.zzr.util.page.PagerResult;
import com.zzr.vo.customer.CustomerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sjgtw-zzr on 2018/3/8.
 */
@Slf4j
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public String customerList(){
        return "customer/customer_list";
    }

    @RequestMapping(value = "loadCustomer",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PagerResult<CustomerVO> loadCustomer(Pager<CustomerVO> customerVOPager, CustomerVO customerVO){
        customerVOPager.setCondition(customerVO);
        return customerService.findPage(customerVOPager);
    }

    @RequestMapping(value = "/edit/{customerId}",method = RequestMethod.GET)
    public String customerEdit(@PathVariable Long customerId,Model model){
        if(customerId == null || customerId == 0){
            model.addAttribute("customer",new CustomerVO());
            return "customer/customer_edit";
        }
        CustomerVO customer = customerService.getById(customerId);
        model.addAttribute("customer",customer);
        return "customer/customer_edit";
    }
    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public JsonResult customerEdit(CustomerVO customer){
        try {
            if(customer.getId() == null || customer.getId() == 0){
                CustomerVO result = customerService.add(customer);
                if(result == null || result.getId() == null || result.getId() == 0){
                    log.error("添加客户失败，客户{}",result);
                    return new JsonResult(false,"客户添加失败");
                }
                return new JsonResult(true,"添加客户成功");
            }else{
                customerService.update(customer);
                return new JsonResult(true,"客户更新成功");
            }
        }catch (Exception e){
            log.error("客户更新或者添加出错",e);
        }
        return new JsonResult(false,"操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCustomer",method = RequestMethod.POST)
    public JsonResult deleteCustomer(@RequestParam(value = "ids") List<Long> ids){
        try {
            if(ids.isEmpty()){
                return new JsonResult(false,"请选择要删除的数据");
            }
            customerService.delList(ids);
            return new JsonResult(true,"客户删除成功");
        }catch (Exception e){
            log.error("客户更新或者添加出错",e);
        }
        return new JsonResult(false,"操作失败");
    }
}

