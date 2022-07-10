package eu.morozik.historicalplaces.service.impl;

import eu.morozik.historicalplaces.dao.AttractionDao;
import eu.morozik.historicalplaces.dao.CountryDao;
import eu.morozik.historicalplaces.dao.SettlementDao;
import eu.morozik.historicalplaces.dao.UserDao;
import eu.morozik.historicalplaces.dto.GeneralObjectDto;
import eu.morozik.historicalplaces.service.GlobalService;
import eu.morozik.historicalplaces.utils.CustomRecursiveTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
@Slf4j
public class GlobalServiceImpl implements GlobalService {

    private  final AttractionDao attractionDao;
    private final  CountryDao countryDao;
    private final  SettlementDao settlementDao;
    private final  UserDao userDao;

    public static String nameEntity ;

    @Override
    public GeneralObjectDto findCountEntityByName(String name) {

        nameEntity = name;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CustomRecursiveTask customRecursiveTask = new CustomRecursiveTask(attractionDao,countryDao,settlementDao,userDao);
        return forkJoinPool.invoke(customRecursiveTask);
    }

}
