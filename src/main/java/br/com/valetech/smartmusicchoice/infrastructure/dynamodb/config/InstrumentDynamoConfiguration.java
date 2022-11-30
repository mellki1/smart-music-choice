package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.Instrument;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class InstrumentDynamoConfiguration extends DynamoDbTemplate<Instrument> {

    private static final String INSTRUMENT_TABLE_NAME = "instrument";
    private static final String[] INSTRUMENT_COLUMNS = {"id", "name"};
    @Override
    public String getTableName() {
        return INSTRUMENT_TABLE_NAME;
    }

    @Override
    protected Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, Instrument instrument) {
        domainPutItem.put(INSTRUMENT_COLUMNS[0], AttributeValue.builder().s(instrument.getId()).build());
        domainPutItem.put(INSTRUMENT_COLUMNS[1], AttributeValue.builder().s(instrument.getName()).build());
        return domainPutItem;
    }
}
