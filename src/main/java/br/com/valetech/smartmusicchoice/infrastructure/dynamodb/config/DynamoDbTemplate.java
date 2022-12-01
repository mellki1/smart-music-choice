package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class DynamoDbTemplate<T> {
    private final String[] tableColumns;

    protected DynamoDbTemplate(String... tableColumns) {
        this.tableColumns = tableColumns;
    }

    public abstract String getTableName();

    public abstract T getDomainObject(Map<String, AttributeValue> item);

    public ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(tableColumns).build();
    }

    public abstract Map<String, AttributeValue> getDomainPutItems(Map<String, AttributeValue> domainPutItem, T domain);

    public PutItemRequest putRequest(T domain) {
        Map<String, AttributeValue> domainPutItem = new HashMap<>();
        Map<String, AttributeValue> item = getDomainPutItems(domainPutItem, domain);

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    public GetItemRequest getRequest(T domain) {
        Map<String, AttributeValue> key = new HashMap<>();
        key = getDomainPutItems(key, domain);
        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(tableColumns)
                .build();
    }

    public T from(Map<String, AttributeValue> item) {
        T domain = null;
        if (item != null && !item.isEmpty()) {
            domain = getDomainObject(item);
        }
        return domain == null ? (T) new Object() : domain;
    }
}
