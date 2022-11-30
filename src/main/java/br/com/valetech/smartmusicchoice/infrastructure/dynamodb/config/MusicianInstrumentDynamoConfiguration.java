package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.MusicianInstrument;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class MusicianInstrumentDynamoConfiguration extends DynamoDbTemplate<MusicianInstrument> {
    private static final String MUSICIAN_INSTRUMENT_TABLE_NAME = "musician";
    private static final String[] MUSICIAN_INSTRUMENT_COLUMNS = {"instrumentId", "musicianId"};


    @Override
    public String getTableName() {
        return MUSICIAN_INSTRUMENT_TABLE_NAME;
    }

    @Override
    protected Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, MusicianInstrument musicianInstrument) {
        domainPutItem.put(MUSICIAN_INSTRUMENT_COLUMNS[0], AttributeValue.builder().s(musicianInstrument.getInstrumentId()).build());
        domainPutItem.put(MUSICIAN_INSTRUMENT_COLUMNS[1], AttributeValue.builder().s(musicianInstrument.getMusicianId()).build());
        return domainPutItem;
    }
}
