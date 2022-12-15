package com.example.NestDigitalApplication.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AdminController {
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> AddEmployee(@RequestBody Employee emp){
        List<Employee> emp1 = (List<Employee>) empdao.UserLoginDetailsByCred(emp.getUsername(), emp.getPassword(), emp.getEmpCode());
        HashMap<String, String> hashMap = new HashMap<>();
        if(emp1.size()==0){
            LocalDateTime now = LocalDateTime.now();
            empdao.save(emp);
            List<Employee> result = (List<Employee>) empdao.UserLoginDetailsById(emp.getEmpCode());
            Leaves1 l1 = new Leaves1();
            l1.setEmpId(String.valueOf(result.get(0).getId()));
            l1.setYear(dtf.format(now));
            l1.setCasualLeave(20);
            l1.setSickLeave(7);
            l1.setSpecialLeave(3);
            l1dao.save(l1);
            hashMap.put("status","success");
        }else{
            hashMap.put("status","failed");
        }
        return hashMap;
    }
}
