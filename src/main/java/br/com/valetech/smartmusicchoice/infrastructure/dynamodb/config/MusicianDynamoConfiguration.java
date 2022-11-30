package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.Musician;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class MusicianDynamoConfiguration extends DynamoDbTemplate<Musician> {
    private static final String MUSICIAN_TABLE_NAME = "musician";
    private static final String[] MUSICIAN_COLUMNS = {"id", "name"};

    @Override
    public String getTableName() {
        return MUSICIAN_TABLE_NAME;
    }
    @Override
    protected Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, Musician musician) {
        domainPutItem.put(MUSICIAN_COLUMNS[0], AttributeValue.builder().s(musician.getId()).build());
        domainPutItem.put(MUSICIAN_COLUMNS[1], AttributeValue.builder().s(musician.getName()).build());
        return domainPutItem;
    }
}
