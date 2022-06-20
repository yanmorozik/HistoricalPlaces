package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.RoleDao;
import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Role;
import eu.morozik.historicalplaces.service.RoleService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Transactional
    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role response = roleDao.save(role);
        return modelMapper.map(response, RoleDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public RoleDto findById(Long id){
        Role role = roleDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(role, RoleDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Role> roles = roleDao.findAll(pages);
        return (List<RoleDto>) mapperUtil.map(roles.getContent(), RoleDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}
