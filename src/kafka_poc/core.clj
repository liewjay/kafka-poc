(ns kafka-poc.core
  (:require [clojure.string :as str]))

(defn consume-kafka
  [group topic]
  (let [deserializer (->
                       org.apache.kafka.common.serialization.StringDeserializer
                       .getName)
        brokers ["aalx-ctec-a03p.enwd.co.sa.charterlab.com:9092"
                 "aalx-ctec-a03p.enwd.co.sa.charterlab.com:9093"
                 "aalx-ctec-a03p.enwd.co.sa.charterlab.com:9094"
                 "aalx-ctec-a03p.enwd.co.sa.charterlab.com:9095"]
        props (doto (java.util.Properties.)
                (.put "bootstrap.servers" (str/join "," brokers))
                (.put "group.id" group)
                (.put "key.deserializer" deserializer)
                (.put "value.deserializer" deserializer))
        consumer (org.apache.kafka.clients.consumer.KafkaConsumer. props)
        _ (.subscribe consumer [topic])
        records (->
                  (.poll consumer 1000)
                  .iterator
                  iterator-seq)
        _ (println records (count records))
        _ (.close consumer)]
    (map (fn [record]
           {:offset (.offset record)
            :value (.value record)}) records)))

(consume-kafka "jaytest" "newt")
