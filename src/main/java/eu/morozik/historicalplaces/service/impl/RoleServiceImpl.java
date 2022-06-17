package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.RoleDao;
import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.dto.reviewdto.ReviewDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Review;
import eu.morozik.historicalplaces.model.Role;
import eu.morozik.historicalplaces.service.RoleService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role response = roleDao.save(role);
        return modelMapper.map(response, RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) throws NotFoundException {
        Role role = roleDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleDao.findAll();
        return (List<RoleDto>) mapperUtil.map(roles,RoleDto.class);
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}
