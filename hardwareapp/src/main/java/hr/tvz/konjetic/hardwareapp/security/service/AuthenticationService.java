package hr.tvz.konjetic.hardwareapp.security.service;

import hr.tvz.konjetic.hardwareapp.security.command.LoginCommand;
import hr.tvz.konjetic.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
