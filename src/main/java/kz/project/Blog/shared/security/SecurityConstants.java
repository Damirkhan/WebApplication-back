package kz.project.Blog.shared.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String REGISTER = "/api/users/register";
    public static final String LOGIN_URL = "/login";
    public static final String SWAGGER_URL = "/swagger-ui.html";
    public static final String SOCKET_URL = "/socket/**";
}
