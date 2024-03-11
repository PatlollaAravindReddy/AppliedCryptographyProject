package com.example.demo.controller;


import com.example.demo.dto.Patient;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.encryption;
import com.example.demo.service.patientDetails;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EncryptionDecryptionController {

    @Autowired
    encryption encryption;

    @Autowired
    patientDetails patientDetails;

    @RequestMapping(method = RequestMethod.POST, path = "/storeDetails")
    public ResponseEntity storePatientsData(@RequestBody Patient patient) throws Exception {
        try {
            Patient data = encryption.aesEncryption(patient);
            patientDetails.storingPatientDetails(data);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getDetails")
    public ResponseEntity getPatientDetails(@RequestParam String userId) throws Exception {
        try {
            List<Patient> key = patientDetails.fetchPatientDetails(userId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
