package com.ssm.gdr.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.gdr.entity.Emp;
import com.ssm.gdr.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired

    private EmpService empService;

    /*
    *分页查询全部
     */
    @RequestMapping("/allemp")

    public String list(Model model,@RequestParam(defaultValue="1",required=true,value="pageNo") Integer pageNo) {
        Integer pageSize=4;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo, pageSize);
        List<Emp> list = empService.queryAllEmp();//获取所有用户信息
        PageInfo<Emp> pageInfo=new PageInfo<Emp>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "allemp";

    }

    /*
     *去往添加界面
     */

    @RequestMapping("/toAddemp")

    public String toAddEmp() {

        return "addemp";

    }

    /*
    *  按照添加
     */

    @RequestMapping("/addemp")

    public String addEmp(Emp emp) {
        empService.addEmp(emp);
        return "redirect:/emp/allemp";
    }


    /*
    *按照id删除
     */

    @RequestMapping("/del/{empId}")

    public String deleteemp(@PathVariable("empId") int id) {

        empService.deleteEmpById(id);

        return "redirect:/emp/allemp";

    }

    /*
    *去往更新界面
     */

    @RequestMapping("/toUpdateemp")

    public String toUpdateemp(Model model, int id) {

        model.addAttribute("emp", empService.queryById(id));

        return "updateemp";

    }

    /*
    *按照id更新
     */


    @RequestMapping("/updateemp")

    public String updatePaper(Model model, Emp emp) {

        empService.updateEmp(emp);

        emp = empService.queryById(emp.getId());

        model.addAttribute("emp", emp);

        return "redirect:/emp/allemp";

    }

    @RequestMapping("/selectemp")
    public String list1(Model model,Emp emp,@RequestParam(defaultValue="1",required=true,value="pageNo") Integer pageNo) {
        Integer pageSize=4;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo, pageSize);
        List<Emp> list = empService.queryByName(emp.getName());//获取固定姓名信息
        PageInfo<Emp> pageInfo=new PageInfo<Emp>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "allemp";

    }

}




