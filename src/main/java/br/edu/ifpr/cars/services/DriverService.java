package br.edu.ifpr.cars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    /*public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }*/

    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    public Driver findDriver(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver fullUpdateDriver(Long id, Driver driver) {
        Driver foundDriver = findDriver(id);

        foundDriver.setName(driver.getName());
        foundDriver.setBirthDate(driver.getBirthDate());
        foundDriver.setNumero(driver.getNumero());

        return driverRepository.save(foundDriver);
    }

    public Driver incrementalUpdateDriver(Long id, Driver driver) {
        Driver foundDriver = findDriver(id);

        foundDriver.setName(Optional.ofNullable(driver.getName())
                .orElse(foundDriver.getName()));

        foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate())
                .orElse(foundDriver.getBirthDate()));

        foundDriver.setNumero(Optional.ofNullable(driver.getNumero())
                .orElse(foundDriver.getNumero()));

        return driverRepository.save(foundDriver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}