package tcc.poc.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("topicSupplierProducer")
public class TopicSupplierProducer extends TopicWarehouse{

    @Value("${topic.supplier.register.producer}")
    private String topicName;

    public TopicSupplierProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public String getTopicName() {
        return topicName;
    }

}