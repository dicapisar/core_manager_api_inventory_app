package com.dicapisar.coreManagerAPI.controllers;

import com.dicapisar.coreManagerAPI.dtos.request.ProviderCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ProviderUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ProviderResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.services.IProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.*;
import static com.dicapisar.coreManagerAPI.utils.SessionUtils.*;

@RestController
@AllArgsConstructor
@RequestMapping("/providers")
public class ProviderController {

    private IProviderService providerService;

    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderResponseDTO> getProviderById(@PathVariable Long providerId, HttpSession session)
            throws RecordNotFoundException, SessionErrorException, SessionWithOutPermissionException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(providerService.getProviderById(providerId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProviderResponseDTO> createNewProvider(@Validated @RequestBody ProviderCreateRequestDTO providerCreateRequestDTO,
                                                           HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyExistedException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(providerService.createNewProvider(providerCreateRequestDTO, getIdUserSession(session)), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<List<ProviderResponseDTO>> getListProviders(@RequestParam(required = false, defaultValue = "true") String typeStatus,
                                                                HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, TypeStatusErrorException, ListRecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(providerService.getListProviders(typeStatus), HttpStatus.OK);
    }

    @PostMapping("/{providerId}")
    public ResponseEntity<ProviderResponseDTO> updateProviderById(@PathVariable Long providerId,
                                                            @Validated @RequestBody ProviderUpdateRequestDTO providerUpdateRequestDTO,
                                                            HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordNotFoundException, RecordNotActiveException,
            RecordWithSameDataException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(providerService.updateProviderById(providerUpdateRequestDTO, providerId, getIdUserSession(session)), HttpStatus.OK);
    }

    @PutMapping("/activate/{providerId}")
    public ResponseEntity<?> activateBrandById(@PathVariable Long providerId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        providerService.changeStatusToTypeProviderById(providerId, getIdUserSession(session), true);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/deactivate/{providerId}")
    public ResponseEntity<?> deactivateBrandById(@PathVariable Long providerId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        providerService.changeStatusToTypeProviderById(providerId, getIdUserSession(session), false);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
