package com.turkcell.rentalservice.repository;

import com.turkcell.rentalservice.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {

    @Query("SELECT r FROM Rental r WHERE r.isRentalProcessContinue = true AND r.carId = :carId")
    List<Rental> findContinueRentalsByCarId(@Param("carId") String carId);
}
