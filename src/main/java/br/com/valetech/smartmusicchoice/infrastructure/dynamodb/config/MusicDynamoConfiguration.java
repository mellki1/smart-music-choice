package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import br.com.valetech.smartmusicchoice.domain.Music;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class MusicDynamoConfiguration extends DynamoDbTemplate<Music> {

    private static final String MUSIC_TABLE_NAME = "music";
    private static final String[] MUSIC_COLUMNS = {"id", "name", "singer", "lastDatePlayed"};
    @Override
    public String getTableName() {
        return MUSIC_TABLE_NAME;
    }

    @Override
    protected Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, Music music) {
        domainPutItem.put(MUSIC_COLUMNS[0], AttributeValue.builder().s(music.getId()).build());
        domainPutItem.put(MUSIC_COLUMNS[1], AttributeValue.builder().s(music.getName()).build());
        domainPutItem.put(MUSIC_COLUMNS[2], AttributeValue.builder().s(music.getSinger()).build());
        domainPutItem.put(MUSIC_COLUMNS[3], AttributeValue.builder().s(music.getLastDatePlayed()).build());
        return domainPutItem;
    }
}
