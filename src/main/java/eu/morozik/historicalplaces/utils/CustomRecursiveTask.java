package eu.morozik.historicalplaces.utils;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.model.Attraction;
import eu.morozik.historicalplaces.model.Country;
import eu.morozik.historicalplaces.model.Settlement;
import eu.morozik.historicalplaces.model.User;
import eu.morozik.historicalplaces.model.enums.Entity;
import eu.morozik.historicalplaces.service.impl.GlobalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.RecursiveTask;

@Component
@RequiredArgsConstructor
public class CustomRecursiveTask extends RecursiveTask<GeneralObjectDto> {

    private  final AttractionDao attractionDao;
    private final CountryDao countryDao;
    private final SettlementDao settlementDao;
    private final UserDao userDao;

    @Override
    protected GeneralObjectDto compute() {
        GeneralObjectDto dto = null;
        
        List<Attraction> attractions ;
        List<Country> countries ;
        List<Settlement> settlements;
        List<User> users;
        
        String name = null;
        attractions =  attractionDao.findAll();

        for (Attraction attraction : attractions) {
            if (attraction.getName().equals(name)) {
                dto = GeneralObjectDto.builder().name(name).type(Entity.ATTRACTION).build();
                break;
            }
        }

        countries = countryDao.findAll();
        for (Country country : countries) {
            if (country.getName().equals(name)) {
                dto = GeneralObjectDto.builder().name(name).type(Entity.COUNTRY).build();
                break;
            }
        }

        settlements = settlementDao.findAll();
        for (Settlement settlement : settlements) {
            if (settlement.getName().equals(name)) {
                dto = GeneralObjectDto.builder().name(name).type(Entity.SETTLEMENT).build();
                break;
            }
        }

        users= userDao.findAll();
        for (User user : users) {
            if (user.getFirstName().equals(name)) {
                dto = GeneralObjectDto.builder().name(name).type(Entity.USER).build();
                break;
            }
        }
        return dto;
    }
}
