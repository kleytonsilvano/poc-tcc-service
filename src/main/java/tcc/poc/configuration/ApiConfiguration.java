package tcc.poc.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import tcc.poc.kafka.*;

@Configuration
public class ApiConfiguration {
/*
    @Bean("topicCustomerProducer")
    public TopicWarehouse getTopicCustomerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new TopicCustomerProducer(kafkaTemplate);
    }

    @Bean("topicMerchandiseProducer")
    public TopicWarehouse getTopicMerchandiseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new TopicMerchandiseProducer(kafkaTemplate);
    }

    @Bean("topicSupplierProducer")
    public TopicWarehouse getTopicSupplierProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new TopicSupplierProducer(kafkaTemplate);
    }

    @Bean("topicWarehouseProducer")
    public TopicWarehouse getTopicWarehouseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new TopicWarehouseProducer(kafkaTemplate);
    }

 */
}
