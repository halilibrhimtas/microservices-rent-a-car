package com.turkcell.rentalservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "car_id")
    private String carId;

    @Column(name = "started_date")
    private Date startedDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_rental_process_continue")
    private boolean isRentalProcessContinue;

    @Column(name = "customer_id")
    private UUID customerId;

}
