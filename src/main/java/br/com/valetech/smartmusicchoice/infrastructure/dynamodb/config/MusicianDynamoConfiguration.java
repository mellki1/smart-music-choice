package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.Musician;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.UUID;

public class MusicianDynamoConfiguration extends DynamoDbTemplate<Musician> {
    private static final String MUSICIAN_TABLE_NAME = "musician";
    private static final String[] MUSICIAN_COLUMNS = {"id", "name"};

    @Override
    public String getTableName() {
        return MUSICIAN_TABLE_NAME;
    }

    @Override
    public Musician getDomainObject(Map<String, AttributeValue> item) {
        Musician musician = new Musician();
        musician.setId(UUID.fromString(item.get(MUSICIAN_COLUMNS[0]).s()));
        musician.setName(item.get(MUSICIAN_COLUMNS[1]).s());
        return musician;
    }
    @Override
    public Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, Musician musician) {
        domainPutItem.put(MUSICIAN_COLUMNS[0], AttributeValue.builder().s(musician.getId().toString()).build());
        domainPutItem.put(MUSICIAN_COLUMNS[1], AttributeValue.builder().s(musician.getName()).build());
        return domainPutItem;
    }
}
