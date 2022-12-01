package br.com.valetech.smartmusicchoice.setup;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

public class DynamoSetup implements QuarkusTestResourceLifecycleManager {


    @Container
    LocalStackContainer localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"))
            .withServices(LocalStackContainer.Service.DYNAMODB)
            .withExposedPorts(4566,4566);

    @Override
    public Map<String, String> start() {
        try {
            localstack.start();
            await().atMost(5, TimeUnit.SECONDS);
            localstack.execInContainer("sh", "-c", "awslocal dynamodb create-table --endpoint-url=http://localhost:4566\\\n" +
                    "    --table-name instrument \\\n" +
                    "    --attribute-definitions \\\n" +
                    "        AttributeName=id,AttributeType=S \\\n" +
                    "        AttributeName=name,AttributeType=S \\\n" +
                    "    --key-schema \\\n" +
                    "        AttributeName=id,KeyType=HASH \\\n" +
                    "        AttributeName=name,KeyType=RANGE \\\n" +
                    "--provisioned-throughput \\\n" +
                    "        ReadCapacityUnits=10,WriteCapacityUnits=5").getExitCode();

            localstack.execInContainer("sh", "-c", "awslocal dynamodb put-item --endpoint-url=http://localhost:4566\\\n" +
                    "    --table-name instrument \\\n" +
                    "    --item '{\"id\" : {\"S\": \"79d444a6-9676-4bd8-bd80-b5dd83575bf2\"},\"name\": {\"S\": \"sax\"}}'");
            var map = ImmutableMap.of(
                    "quarkus.dynamodb.endpoint-override", String.format("http://%s:%s", localstack.getHost(), localstack.getFirstMappedPort()),
                    "quarkus.dynamodb.aws.region", localstack.getRegion(),
                    "quarkus.dynamodb.aws.credentials.type", "static",
                    "quarkus.dynamodb.aws.credentials.static-provider.access-key-id", localstack.getAccessKey(),
                    "quarkus.dynamodb.aws.credentials.static-provider.secret-access-key", localstack.getAccessKey());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public void stop() {
        // close container
    }
}