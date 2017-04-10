(ns kafka-poc.core
  (:use [clj-kafka.producer :as producer]
        [clj-kafka.zk :as zk]))

(defn do-something
  "I don't do a whole lot."
  []
  (let [brokers (zk/brokers {"zookeeper.connect" "localhost:2181"})]
    (println brokers)
    (println "Number of brokers" (count brokers))
    (println "First host" (:host (first brokers)))))

t
(do-something)
