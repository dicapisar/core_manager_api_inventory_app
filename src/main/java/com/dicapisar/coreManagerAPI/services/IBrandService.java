package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;

public interface IBrandService {
    BrandResponseDTO getBrandById(Long brandId) throws RecordNotFoundException;
}
