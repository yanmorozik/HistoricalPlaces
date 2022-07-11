package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.CredentialDao;
import eu.morozik.historicalplaces.dao.RoleDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.AuthenticationDto;
import eu.morozik.historicalplaces.dto.AuthenticationDtoWithToken;
import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.dto.SearchWithThreeFiltersDto;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Credential;
import eu.morozik.historicalplaces.model.Role;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.model.enums.Entity;
import eu.morozik.historicalplaces.model.enums.Status;
import eu.morozik.historicalplaces.security.JwtTokenProvider;
import eu.morozik.historicalplaces.service.UserService;
import eu.morozik.historicalplaces.specification.Filter;
import eu.morozik.historicalplaces.specification.SpecificationService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final CredentialDao credentialDao;
    private final RoleDao roleDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final SpecificationService<User> creator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto save(UserWithRelationIdsDto userWithRelationIdsDto) {
        User response = userDao.save(reassignment(userWithRelationIdsDto));
        return modelMapper.map(response, UserDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userDao.findAll(pageable)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll(SearchWithThreeFiltersDto searchDto) {
        List<Filter> filters = creator.checkFilters(searchDto);
        if (filters.size() > 0) {
            return (List<UserDto>) mapperUtil.map(userDao.findAll(creator.getSpecificationFromFilters(filters)), UserDto.class);
        } else {
            return (List<UserDto>) mapperUtil.map(userDao.findAll(), UserDto.class);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findUserByName(String name) {
        List<User> users = userDao.findUserByName(name);
        return (List<UserDto>) mapperUtil.map(users, UserDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findBySurname(String surname) {
        List<User> users = userDao.findBySurname(surname);
        return (List<UserDto>) mapperUtil.map(users, UserDto.class);
    }

    @Transactional
    @Override
    public AuthenticationDtoWithToken authenticate(AuthenticationDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
        User user = userDao.findByCredentialLogin(dto.getLogin()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        String token = jwtTokenProvider.creatToken(dto.getLogin(), user.getRoles());
        return AuthenticationDtoWithToken.builder().login(dto.getLogin()).token(token).build();
    }

    @Transactional
    @Override
    public UserDto registration(UserDto userDto) {

        Role roleUser = roleDao.findByNameRole("ROLE_USER");

        Credential credential = modelMapper.map(userDto.getCredential(), Credential.class);
        credential.setPassword(passwordEncoder.encode(userDto.getCredential().getPassword()));

        credentialDao.save(credential);

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        final User user = modelMapper.map(userDto, User.class);
        user.setRoles(roles);
        user.setCredential(credential);
        user.setStatus(Status.ACTIVE);
        userDao.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public GeneralObjectDto searchAsGlobal(String name) {
        List<User> users = userDao.findUserByName(name);

        for (User user : users) {
            if (user.getFirstName().equals(name))
                return GeneralObjectDto.builder().name(user.getFirstName()).type(Entity.USER).build();
        }
        return null;
    }

    public User reassignment(UserWithRelationIdsDto userWithRelationIdsDto) {
        final User user = modelMapper.map(userWithRelationIdsDto, User.class);

        Status[] statuses = Status.values();
        user.setStatus(statuses[Math.toIntExact(userWithRelationIdsDto.getStatusId())]);

        Credential credential = credentialDao.findById(userWithRelationIdsDto.getCredentialId())
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(userWithRelationIdsDto
                            .getCredentialId());
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        user.setCredential(credential);

        Set<Role> roles = userWithRelationIdsDto.getRoleIds()
                .stream()
                .map(id -> roleDao.findById(id)
                        .orElseThrow(() -> {
                            NotFoundException notFoundException = new NotFoundException(id);
                            log.error(notFoundException.getLocalizedMessage());
                            return notFoundException;
                        }))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return user;
    }
}
