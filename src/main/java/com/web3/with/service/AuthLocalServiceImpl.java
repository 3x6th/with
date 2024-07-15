package com.web3.with.service;

import com.web3.with.mapper.UserMapper;
import com.web3.with.repository.RoleRepository;
import com.web3.with.security.model.AuthDto;
import com.web3.with.security.model.RegistrationDto;
import com.web3.with.security.model.auth.AuthProvider;
import com.web3.with.service.api.AuthLocalService;
import com.web3.with.service.api.UserService;
import com.web3.with.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthLocalServiceImpl implements AuthLocalService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void register(RegistrationDto registrationDto) throws BadRequestException {
        if (userService.existsByEmail(registrationDto.getEmail())) {
            throw new BadRequestException("User already exists");
        }
        var user = userMapper.registrationDtoToUser(registrationDto);
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        var role = roleRepository.findByRole(registrationDto.getRole());
        if (role == null) {
            throw new BadRequestException("Role not found");
        }
        user.setRole(role);
        log.info("Roles before saving: {}", user.getRole());

        userService.save(user);

        log.info("User saved with roles: {}", user.getRole());
    }

    @Override
    public String authenticate(AuthDto authDto) {
        var token = new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());
        var auth = authenticationManager.authenticate(token);
        var user = (UserDetails) auth.getPrincipal();
        return jwtUtil.generateJwtToken(user);
    }
}
