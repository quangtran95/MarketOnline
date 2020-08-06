package com.quang.spring.demo.controller;

import com.quang.spring.demo.dao.EmployeeDao;
import com.quang.spring.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
   @Autowired
   private EmployeeDao employeeDao;

   @RequestMapping("/")
   @ResponseBody
   public String welcome() {
      return "Welcome to Spring Boot + REST + JWT Example.";
   }

   @RequestMapping("/test")
   @ResponseBody
   public String test() {
      return "{greeting: 'Hello'}";
   }

   // URL:
   // http://localhost:8080/employees
   @RequestMapping(value = "/employees", //
         method = RequestMethod.GET, //
         produces = { MediaType.APPLICATION_JSON_VALUE, //
               MediaType.APPLICATION_XML_VALUE })
   @ResponseBody
   public List<Employee> getEmployees() {
      List<Employee> list = employeeDao.getAllEmployees();
      return list;
   }

   // URL:
   // http://localhost:8080/employee/{empNo}
   @RequestMapping(value = "/employee/{empNo}", //
         method = RequestMethod.GET, //
         produces = { MediaType.APPLICATION_JSON_VALUE, //
               MediaType.APPLICATION_XML_VALUE })
   @ResponseBody
   public Employee getEmployee(@PathVariable("empNo") String empNo) {
      return employeeDao.getEmployee(empNo);
   }

   // URL:
   // http://localhost:8080/employee

   @RequestMapping(value = "/employee", //
         method = RequestMethod.POST, //
         produces = { MediaType.APPLICATION_JSON_VALUE, //
               MediaType.APPLICATION_XML_VALUE })
   @ResponseBody
   public Employee addEmployee(@RequestBody Employee emp) {

      System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());

      return employeeDao.addEmployee(emp);
   }

   // URL:
   // http://localhost:8080/employee
   @RequestMapping(value = "/employee", //
         method = RequestMethod.PUT, //
         produces = { MediaType.APPLICATION_JSON_VALUE, //
               MediaType.APPLICATION_XML_VALUE })
   @ResponseBody
   public Employee updateEmployee(@RequestBody Employee emp) {

      System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

      return employeeDao.updateEmployee(emp);
   }

   // URL:
   // http://localhost:8080/employee/{empNo}
   @RequestMapping(value = "/employee/{empNo}", //
         method = RequestMethod.DELETE, //
         produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
   @ResponseBody
   public void deleteEmployee(@PathVariable("empNo") String empNo) {

      System.out.println("(Service Side) Deleting employee: " + empNo);

      employeeDao.deleteEmployee(empNo);
   }
}
