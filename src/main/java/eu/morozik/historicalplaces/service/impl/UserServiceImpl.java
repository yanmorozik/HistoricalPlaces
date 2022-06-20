package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.CredentialDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.RoleDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Credential;
import eu.morozik.historicalplaces.model.Role;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.model.enums.Status;
import eu.morozik.historicalplaces.service.UserService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<User> users = userDao.findAll(pages);
        return (List<UserDto>) mapperUtil.map(users.getContent(), UserDto.class);
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

    public User reassignment(UserWithRelationIdsDto userWithRelationIdsDto) {
        final User user = modelMapper.map(userWithRelationIdsDto, User.class);

        user.setStatus(Status.getStatus(userWithRelationIdsDto.getStatusId()));

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
