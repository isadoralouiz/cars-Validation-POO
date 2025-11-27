package br.edu.ifpr.cars.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TravelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Passenger passengerName;

    @ManyToOne
    Driver driver;

    String origin;
    String destination;

    @Enumerated(EnumType.STRING)
    TravelRequestStatus status = TravelRequestStatus.CREATED;

}
