package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.RoleDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dto.RoleDto;
import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Role;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.service.SettlementService;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementDao settlementDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public SettlementDto save(SettlementDto roleDto) {
        Settlement settlement = modelMapper.map(roleDto, Settlement.class);
        Settlement response = settlementDao.save(settlement);
        return modelMapper.map(response, SettlementDto.class);
    }

    @Override
    public SettlementDto findById(Long id) throws NotFoundException {
        Settlement settlement = settlementDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(settlement, SettlementDto.class);
    }

    @Override
    public List<SettlementDto> findAll() {
        List<Settlement> settlements = settlementDao.findAll();
        return (List<SettlementDto>) mapperUtil.map(settlements,SettlementDto.class);
    }

    @Override
    public void deleteById(Long id) {
        settlementDao.deleteById(id);
    }
}
