package com.ssm.gdr.controller;


import com.ssm.gdr.entity.Emp;
import com.ssm.gdr.service.EmpService;
import com.ssm.gdr.uitl.ExcelUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    EmpService empService;
    protected Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/input")
    @ResponseBody
    public String InputExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String flag = "02";// 上传标志
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();// 原文件名字
                log.info("文件名：" + originalFilename);
                InputStream is = file.getInputStream();// 获取输入流
                flag = empService.InputExcel(is, originalFilename);
            } catch (Exception e) {
                flag = "03";// 上传出错
                e.printStackTrace();
            }
        }
        return flag;
    }



    @RequestMapping(value = "/output", produces = "application/form-data; charset=utf-8")
    @ResponseBody
    public String OutputExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html,charset=utf-8");

        List<Emp> list = empService.queryAllEmp();

        String message = ExcelUtil.OutExcel(request, response, list);
        if (message.equals("fail")) {
            ServletOutputStream out = response.getOutputStream();
            message = "导出失败，请重试";
            String s = "<!DOCTYPE HTML><html><head><script>alert('" + message + "');</script></head><body></body></html>";
            out.print(s);
        }
        return null;
    }
 }
