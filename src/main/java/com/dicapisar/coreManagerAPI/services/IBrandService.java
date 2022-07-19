package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.ListRecordNotFoundException;
import com.dicapisar.coreManagerAPI.exceptions.RecordAlreadyExistedException;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;
import com.dicapisar.coreManagerAPI.exceptions.TypeStatusErrorException;

import java.util.List;

public interface IBrandService {
    BrandResponseDTO getBrandById(Long brandId) throws RecordNotFoundException;
    BrandResponseDTO createNewBrand(BrandCreateRequestDTO brandCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException;
    List<BrandResponseDTO> getListBrands(String typeStatus) throws TypeStatusErrorException, ListRecordNotFoundException;
}
