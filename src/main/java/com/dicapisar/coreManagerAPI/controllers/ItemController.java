package com.dicapisar.coreManagerAPI.controllers;

import com.dicapisar.coreManagerAPI.dtos.request.ItemCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ItemUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ItemResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.services.IItemService;
import lombok.AllArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
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
@RequestMapping("/items")
public class ItemController {

    private IItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long itemId, HttpSession session)
            throws RecordNotFoundException, SessionErrorException, SessionWithOutPermissionException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(itemService.getItemById(itemId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ItemResponseDTO> createNewItem(@Validated @RequestBody ItemCreateRequestDTO itemCreateRequestDTO,
                                                           HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyExistedException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(itemService.createNewItem(itemCreateRequestDTO, getIdUserSession(session)), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<List<ItemResponseDTO>> getListItems(@RequestParam(required = false, defaultValue = "true") String typeStatus,
                                                                HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, TypeStatusErrorException, ListRecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(itemService.getListItems(typeStatus), HttpStatus.OK);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> updateItemById(@PathVariable Long itemId,
                                                            @Validated @RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO,
                                                            HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordNotFoundException, RecordNotActiveException,
            RecordWithSameDataException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(itemService.updateItemById(itemUpdateRequestDTO, itemId, getIdUserSession(session)), HttpStatus.OK);
    }

    @PutMapping("/activate/{itemId}")
    public ResponseEntity<?> activateItemById(@PathVariable Long itemId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        itemService.changeStatusToItemById(itemId, getIdUserSession(session), true);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/deactivate/{itemId}")
    public ResponseEntity<?> deactivateItemById(@PathVariable Long itemId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        itemService.changeStatusToItemById(itemId, getIdUserSession(session), false);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
