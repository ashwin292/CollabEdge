package org.projects.collabedge.userservice.utils;

import static org.mindrot.jbcrypt.BCrypt.*;

public class BCrypt {

    public static String hashPassword(String password) {
        return hashpw(password, gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return checkpw(password, hashedPassword);
    }
}
