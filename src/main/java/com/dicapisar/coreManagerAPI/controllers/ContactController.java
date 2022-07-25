package com.dicapisar.coreManagerAPI.controllers;

import com.dicapisar.coreManagerAPI.dtos.request.ContactCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ContactUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ProviderCreateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.request.ProviderUpdateRequestDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ContactResponseDTO;
import com.dicapisar.coreManagerAPI.dtos.response.ProviderResponseDTO;
import com.dicapisar.coreManagerAPI.exceptions.*;
import com.dicapisar.coreManagerAPI.services.IContactService;
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
@RequestMapping("/")
public class ContactController {

    private IContactService contactService;

    @GetMapping("/providers/{providerId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDTO> getContactByProviderIdAndContactId(@PathVariable Long providerId,
                                                                                 @PathVariable Long contactId,
                                                                                 HttpSession session)
            throws RecordNotFoundException, SessionErrorException, SessionWithOutPermissionException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(contactService.getContactByIdAndProviderId(contactId, providerId), HttpStatus.OK);
    }

    @PostMapping("/providers/{providerId}/create_new_contact")
    public ResponseEntity<ContactResponseDTO> createNewProvider(@Validated @RequestBody ContactCreateRequestDTO contactCreateRequestDTO,
                                                                @PathVariable Long providerId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyExistedException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(contactService.createNewContactByProviderId(contactCreateRequestDTO, providerId, getIdUserSession(session)), HttpStatus.CREATED);

    }

    @GetMapping("/providers/{providerId}/list_contacts")
    public ResponseEntity<List<ContactResponseDTO>> getListContactsByProviderId(@RequestParam(required = false, defaultValue = "true") String typeStatus,
                                                                                @PathVariable Long providerId,
                                                                                HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, TypeStatusErrorException,
            ListRecordNotFoundException, RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(contactService.getListContactByProviderId(providerId,typeStatus), HttpStatus.OK);
    }

    @PostMapping("/providers/{providerId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDTO> updateContactByIdAndProviderId(@PathVariable Long providerId,
                                                                             @PathVariable Long contactId,
                                                            @Validated @RequestBody ContactUpdateRequestDTO contactUpdateRequestDTO,
                                                            HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordNotFoundException, RecordNotActiveException,
            RecordWithSameDataException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        return new ResponseEntity<>(contactService.updateContactByIdAndProviderId(contactUpdateRequestDTO, contactId, providerId, getIdUserSession(session)), HttpStatus.OK);
    }

    @PutMapping("/contacts/activate/{contactId}")
    public ResponseEntity<?> activateBrandById(@PathVariable Long contactId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        contactService.changeStatusToContactById(contactId, getIdUserSession(session), true);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/contacts/deactivate/{contactId}")
    public ResponseEntity<?> deactivateBrandById(@PathVariable Long contactId, HttpSession session)
            throws SessionErrorException, SessionWithOutPermissionException, RecordAlreadyHasStateException,
            RecordNotFoundException {

        ArrayList<String> rolesPermissions = new ArrayList<>(List.of(ADMIN, MANAGER, EMPLOYED));

        validateSessionExist(session);
        validateSessionHavePermissions(session, rolesPermissions);

        contactService.changeStatusToContactById(contactId, getIdUserSession(session), false);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
