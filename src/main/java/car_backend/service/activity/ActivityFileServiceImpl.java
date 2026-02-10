package fr.bpce.car.service.activity;

import fr.bpce.car.repository.ActivityFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActivityFileServiceImpl implements ActivityFileService {

    @Autowired
    private ActivityFileRepository repository;



}
