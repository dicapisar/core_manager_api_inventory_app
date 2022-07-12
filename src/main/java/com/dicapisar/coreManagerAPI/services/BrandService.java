package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.RecordAlreadyExistedException;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.BrandRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
import com.dicapisar.coreManagerAPI.utils.BrandUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandService implements IBrandService{
    private BrandRepository brandRepository;
    private UserRepository userRepository;

    public BrandResponseDTO getBrandById(Long brandId) throws RecordNotFoundException {
        Brand brand = brandRepository.getBrandByIdAAndIsActive(brandId);

        if (brand == null) {
            throw new RecordNotFoundException("brand", brandId);
        }

        return BrandUtils.toBrandResponseDTO(brand);

    }

    public BrandResponseDTO createNewBrand(BrandCreateRequestDTO brandCreateRequestDTO, Long idUserCreator)
            throws RecordAlreadyExistedException {
        Brand branFounded = brandRepository.getBrandByName(brandCreateRequestDTO.getName());

        if (branFounded != null) {
            throw new RecordAlreadyExistedException("brand", "name");
        }

        User creator = userRepository.getUserById(idUserCreator);

        Brand newBrand = BrandUtils.toBrandModel(brandCreateRequestDTO.getName(), creator);

        Brand brandCreated = brandRepository.save(newBrand);

        return BrandUtils.toBrandResponseDTO(brandCreated);

    }
}
