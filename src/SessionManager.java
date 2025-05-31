public class SessionManager {
    private static String loggedInEmail;
    private static String loggedInRole;

    public static void setLoggedInEmail(String email) {
        loggedInEmail = email;
    }

    public static String getLoggedInEmail() {
        return loggedInEmail;
    }

    public static void setLoggedInRole(String role) {
        loggedInRole = role;
    }

    public static String getLoggedInRole() {
        return loggedInRole;
    }
}
