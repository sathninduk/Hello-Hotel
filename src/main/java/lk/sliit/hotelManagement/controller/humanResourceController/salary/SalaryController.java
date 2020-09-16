package lk.sliit.hotelManagement.controller.humanResourceController.salary;

import lk.sliit.hotelManagement.controller.SuperController;
import lk.sliit.hotelManagement.dto.hr.SalaryDTO;
import lk.sliit.hotelManagement.dto.manager.EmployeeDTO;
import lk.sliit.hotelManagement.service.custom.HumanResourceBO;
import lk.sliit.hotelManagement.service.custom.IndexLoginBO;
import lk.sliit.hotelManagement.service.custom.ManageBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SalaryController {
    @Autowired
    IndexLoginBO indexLoginBO;
    @Autowired
    ManageBO manageBO;
    @Autowired
    HumanResourceBO humanResourceBO;
    @GetMapping("/salary")
    public String salary(Model model) {
        List<EmployeeDTO> p = humanResourceBO.findAllUserwithOT();
        List<SalaryDTO> p2 = humanResourceBO.findAllsalaryStateNotFalse();
        model.addAttribute("loadAllUsers", p);
        model.addAttribute("listEmployeesTableSalary", p2);
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "salary";
    }
    //Load All Salaries To a Table
    @GetMapping("/allSalary")
    public ModelAndView loadAllSalary(Model model, @ModelAttribute EmployeeDTO employee, HttpServletRequest request, HttpServletResponse response) throws ServletException, IllegalStateException, IOException {
        ModelAndView mav = new ModelAndView ( "allSalary" );
        mav.addObject ( "listEmployeesTableSalarya", humanResourceBO.findAllSalary ( ) );
        model.addAttribute ( "loggerName", indexLoginBO.getEmployeeByIdNo ( SuperController.idNo ) );
        return mav;
    }
    @PostMapping("salarySave")
    public String loadInvoicePage(@ModelAttribute SalaryDTO salaryDTO, Model model, HttpServletRequest request) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));

        try {
            SalaryDTO employeeDTO1 = humanResourceBO.findHighestSalaryId();
            SalaryDTO employeeDTO2 = null;
            try {
                employeeDTO2 = humanResourceBO.findSalarybyId(salaryDTO.getSalaryId());
            }catch (NullPointerException d){
                int maxId = (employeeDTO1.getSalaryId());
                if (salaryDTO.getSalaryId()==(maxId)) {
                    salaryDTO.setSalaryId((maxId));
                } else {
                    maxId++;
                    salaryDTO.setSalaryId((maxId));
                }
            }

        } catch (NullPointerException e){
            salaryDTO.setSalaryId(1);
        }
        humanResourceBO.saveSalary(salaryDTO);
        return "redirect:/salary";
    }
}

