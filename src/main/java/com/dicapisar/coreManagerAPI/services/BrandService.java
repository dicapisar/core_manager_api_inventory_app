package com.dicapisar.coreManagerAPI.services;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.ListRecordNotFoundException;
import com.dicapisar.coreManagerAPI.exceptions.RecordAlreadyExistedException;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;
import com.dicapisar.coreManagerAPI.exceptions.TypeStatusErrorException;
import com.dicapisar.coreManagerAPI.models.Brand;
import com.dicapisar.coreManagerAPI.models.User;
import com.dicapisar.coreManagerAPI.repository.BrandRepository;
import com.dicapisar.coreManagerAPI.repository.UserRepository;
import com.dicapisar.coreManagerAPI.utils.BrandUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.STATUS_BOTH;
import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.STATUS_TRUE;
import static com.dicapisar.coreManagerAPI.utils.ValidationUtils.validateTypeStatus;

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

    public List<BrandResponseDTO> getListBrands(String typeStatus)
            throws TypeStatusErrorException, ListRecordNotFoundException {
        validateTypeStatus(typeStatus);

        List<Brand> brandList;
        if (typeStatus.equals(STATUS_BOTH)) {
            brandList = this.getBrandList();
        } else {
            brandList = this.getBrandListByTypeStatus(typeStatus);
        }

        List<BrandResponseDTO> brandResponseDTOS = new ArrayList<>();

        for (Brand brand :
                brandList) {
            brandResponseDTOS.add(BrandUtils.toBrandResponseDTO(brand));
        }

        return brandResponseDTOS;

    }

    private List<Brand> getBrandList() throws ListRecordNotFoundException {
       List<Brand> brandList = brandRepository.getBrandList();

       if (brandList.isEmpty()) {
           throw new ListRecordNotFoundException("brand");
       }

       return brandList;
    }

    private List<Brand> getBrandListByTypeStatus(String typeStatus) throws ListRecordNotFoundException {
        List<Brand> brandList = brandRepository.getBrandListByTypeStatus(typeStatus.equals(STATUS_TRUE) ? Boolean.TRUE : Boolean.FALSE);

        if (brandList.isEmpty()) {
            throw new ListRecordNotFoundException("brand");
        }

        return brandList;
    }


}
