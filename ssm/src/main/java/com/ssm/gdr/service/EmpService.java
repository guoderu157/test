package com.ssm.gdr.service;

import com.ssm.gdr.entity.Emp;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface EmpService {
    /**
     * 查询全部
     * @return
     */
    List<Emp> queryAllEmp();//

    int addEmp(Emp emp);//添加

    int deleteEmpById(int id);//删除

    int updateEmp(Emp emp);//修改

    Emp queryById(int id);//按id查询

    List<Emp> queryByName(String name);

    String InputExcel(InputStream is, String originalFilename);

}
