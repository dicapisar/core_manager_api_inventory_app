package com.dicapisar.coreManagerAPI.utils;

import com.dicapisar.coreManagerAPI.exceptions.SessionErrorException;
import com.dicapisar.coreManagerAPI.exceptions.SessionWithOutPermissionException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.ROL_NAME;
import static com.dicapisar.coreManagerAPI.commons.CoreManagerConstants.ROL_ID;

public class SessionUtils {
    public static void validateSessionExist(HttpSession session) throws SessionErrorException {
        if (session.getAttribute(ROL_NAME) == null) {
            throw new SessionErrorException();
        }
    }

    public static void validateSessionHavePermissions(HttpSession session, ArrayList<String> permissionList)
            throws SessionWithOutPermissionException {
        if (!permissionList.contains(session.getAttribute(ROL_NAME))){
            throw new SessionWithOutPermissionException();
        }
    }

    public static Long getIdUserSession(HttpSession session) {
        return (Long) session.getAttribute(ROL_ID);
    }
}
