package com.quantridulieu.hotelManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query(value = "SELECT * FROM room WHERE room_id = :roomID", nativeQuery = true)
    Room findByRoomID(@Param("roomID") String roomID);

    @Query("SELECT r FROM Room r WHERE r.roomId IN :roomIDs")
    List<Room> findByRoomIDs(@Param("roomIDs") List<String> roomIDs);

    @Query(value = "SELECT * FROM room WHERE room_number = :roomNumber", nativeQuery = true)
    Room findByRoomNumber(@Param("roomNumber") int roomNumber);

    @Query(value = "SELECT * FROM room WHERE status = :status", nativeQuery = true)
    List<Room> findByStatus(@Param("status") String status);

    @Query(value = "SELECT * FROM room WHERE category_id = :categoryId", nativeQuery = true)
    List<Room> findByCategory(@Param("categoryId") String categoryId);

    @Procedure(name = "SearchRoom") // ✅ Đúng với tên trong MySQL
    List<Room> searchRoom(
            @Param("roomId") String roomId,
            @Param("roomNumber") Integer roomNumber,
            @Param("status") String status,
            @Param("categoryId") String categoryId,
            @Param("categoryName") String categoryName);

    @Query(value = "SELECT r.* FROM room r " +
            "WHERE r.status LIKE '%Occupied%'", nativeQuery = true)
    List<Room> findOccupiedRooms();

    @Modifying
    @Transactional
    @Query(value = "UPDATE room SET status = :status WHERE room_id = :room_id", nativeQuery = true)
    int updateStatusByRoomId(@Param("room_id") String room_id, @Param("status") String status);

}
