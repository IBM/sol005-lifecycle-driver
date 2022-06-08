# Configuring Kafka

The NFVO driver expects to find a Kafka endpoint with a pre-existing topic configured or auto-creation of topics enabled. It may be necessary to override the default host and manually create the expected Kafka topic.

**Configuring Kafka host**

The Kafka host by default is set to `iaf-system-kafka-bootstrap:9092`. This value can be configured via the Helm values file by setting the following property during the Helm install. 

###### Example of values passed to Helm chart during install
```yaml
app:
  config:
    env:
      spring_kafka_bootstrap_servers: iaf-system-kafka-bootstrap:9092
```
Note :-

kafka host value must be set as follows, in values.yaml file of the helm package, depending on the CP4NA versions:


    For CP4NA v2.3+, it must be cp4na-o-events-kafka-bootstrap can be

    For CP4NA v2.3/v2.3+, it must be cp4na-o-events-kafka-bootstrap

**Creating Kafka topic**

The NFVO driver expects to find a Kafka topic called `lcm_op_occ_polling_requests`. This should be manually created with replication-factor and partitions set as per your scaling requirements. 
