package com.ssm.gdr.dao;


import com.ssm.gdr.entity.Emp;

import java.util.List;
import java.util.Map;


public interface EmpDao {

    int addEmp(Emp emp);

    int deleteEmpById(int id);

    int updateEmp(Emp emp);

    Emp queryById(int id);

    List<Emp> queryAllEmp();

    List<Emp> queryByName(String name);

    void InputExcel(Map<String, Object> ginsengMap);

}
