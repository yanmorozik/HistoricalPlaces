package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AddressDao;
import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.ReviewDao;
import eu.morozik.historicalplaces.dao.projection.view.AttractionView;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionDto;
import eu.morozik.historicalplaces.dto.attractiondto.AttractionWithRelationIdsDto;
import eu.morozik.historicalplaces.exception.NotFoundException;
import eu.morozik.historicalplaces.model.Address;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Review;
import eu.morozik.historicalplaces.service.AttractionService;
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
public class AttractionServiceImpl implements AttractionService {

    private final AttractionDao attractionDao;
    private final AddressDao addressDao;
    private final ReviewDao reviewDao;
    private final ModelMapper modelMapper;
    private final MapperUtil mapperUtil;

    @Override
    public AttractionDto save(AttractionWithRelationIdsDto attractionWithRelationIdsDto) {
        Attraction response = attractionDao.save(reassignment(attractionWithRelationIdsDto));
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
        return (List<AttractionDto>) mapperUtil.map(attractions, AttractionDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        attractionDao.deleteSimilarPlaces(id);
        attractionDao.deleteById(id);
    }

    @Override
    public AttractionView findByName(String name) {
        return attractionDao.findByName(name);
    }

    public Attraction reassignment(AttractionWithRelationIdsDto attractionWithRelationIdsDto) {
        final Attraction attraction = modelMapper.map(attractionWithRelationIdsDto, Attraction.class);

        Address address = addressDao.findById(attractionWithRelationIdsDto.getAddress())
                .orElseThrow(() -> new NotFoundException(attractionWithRelationIdsDto.getAddress()));
        attraction.setAddress(address);

        Set<Review> reviews = attractionWithRelationIdsDto.getReviewIds()
                .stream()
                .map(id -> reviewDao.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .collect(Collectors.toSet());
        attraction.setReviews(reviews);

        Set<Attraction> attractions = attractionWithRelationIdsDto.getAttractionIds()
                .stream()
                .map(id -> attractionDao.findById(id).orElseThrow(() -> new NotFoundException(id)))
                .collect(Collectors.toSet());
        attraction.setSimilarAttractions(attractions);

        return attraction;
    }
}
