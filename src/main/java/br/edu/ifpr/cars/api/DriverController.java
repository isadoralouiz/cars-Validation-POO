package br.edu.ifpr.cars.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.domain.DriverRepository;
import jakarta.validation.Valid;

@Service
@RestController
@RequestMapping(value = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    // 🔹 LISTAR TODOS
    @GetMapping
    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    // 🔹 BUSCAR POR ID
    @GetMapping("/{id}")
    public Driver findDriver(@PathVariable("id") Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    // 🔹 CRIAR NOVO
    @PostMapping
    public Driver createDriver(@Valid @RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    // 🔹 ATUALIZAR COMPLETAMENTE (PUT)
    @PutMapping("/{id}")
    public Driver fullUpdateDriver(@PathVariable("id") Long id, @Valid @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);

        foundDriver.setName(driver.getName());
        foundDriver.setEmail(driver.getEmail());
        foundDriver.setCpf(driver.getCpf());
        foundDriver.setBirthDate(driver.getBirthDate());

        return driverRepository.save(foundDriver);
    }

    // 🔹 ATUALIZAÇÃO PARCIAL (PATCH)
    @PatchMapping("/{id}")
    public Driver incrementalUpdateDriver(@PathVariable("id") Long id, @Valid @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);

        foundDriver.setName(Optional.ofNullable(driver.getName()).orElse(foundDriver.getName()));
        foundDriver.setEmail(Optional.ofNullable(driver.getEmail()).orElse(foundDriver.getEmail()));
        foundDriver.setCpf(Optional.ofNullable(driver.getCpf()).orElse(foundDriver.getCpf()));
        foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate()).orElse(foundDriver.getBirthDate()));

        return driverRepository.save(foundDriver);
    }

    // 🔹 DELETAR
    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        if (!driverRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado.");
        }
        driverRepository.deleteById(id);
    }
}
