package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class DynamoDbTemplate<T> {

    private static final int PARTITION_KEY_INDEX_COLUMN = 0;
    private static final int SORT_KEY_INDEX_COLUMN = 0;
    private final String partitionKey;
    private final String sortKey;
    private final String[] tableColumns;

    protected DynamoDbTemplate(String... tableColumns) {
        this.partitionKey = tableColumns[PARTITION_KEY_INDEX_COLUMN];
        this.sortKey = tableColumns[SORT_KEY_INDEX_COLUMN];
        this.tableColumns = tableColumns;
    }

    public abstract String getTableName();
    protected abstract Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, T domain);

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(partitionKey, sortKey).build();
    }

    protected PutItemRequest putRequest(T domain) {
        Map<String, AttributeValue> domainPutItem = new HashMap<>();
        Map<String, AttributeValue> item = getDomainPutItems(domainPutItem, domain);

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(partitionKey, AttributeValue.builder().s(name).build());
        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(tableColumns)
                .build();
    }
}
