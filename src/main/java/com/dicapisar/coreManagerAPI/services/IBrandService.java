package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.RecordAlreadyExistedException;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;

public interface IBrandService {
    BrandResponseDTO getBrandById(Long brandId) throws RecordNotFoundException;
    BrandResponseDTO createNewBrand(BrandCreateRequestDTO brandCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException;
}
