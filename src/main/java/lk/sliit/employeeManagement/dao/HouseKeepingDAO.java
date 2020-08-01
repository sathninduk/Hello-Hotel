package lk.sliit.employeeManagement.dao;

import lk.sliit.employeeManagement.entity.houseKeeping.HotelRoom;
import org.springframework.data.repository.CrudRepository;

public interface HouseKeepingDAO extends CrudRepository<HotelRoom,String> {
    HotelRoom findTopByOrderByRoomIdDesc();
}
