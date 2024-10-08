package ru.itmo.soa.mainservice.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soa.mainservice.model.Band;
import ru.itmo.soa.mainservice.model.MusicGenre;
import ru.itmo.soa.mainservice.model.Single;
import ru.itmo.soa.mainservice.model.dto.BandUpdate;
import ru.itmo.soa.mainservice.services.BandService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @PostMapping
    public ResponseEntity<Band> createBand(@Valid @RequestBody Band band) {
        Band createdBand = bandService.createBand(band);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBand);
    }

    @GetMapping
    public ResponseEntity<List<Band>> getBands(
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false) String[] filter,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

            List<Band> bands = bandService.getBands(sort, filter, page, size);
            return ResponseEntity.ok(bands);
    }

    @GetMapping("/{id}")
    public Band getBandById(@PathVariable Long id) {
        return bandService.getBandById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Band> updateBand(@PathVariable Long id, @RequestBody BandUpdate bandUpdate) {
        Band updatedBand = bandService.updateBand(bandUpdate, id);
        return ResponseEntity.ok(updatedBand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBandById(@PathVariable Long id) {
        bandService.deleteBandById(id);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<MusicGenre>> getAllGenres() {
        return ResponseEntity.ok(bandService.getAllGenres());
    }

    @DeleteMapping("/genre/{genre}")
    public ResponseEntity<Void> deleteBandsByGenre(@PathVariable("genre") String genre) {
        bandService.deleteBandsByGenre(genre);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genre/min")
    public ResponseEntity<Band> getGroupWithMinGenre() {
        Band band = bandService.getGroupWithMinGenre();
        return ResponseEntity.ok(band);
    }

//    Запросы со второго сервера
    @PostMapping("{id}/singles")
    public ResponseEntity<Band> addSingleToBand(@PathVariable Long id, @RequestBody Single single) {
        Band updatedBand = bandService.addSingleToBand(id, single);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBand);
    }

    @PutMapping("bands/{bandId}/singles/{singleId}")
    public ResponseEntity<Single> changeSingle(@PathVariable Long bandId, @PathVariable Long singleId, @RequestBody Single single) {
        Single updatedSingle = bandService.changeSingle(bandId, singleId, single);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedSingle);
    }
}
