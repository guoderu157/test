package com.ssm.gdr.service.impl;

import com.ssm.gdr.dao.EmpDao;
import com.ssm.gdr.entity.Emp;
import com.ssm.gdr.service.EmpService;
import com.ssm.gdr.uitl.ExcelUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceimpl implements EmpService {

        @Autowired
        private EmpDao empDao;



        @Override
        public int addEmp(Emp emp) {

            return empDao.addEmp(emp);

        }



        @Override
        public int deleteEmpById(int id) {

            return empDao.deleteEmpById(id);

        }



        @Override
        public int updateEmp(Emp emp) {

            return empDao.updateEmp(emp);

        }



        @Override
        public Emp queryById(int id) {

            return empDao.queryById(id);

        }



        @Override
        public List<Emp> queryAllEmp() {

            return empDao.queryAllEmp();

        }

        @Override
        public  List<Emp> queryByName(String name) {

            return empDao.queryByName(name);
        }

    @Override
    public String InputExcel(InputStream is, String originalFilename) {
        Map<String,Object> ginsengMap = new HashMap<String,Object>();
        List<ArrayList<Object>> list;
        if (originalFilename.endsWith(".xls")) {
            list = ExcelUtil.readExcel2003(is);
        } else {
            list = ExcelUtil.readExcel2007(is);
        }
        for (int i=0,j=list.size();i<j;i++){
            List<Object> row = list.get(i);
            ginsengMap.put("name", row.get(0).toString());
            ginsengMap.put("age", row.get(1).toString());
            ginsengMap.put("sex", row.get(2).toString());
            ginsengMap.put("email", row.get(3).toString());
            ginsengMap.put("phone", row.get(4).toString());
            empDao.InputExcel(ginsengMap);
        }
        return "01";
    }


}
