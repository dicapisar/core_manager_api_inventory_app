package com.dicapisar.coreManagerAPI.controllers;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.RecordAlreadyExistedException;
import com.dicapisar.coreManagerAPI.exceptions.RecordNotFoundException;
import com.dicapisar.coreManagerAPI.exceptions.SessionErrorException;
import com.dicapisar.coreManagerAPI.exceptions.SessionWithOutPermissionException;
import com.dicapisar.coreManagerAPI.services.IBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.*;
import static com.dicapisar.coreManagerAPI.utils.SessionUtils.*;

@RestController
@AllArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private IBrandService brandService;

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Long brandId, HttpSession session)
            throws RecordNotFoundException, SessionErrorException, SessionWithOutPermissionException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(brandService.getBrandById(brandId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BrandResponseDTO> createNewBrand(@RequestBody BrandCreateRequestDTO brandCreateRequestDTO,
                                                           HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyExistedException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(brandService.createNewBrand(brandCreateRequestDTO, getIdUserSession(session)), HttpStatus.CREATED);

    }
}
