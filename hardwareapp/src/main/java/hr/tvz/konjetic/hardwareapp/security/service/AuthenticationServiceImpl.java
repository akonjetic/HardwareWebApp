package hr.tvz.konjetic.hardwareapp.security.service;

import hr.tvz.konjetic.hardwareapp.security.command.LoginCommand;
import hr.tvz.konjetic.hardwareapp.security.domain.User;
import hr.tvz.konjetic.hardwareapp.security.dto.LoginDTO;
import hr.tvz.konjetic.hardwareapp.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final hr.tvz.konjetic.hardwareapp.security.service.JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(hr.tvz.konjetic.hardwareapp.security.service.JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        // TODO - implementirati provjeru odgovara li lozinka, koju je unio korisnik, enkriptiranoj lozinki u bazi
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if(b.matches(rawPassword, encryptedPassword)){
            return true;
        }else {

            throw new UnsupportedOperationException();
        }
    }
}
