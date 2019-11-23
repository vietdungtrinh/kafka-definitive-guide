package com.diipgaid.constant;

public class AppConstant {
    public static String BOOTSTRAP_SERVERS = "kafka01:9092,kafka02:9092";
    /**
     * The acks parameter controls how many partition replicas must receive the record
     * before the producer can consider the write successful
     */
    public static String ACKS = "all";
    public static String TOPIC = "diipgaid-travel-topic";
    /**
     * When the producer receives an error message from the server, the error could be
     * transient (e.g., a lack of leader for a partition). In this case, the value of the retries
     * parameter will control how many times the producer will retry sending the message
     * before giving up and notifying the client of an issue. By default, the producer will wait
     * 100ms between retries, but you can control this using the retry.backoff.ms parameter
     */
    public static int RETRIES = 3;
    /**
     * This controls how many messages the producer will send to the server without
     * receiving responses
     */
    public static int MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = 1;
    /**
     * When multiple records are sent to the same partition, the producer will batch them
     * together. This parameter controls the amount of memory in bytes (not messages!)
     * that will be used for each batch. When the batch is full, all the messages in the batch
     * will be sent. However, this does not mean that the producer will wait for the batch to
     * become full. The producer will send half-full batches and even batches with just a single message in them
     */
    public static int BATCH_SIZE = 100;
    /**
     * Controls the amount of time to wait for additional messages before send‐
     * ing the current batch. KafkaProducer sends a batch of messages either when the cur‐
     * rent batch is full or when the linger.ms limit is reached. By default, the producer will
     * send messages as soon as there is a sender thread available to send them, even if
     * there’s just one message in the batch
     */
    public static int LINGER_MS = 0;

    /**
     * This setting controls the size of a produce request sent by the producer. It caps both
     * the size of the largest message that can be sent and the number of messages that the
     * producer can send in one request. For example, with a default maximum request size
     * of 1 MB, the largest message you can send is 1 MB or the producer can batch 1,000
     * messages of size 1 K each into one request. In addition, the broker has its own limit
     * on the size of the largest message it will accept ( message.max.bytes ). It is usually a
     * good idea to have these configurations match, so the producer will not attempt to
     * send messages of a size that will be rejected by the broker.
     */
    public static int MAX_REQUEST_SIZE = 1;
}
