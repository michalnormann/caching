package pl.normann.cachingwebinar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.normann.cachingwebinar.domain.Pet;
import pl.normann.cachingwebinar.services.PetService;

@RestController
@RequestMapping("api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{name}")
    public Pet get(@PathVariable final String name) {
        return petService.getByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createOrUpdate(@RequestBody final Pet name) {
        return petService.createOrUpdate(name);
    }
}
