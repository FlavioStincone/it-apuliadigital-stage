package it.exprivia.rider_dispatch.controller;

import it.exprivia.rider_dispatch.model.dto.RiderDTO;
import it.exprivia.rider_dispatch.service.IRiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/riders")
@RequiredArgsConstructor
@Slf4j
public class RiderController {

    private final IRiderService service;

    @PostMapping
    public ResponseEntity<RiderDTO> updateRider(@RequestBody RiderDTO riderDTO) {
        RiderDTO savedRider = service.updateRider(riderDTO);
        return new ResponseEntity<>(savedRider, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RiderDTO>> getAllRiders() {
        return ResponseEntity.ok(service.getAllRiders());
    }

    @GetMapping("/{fiscalCode}")
    public ResponseEntity<RiderDTO> getRiderByFiscalCode(@PathVariable String fiscalCode) {
        return ResponseEntity.ok(service.getRiderByFiscalCode(fiscalCode));
    }

    @DeleteMapping("/remove/{fiscalCode}")
    public ResponseEntity<Boolean> removeRider(@PathVariable String fiscalCode) {
        return ResponseEntity.ok(service.removeRider(fiscalCode));
    }
    
}