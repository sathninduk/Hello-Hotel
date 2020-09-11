package lk.sliit.hotelManagement.service.custom.impl;

import lk.sliit.hotelManagement.dao.banquetDAO.BanquetBillDAO;
import lk.sliit.hotelManagement.dao.banquetDAO.BanquetOrderDAO;
import lk.sliit.hotelManagement.dao.kitchenDAO.MenuDAO;
import lk.sliit.hotelManagement.dao.reservationDAO.CustomerDAO;
import lk.sliit.hotelManagement.dto.banquet.BanquetAddDTO;
import lk.sliit.hotelManagement.dto.banquet.BanquetBillDTO;
import lk.sliit.hotelManagement.dto.banquet.BanquetOrderDTO;
import lk.sliit.hotelManagement.dto.reservation.CustomerDTO;
import lk.sliit.hotelManagement.entity.banquet.BanquetBill;
import lk.sliit.hotelManagement.entity.banquet.BanquetOrder;
import lk.sliit.hotelManagement.entity.reservation.Customer;
import lk.sliit.hotelManagement.service.custom.BanquetBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class BanquetBOImpl implements BanquetBO {

    @Autowired
    BanquetOrderDAO banquetOrderDAO;
    @Autowired
    BanquetBillDAO banquetBillDAO;
    @Autowired
    MenuDAO menuDAO;
    @Autowired
    CustomerDAO customerDAO;

    @Override
    public void saveBanquet(BanquetAddDTO banquetAddDTO) {

        customerDAO.save(new Customer(
                banquetAddDTO.getCustomerId(),
                banquetAddDTO.getEmail(),
                banquetAddDTO.getName(),
                banquetAddDTO.getAddress(),
                banquetAddDTO.getContactNumber()
        ));

        //double zero=0.0;
        //banquetAddDTO.setTotal(zero);
        //banquetAddDTO.setFoodPrice(zero);
        //banquetAddDTO.setOtherPrice(zero);
        banquetBillDAO.save(new BanquetBill(
                banquetAddDTO.getBanquetBillId(),
                banquetAddDTO.getTotal(),
                banquetAddDTO.getFoodPrice(),
                banquetAddDTO.getOtherPrice(),
                banquetAddDTO.getAdvanceFee()
        ));
        String status ="pending";
        banquetAddDTO.setOrderState(status);

        banquetOrderDAO.save(new BanquetOrder(
                banquetAddDTO.getOrderId(),
                banquetAddDTO.getHallId(),
                banquetAddDTO.getOrderState(),
                banquetAddDTO.getNoOfPlates(),
                banquetAddDTO.getDate(),
                banquetAddDTO.getSubmittedBy(),
                customerDAO.findOne(banquetAddDTO.getCustomerId()),
                menuDAO.findOne(banquetAddDTO.getMenuId()),
                banquetBillDAO.findOne(banquetAddDTO.getBanquetBillId())
        ));
    }

    @Override
    public BanquetOrderDTO findTopBanquetId() {

        BanquetOrder banquetOrder = banquetOrderDAO.findTopByOrderByOrderIdDesc();
        return new BanquetOrderDTO(
                banquetOrder.getOrderId()
        );
    }

    @Override
    public CustomerDTO findTopCustomerId() {

        Customer customer = customerDAO.findTopByOrderByCustomerIdDesc();
        return new CustomerDTO(
                customer.getCustomerId()
        );
    }

    @Override
    public BanquetBillDTO findTopBanquetBillId() {
        BanquetBill banquetBill =banquetBillDAO.findTopByOrderByBillIdDesc();
        return new BanquetBillDTO(
                banquetBill.getBillId()
        );
    }

    @Override
    @Transactional(readOnly = false)
    public List<CustomerDTO> findAllCustomers() {
        Iterable<Customer> all = customerDAO.findAll();
        List<CustomerDTO> dtos = new ArrayList<>();
        for ( Customer a: all){
            dtos.add(new CustomerDTO(
                    a.getCustomerId(),
                    a.getEmail(),
                    a.getName(),
                    a.getAddress(),
                    a.getContactNumber()
            ));
        }
        return dtos;
    }


    @Override
    public List<BanquetAddDTO> findAllBanquet() {
        Iterable<BanquetOrder> all = banquetOrderDAO.findAll();
        List<BanquetAddDTO> dtos = new ArrayList<>();
        for ( BanquetOrder a: all){
            dtos.add(new BanquetAddDTO(
                    a.getOrderId(),
                    a.getCustomer().getName(),
                    a.getCustomer().getAddress(),
                    a.getDate(),
                    a.getHallId(),
                    a.getNoOfPlates(),
                    a.getMenu().getMenuId(),
                    a.getBanquetBill().getAdvancePayment(),
                    a.getBanquetBill().getBillId(),
                    a.getOrderState(),
                    a.getBanquetBill().getTotal()
            ));
        }
        return dtos;
    }

    @Override
    public List<BanquetAddDTO> findNextBanquets() {
        //Date todaydate = new Date();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.add(Calendar.DATE, 3);
        java.util.Date afterThreeDays = cal1.getTime();
        cal2.add(Calendar.DATE, 1);
        java.util.Date afterOneDays = cal2.getTime();
        Iterable <BanquetOrder> banquetOrders = banquetOrderDAO.findBanquetOrdersByDateBetween (afterOneDays ,afterThreeDays);
        List <BanquetAddDTO> dtos = new ArrayList<>();
        for ( BanquetOrder a: banquetOrders){
            dtos.add(new BanquetAddDTO(
                    a.getOrderId(),
                    a.getCustomer().getName(),
                    a.getCustomer().getContactNumber(),
                    a.getDate(),
                    a.getHallId(),
                    a.getNoOfPlates(),
                    a.getBanquetBill().getAdvancePayment(),
                    a.getOrderState()
            ));
        }
        return dtos;
    }

    @Override
    public void updateBanquetStatus(String orderId) {
        String status ="confirmed";
        banquetOrderDAO.updateBanStatus(status,orderId);

    }

    @Override
    public void updateBanquetStatusToCancel(String orderId) {
        String status ="canceled";
        banquetOrderDAO.updateBanStatus(status,orderId);
    }

    @Override
    public void deleteBanquet(String idNo) {
        banquetOrderDAO.delete(idNo);
    }

    @Override
    public List<BanquetAddDTO> findTodayBanquets() {
        Date todayDate = new Date();
        Iterable <BanquetOrder> banquetOrders = banquetOrderDAO.findBanquetOrdersByDate(todayDate);
        List <BanquetAddDTO> dtos2 = new ArrayList<>();
        for ( BanquetOrder a: banquetOrders){
            dtos2.add(new BanquetAddDTO(
                    a.getOrderId(),
                    a.getCustomer().getName(),
                    a.getCustomer().getContactNumber(),
                    a.getDate(),
                    a.getHallId(),
                    a.getNoOfPlates(),
                    a.getBanquetBill().getAdvancePayment(),
                    a.getOrderState()
            ));
        }
        return dtos2;
    }

    @Override
    public void updateBanquetDetails(BanquetAddDTO banquetAddDTO) {
        banquetBillDAO.updateBanquetBillTable(
                banquetAddDTO.getAdvanceFee(),
                banquetAddDTO.getBanquetBillId()
        );
        banquetOrderDAO.updateBanquetTable(
                banquetAddDTO.getHallId(),
                banquetAddDTO.getNoOfPlates(),
                banquetAddDTO.getDate(),
                menuDAO.findOne(banquetAddDTO.getMenuId()),
                banquetAddDTO.getOrderId()
        );

    }

    @Override
    public List<BanquetAddDTO> findUnconfirmedBanquet() {
        String status = "pending";
        Iterable<BanquetOrder> all = banquetOrderDAO.findAllByOrderStateEquals(status);
        List<BanquetAddDTO> dtos = new ArrayList<>();
        for ( BanquetOrder a: all){
            dtos.add(new BanquetAddDTO(
                    a.getOrderId(),
                    a.getCustomer().getName(),
                    a.getCustomer().getAddress(),
                    a.getDate(),
                    a.getHallId(),
                    a.getNoOfPlates(),
                    a.getMenu().getMenuId(),
                    a.getBanquetBill().getAdvancePayment(),
                    a.getBanquetBill().getBillId()
            ));
        }
        return dtos;
    }

    @Override
    public List<BanquetAddDTO> findConfirmedBanquet() {
        String statusConfirmed = "Confirmed";
        Iterable<BanquetOrder> all = banquetOrderDAO.findAllByOrderStateEquals(statusConfirmed);
        List<BanquetAddDTO> dtos = new ArrayList<>();
        for ( BanquetOrder a: all){
            dtos.add(new BanquetAddDTO(
                    a.getOrderId(),
                    a.getCustomer().getName(),
                    a.getCustomer().getAddress(),
                    a.getDate(),
                    a.getHallId(),
                    a.getNoOfPlates(),
                    a.getMenu().getMenuId(),
                    a.getBanquetBill().getAdvancePayment(),
                    a.getBanquetBill().getBillId()
            ));
        }
        return dtos;
    }

}
