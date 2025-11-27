package br.edu.ifpr.cars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.TravelRequest;
import br.edu.ifpr.cars.domain.TravelRequestStatus;
import br.edu.ifpr.cars.repository.TravelRequestRepository;

@Service
public class TravelService {

    @Autowired
    private TravelRequestRepository travelRepository;

    public List<TravelRequest> listTravels() {
        return travelRepository.findAll();
    }

    public TravelRequest findById(Long id) {
        return travelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TravelRequest createTravel(TravelRequest travel) {
        travel.setId(null);
        travel.setStatus(TravelRequestStatus.CREATED);
        return travelRepository.save(travel);
    }

    public TravelRequest fullUpdateTravel(Long id, TravelRequest travel) {
        TravelRequest founded = findById(id);

        founded.setPassengerName(travel.getPassengerName());
        founded.setOrigin(travel.getOrigin());
        founded.setDestination(travel.getDestination());

        return travelRepository.save(founded);
    }

    public TravelRequest incrementalUpdateTravel(Long id, TravelRequest travel) {
        TravelRequest founded = findById(id);

        founded.setPassengerName(
            Optional.ofNullable(travel.getPassengerName())
                    .orElse(founded.getPassengerName())
        );

        founded.setOrigin(
            Optional.ofNullable(travel.getOrigin())
                    .orElse(founded.getOrigin())
        );

        founded.setDestination(
            Optional.ofNullable(travel.getDestination())
                    .orElse(founded.getDestination())
        );

        return travelRepository.save(founded);
    }

    public TravelRequest acceptTravel(Long id) {
        TravelRequest travel = findById(id);

        if (travel.getStatus() == TravelRequestStatus.ACCEPTED ||
            travel.getStatus() == TravelRequestStatus.FINISHED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (travel.getStatus() != TravelRequestStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        travel.setStatus(TravelRequestStatus.ACCEPTED);
        return travelRepository.save(travel);
    }

    public void deleteTravel(Long id) {
        travelRepository.deleteById(id);
    }
}