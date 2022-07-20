package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.BrandUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;

import java.util.List;

public interface IBrandService {
    BrandResponseDTO getBrandById(Long brandId) throws RecordNotFoundException;
    BrandResponseDTO createNewBrand(BrandCreateRequestDTO brandCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException;
    List<BrandResponseDTO> getListBrands(String typeStatus) throws TypeStatusErrorException, ListRecordNotFoundException;
    BrandResponseDTO updateBrandById(BrandUpdateRequestDTO brandUpdateRequestDTO, Long brandId, Long updaterId)
            throws RecordNotFoundException, RecordNotActiveException, RecordWithSameDataException;
    void changeStatusToBrandById(Long brandId, Long updaterId, boolean newStatus)
            throws RecordNotFoundException, RecordAlreadyHasStateException;
}
