package hr.tvz.konjetic.hardwareapp.security.service;

import hr.tvz.konjetic.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
