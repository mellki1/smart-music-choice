package br.com.valetech.smartmusicchoice.infrastructure.dynamodb.repository;

import br.com.valetech.smartmusicchoice.application.repointerface.InstrumentRepository;
import br.com.valetech.smartmusicchoice.domain.Instrument;
import br.com.valetech.smartmusicchoice.infrastructure.dynamodb.config.InstrumentDynamoConfiguration;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationScoped
public class InstrumentRepositoryImpl implements InstrumentRepository {

    private final InstrumentDynamoConfiguration instrumentDynamoConfiguration = new InstrumentDynamoConfiguration();

    @Inject
    DynamoDbAsyncClient dynamoDB;

    @Override
    public Uni<List<Instrument>> list() {
        return Uni.createFrom()
                .completionStage(() -> dynamoDB.scan(instrumentDynamoConfiguration.scanRequest()))
                .onItem()
                .transform(ScanResponse::items)
                .onItem()
                .transform(maps -> maps.stream().map(instrumentDynamoConfiguration::from))
                .onItem()
                .transform(instrumentStream -> instrumentStream.collect(Collectors.toList()));
    }
}
