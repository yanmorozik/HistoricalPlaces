package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.CredentialDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.RoleDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.userdto.UserDto;
import eu.morozik.historicalplaces.dto.userdto.UserWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Credential;
import eu.morozik.historicalplaces.model.Role;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.model.enums.Status;
import eu.morozik.historicalplaces.service.UserService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final CredentialDao credentialDao;
    private final ReviewDao reviewDao;
    private final RoleDao roleDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public UserDto save(UserWithRelationIdsDto userWithRelationIdsDto) {
        User response = userDao.save(reassignment(userWithRelationIdsDto));
        return modelMapper.map(response, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) throws NotFoundException {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userDao.findAll();
        return (List<UserDto>) mapperUtil.map(users, UserDto.class);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public List<UserDto> findUserByName(String name) {
        List<User> users = userDao.findUserByName(name);
        return (List<UserDto>) mapperUtil.map(users, UserDto.class);
    }

    @Transactional
    @Override
    public List<UserDto> findBySurname(String surname) {
        List<User> users = userDao.findBySurname(surname);
        return (List<UserDto>) mapperUtil.map(users, UserDto.class);
    }

    public User reassignment(UserWithRelationIdsDto userWithRelationIdsDto) {
        final User user = modelMapper.map(userWithRelationIdsDto, User.class);

        user.setStatus(Status.getStatus(userWithRelationIdsDto.getStatusId()));

        Credential credential = credentialDao.findById(userWithRelationIdsDto.getCredentialId())
                .orElseThrow(() -> new NotFoundException(userWithRelationIdsDto.getCredentialId()));
        user.setCredential(credential);

        Set<Role> roles = userWithRelationIdsDto.getRoleIds()
                .stream()
                .map(id -> roleDao.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return user;
    }
}
