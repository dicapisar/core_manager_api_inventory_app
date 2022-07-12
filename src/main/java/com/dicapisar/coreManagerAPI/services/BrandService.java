package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.repository.BrandRepository;
import com.dicapisar.coreManagerAPI.utils.BrandUtils;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandService implements IBrandService{
    private BrandRepository brandRepository;

    public BrandResponseDTO getBrandById(Long brandId) throws RecordNotFoundException {
        Brand brand = brandRepository.getBrandByIdAAndIsActive(brandId);

        if (brand == null) {
            throw new RecordNotFoundException("brand", brandId);
        }

        return BrandUtils.toBrandResponseDTO(brand);

    }
}
