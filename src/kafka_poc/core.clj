(ns kafka-poc.core
  (:use [clj-kafka.producer :as producer]
        [clj-kafka.zk :as zk]))

(defn do-something
  "I don't do a whole lot."
  []
  (let [brokers (zk/brokers {"zookeeper.connect" "172.27.122.91:2181"})]
    (println "Number of brokers" (count brokers))
    (println "First host" (:host (first brokers)))))

(do-something)