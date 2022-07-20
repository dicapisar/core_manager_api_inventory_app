package com.dicapisar.coreManagerAPI.controllers;

import com.dicapisar.coreManagerAPI.dtos.request.BrandCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.BrandUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.TypeItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.TypeItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.BrandResponseDTO;
import com.dicapisar.coreManagerAPI.dtos.response.TypeItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.services.ITypeItemService;
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
@RequestMapping("/type_item")
public class TypeItemController {

    private ITypeItemService typeItemService;
    @GetMapping("/{typeItemId}")
    public ResponseEntity<?> getTypeItemById(@PathVariable Long typeItemId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(typeItemService.getTypeItemById(typeItemId), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<TypeItemResponseDTO> createNewTypeItem(@Validated @RequestBody TypeItemCreateRequestDTO typeItemCreateRequestDTO,
                                                                 HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyExistedException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(typeItemService.createNewTypeItem(typeItemCreateRequestDTO, getIdUserSession(session)), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<List<TypeItemResponseDTO>> getListTypeItem(@RequestParam(required = false, defaultValue = "true") String typeStatus,
                                                                HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, TypeStatusErrorException, ListRecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(typeItemService.getListTypeItemss(typeStatus), HttpStatus.OK);

    }

    @PostMapping("/{typeItemId}")
    public ResponseEntity<TypeItemResponseDTO> updateTypeItemById(@PathVariable Long typeItemId,
                                                            @Validated @RequestBody TypeItemUpdateRequestDTO typeItemUpdateRequestDTO,
                                                            HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordNotFoundException, RecordNotActiveException,
            RecordWithSameDataException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(typeItemService.updateTypeItemById(typeItemUpdateRequestDTO, typeItemId, getIdUserSession(session)), HttpStatus.OK);

    }

    @PutMapping("/activate/{typeItemId}")
    public ResponseEntity<?> activateTypeItemById(@PathVariable Long typeItemId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        typeItemService.changeStatusToTypeItemById(typeItemId, getIdUserSession(session), true);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/deactivate/{typeItemId}")
    public ResponseEntity<?> deactivateTypeItemById(@PathVariable Long typeItemId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        typeItemService.changeStatusToTypeItemById(typeItemId, getIdUserSession(session), false);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
