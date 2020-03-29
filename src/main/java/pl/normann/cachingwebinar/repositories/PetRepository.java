package pl.normann.cachingwebinar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.normann.cachingwebinar.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, String> {
}
