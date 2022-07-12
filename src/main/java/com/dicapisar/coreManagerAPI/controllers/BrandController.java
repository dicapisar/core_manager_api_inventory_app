package com.dicapisar.coreManagerAPI.controllers;

import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;
import com.dicapisar.coreManagerAPI.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private IBrandService brandService;

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Long brandId)
            throws RecordNotFoundException {
        return new ResponseEntity<>(brandService.getBrandById(brandId), HttpStatus.OK);
    }
}
