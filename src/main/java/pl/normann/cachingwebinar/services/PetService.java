package pl.normann.cachingwebinar.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.normann.cachingwebinar.domain.Pet;
import pl.normann.cachingwebinar.repositories.PetRepository;

@Service
@Slf4j
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        log.info("PetService");
        this.petRepository = petRepository;
    }

    @CachePut(cacheNames = "pets", key ="#pet.name") // do aktualizacji
    public Pet createOrUpdate(final Pet pet) {
        log.info("createOrUpdate");
        return petRepository.save(pet);
    }

    @Cacheable(cacheNames = "pets")
    public Pet getByName(final String name) {
        log.info("getByName");
        return petRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("pet with name does not exist" + name));
    }
}
