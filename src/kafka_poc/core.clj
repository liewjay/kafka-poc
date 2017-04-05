(ns kafka-poc.core)

(defn consume-kafka
  [group]
  (let [props (doto (java.util.Properties.)
                (.put "bootstrap.servers" "localhost:9092")
                (.put "group.id" group)
                (.put "key.deserializer" (->
                                           org.apache.kafka.common.serialization.StringDeserializer
                                           .getName))
                (.put "value.deserializer" (->
                                             org.apache.kafka.common.serialization.StringDeserializer
                                             .getName)))
        consumer (org.apache.kafka.clients.consumer.KafkaConsumer. props)
        _ (.subscribe consumer ["newt"])
        records (->
                  (.poll consumer 1000)
                  .iterator
                  iterator-seq)
        _ (.close consumer)]
    (map (fn [record]
           {:offset (.offset record)
            :value (.value record)}) records)))

(consume-kafka "jaytest")
