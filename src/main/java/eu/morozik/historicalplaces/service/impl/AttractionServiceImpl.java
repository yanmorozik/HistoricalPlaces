package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.api.AttractionDao;
import eu.morozik.historicalplaces.service.api.AttractionService;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionDao attractionDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public AttractionDto save(AttractionDto attractionDto) {
        Attraction attraction = modelMapper.map(attractionDto, Attraction.class);
        Attraction response = attractionDao.save(attraction);
        return modelMapper.map(response, AttractionDto.class);
    }

    @Override
    public AttractionDto findById(Long id) throws NotFoundException {
        Attraction attraction = attractionDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        return modelMapper.map(attraction, AttractionDto.class);
    }

    @Override
    public List<AttractionDto> findAll() {
        List<Attraction> attractions = attractionDao.findAll();
        return (List<AttractionDto>) mapperUtil.map(attractions,AttractionDto.class);
    }

    @Override
    public void deleteById(Long id) {
        attractionDao.deleteById(id);
    }
}
