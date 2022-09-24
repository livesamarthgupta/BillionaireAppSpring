package com.rest.controller;

import com.rest.exception.BillionaireNotFoundException;
import com.rest.model.Billionaire;
import com.rest.service.BillionaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillionaireController {
    private static final Logger logger = LoggerFactory.getLogger(BillionaireController.class);

    @Autowired
    BillionaireService billionaireService;

    @GetMapping(path = "/billionaires", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Billionaire> getAllBillionaires() {
        logger.info("Requested for all billionaires.");
        return billionaireService.listAll();
    }

    @GetMapping(path = "/billionaire/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Billionaire getBillionaireById(@PathVariable("id") Long billionaireId) throws BillionaireNotFoundException {
        logger.info("Requested to fetch billionaire with id: {}", billionaireId);
        return billionaireService.getBillionaire(billionaireId);
    }

    @PostMapping(path = "billionaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveBillionaire(@RequestBody Billionaire billionaire) {
        logger.info("Requested to save billionaire: {}", billionaire);
        billionaireService.createBillionaire(billionaire);
        return ResponseEntity.ok().body("Billionaire Created Successfully.");
    }

    @PutMapping(path = "/billionaire/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Billionaire> updateBillionaire(@PathVariable("id") Long billionaireId, @RequestBody Billionaire billionaire) throws BillionaireNotFoundException {
        logger.info("Request to update billionaire with id: {}", billionaireId);
        billionaire.setId(billionaireId);
        Billionaire updatedBillionaire = billionaireService.updateBillionaire(billionaire);

        return ResponseEntity.ok().body(updatedBillionaire);
    }

    @DeleteMapping(path = "/billionaire/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBillionaire(@PathVariable Long id) {
        logger.info("Requested to delete billionaire with id: {}", id);
        billionaireService.deleteBillionaire(id);

        return ResponseEntity.ok().body("Billionaire Deleted Successfully.");
    }

}
