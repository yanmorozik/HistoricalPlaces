package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dto.SettlementDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.service.SettlementService;
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
public class SettlementServiceImpl implements SettlementService {

    private final SettlementDao settlementDao;
    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Transactional
    @Override
    public SettlementDto save(SettlementDto roleDto) {
        Settlement settlement = modelMapper.map(roleDto, Settlement.class);
        Settlement response = settlementDao.save(settlement);
        return modelMapper.map(response, SettlementDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public SettlementDto findById(Long id){
        Settlement settlement = settlementDao.findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException(id);
                    log.error(notFoundException.getLocalizedMessage());
                    return notFoundException;
                });
        return modelMapper.map(settlement, SettlementDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SettlementDto> findAll(int page, int size, String name) {
        Pageable pages = PageRequest.of(page, size, Sort.by(name));
        Page<Settlement> settlements = settlementDao.findAll(pages);
        return (List<SettlementDto>) mapperUtil.map(settlements.getContent(), SettlementDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        settlementDao.deleteById(id);
    }
}
