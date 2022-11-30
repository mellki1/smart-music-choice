package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.Vocalist;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class VocalistDynamoConfiguration extends DynamoDbTemplate<Vocalist> {

    private static final String VOCALIST_TABLE_NAME = "vocalist";
    private static final String[] VOCALIST_COLUMNS = {"id", "name"};

    @Override
    public String getTableName() {
        return VOCALIST_TABLE_NAME;
    }

    @Override
    protected Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, Vocalist vocalist) {
        domainPutItem.put(VOCALIST_COLUMNS[0], AttributeValue.builder().s(vocalist.getId()).build());
        domainPutItem.put(VOCALIST_COLUMNS[1], AttributeValue.builder().s(vocalist.getName()).build());
        return domainPutItem;
    }
}
