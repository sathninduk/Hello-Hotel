package lk.sliit.employeeManagement.controller.inventoryController;

import lk.sliit.employeeManagement.controller.SuperController;
import lk.sliit.employeeManagement.service.custom.IndexLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantStockController {
    @Autowired
    IndexLoginBO indexLoginBO;
    @GetMapping("/restaurantStock")
    public String loginPage(Model model){
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "restaurantStock";
    }
}
