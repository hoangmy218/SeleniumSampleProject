package objects;

public class ApplicationProperties {
    public final String url;
    public final String email;
    public final String password;

    public ApplicationProperties(String url, String email, String password) {
        this.url = url;
        this.email = email;
        this.password = password;
    }
}
