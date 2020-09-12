package lk.sliit.hotelManagement.dao.banquetDAO;

import lk.sliit.hotelManagement.entity.banquet.BanquetBill;
import lk.sliit.hotelManagement.entity.reservation.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BanquetBillDAO extends CrudRepository<BanquetBill,Integer> {

    BanquetBill findTopByOrderByBillIdDesc();

    @Modifying
    @Query(value = "Update BanquetBill set advancePayment = ?1 where billId=?2")
    void updateBanquetBillTable(double advanceFee ,int banquetBillId);


}
