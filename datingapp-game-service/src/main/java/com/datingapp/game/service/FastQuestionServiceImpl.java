package com.datingapp.game.service;

import com.datingapp.game.converter.FastQuestionConverter;
import com.datingapp.game.data.dto.FastQuestionDto;
import com.datingapp.game.data.entity.FastQuestionEntity;
import com.datingapp.game.repository.FastQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created on 27.03.2020
 *
 * @author onurcanyozgat
 */

@Service
public class FastQuestionServiceImpl implements FastQuestionService {

    @Autowired
    private FastQuestionRepository repository;

    @Autowired
    private FastQuestionConverter converter;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public FastQuestionDto save(FastQuestionDto dto) {
        FastQuestionEntity entity = repository.save(converter.toEntity(dto));
        return converter.toDto(entity);
    }

    @Override
    public List<FastQuestionDto> saveAll(List<FastQuestionDto> dtoList) {
        List<FastQuestionEntity> entityList = dtoList.stream().map(dto -> converter.toEntity(dto)).collect(Collectors.toList());
        List<FastQuestionEntity> fastQuestionEntities = repository.saveAll(entityList);
        return fastQuestionEntities.stream().map(entity -> converter.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public FastQuestionDto findById(Long id) {
        Optional<FastQuestionEntity> optional = repository.findById(id);
        return optional.map(questionEntity -> converter.toDto(questionEntity)).orElse(null);
    }

    @Override
    public List<FastQuestionDto> getRandomQuestions(int number) {
        SampleOperation matchStage = Aggregation.sample(5);
        Aggregation aggregation = Aggregation.newAggregation(matchStage);
        AggregationResults<FastQuestionEntity> output = mongoTemplate.aggregate(aggregation, "FastQuestion", FastQuestionEntity.class);
        List<FastQuestionEntity> mappedResults = output.getMappedResults();
        return mappedResults.stream().map(entity -> converter.toDto(entity)).collect(Collectors.toList());
    }
}