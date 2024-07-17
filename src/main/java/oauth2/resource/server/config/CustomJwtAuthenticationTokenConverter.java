package oauth2.resource.server.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;


// In Jwt we added `authorities` field, which lists all the authority names
// that user has. This class is created to load Authentication object's authorities
// field with authority names that we got from Jwt token

// P.S. JwtAuthenticationToken is held by Authentication object
public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {
    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        List<String> authorities = source.getClaim("authorities");
        JwtAuthenticationToken authenticationObj = new JwtAuthenticationToken(
                source,
                authorities.stream().map(SimpleGrantedAuthority::new).toList()
        );
        return authenticationObj;
    }
}
