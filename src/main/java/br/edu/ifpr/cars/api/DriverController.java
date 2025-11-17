package br.edu.ifpr.cars.api;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.services.DriverService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/drivers")
    public List<Driver> listDrivers() {
        return service.listDrivers();
    }

    @GetMapping("/drivers/{id}")
    public Driver findDriver(@PathVariable("id") Long id) {
        return service.findDriver(id);
    }

    @PostMapping("/drivers")
    public Driver createDriver(@RequestBody @Valid Driver driver) {
        return service.createDriver(driver);
    }

    @PutMapping("/drivers/{id}")
    public Driver fullUpdateDriver(@PathVariable("id") Long id,
                                   @RequestBody Driver driver) {
        return service.fullUpdateDriver(id, driver);
    }

    @PatchMapping("/drivers/{id}")
    public Driver incrementalUpdateDriver(@PathVariable("id") Long id,
                                          @RequestBody Driver driver) {
        return service.incrementalUpdateDriver(id, driver);
    }

    @DeleteMapping("/drivers/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        service.deleteDriver(id);
    }
}