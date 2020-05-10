package com.dingwd.netty.study.grpc;

import com.dingwd.netty.study.grpc.proto.HelloReply;
import com.dingwd.netty.study.grpc.proto.HelloRequest;
import com.dingwd.netty.study.grpc.proto.HelloReply;
import com.dingwd.netty.study.grpc.proto.HelloRequest;
import com.dingwd.netty.study.grpc.proto.StreamingGreeterGrpc;
import com.dingwd.netty.study.grpc.proto.StreamingGreeterGrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/7 0007 17:18
 */
public class ManualFlowControlClient {
    private static final Logger logger =
            LoggerFactory.getLogger(ManualFlowControlClient.class);

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);

        // Create a channel and a stub
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        StreamingGreeterGrpc.StreamingGreeterStub  stub = StreamingGreeterGrpc.newStub(channel);

        /*
        在数据流从上游生产者向下游消费者传输的过程中，上游生产速度大于下游消费速度，导致下游的 Buffer 溢出，这种现象就叫做 Backpressure 出现
        Backpressure 和 Buffer 是一对相生共存的概念，只有设置了 Buffer，才有 Backpressure 出现；只要设置了 Buffer，一定存在出现 Backpressure 的风险。
        */
        // When using manual flow-control and back-pressure on the client, the ClientResponseObserver handles both
        // request and response streams.
        ClientResponseObserver<HelloRequest, HelloReply> clientResponseObserver =
                new ClientResponseObserver<HelloRequest, HelloReply>() {

                    ClientCallStreamObserver<HelloRequest> requestStream;

                    @Override
                    public void beforeStart(final ClientCallStreamObserver<HelloRequest> requestStream) {
                        this.requestStream = requestStream;
                        // Set up manual flow control for the response stream. It feels backwards to configure the response
                        // stream's flow control using the request stream's observer, but this is the way it is.
                        requestStream.disableAutoInboundFlowControl();

                        // Set up a back-pressure-aware producer for the request stream. The onReadyHandler will be invoked
                        // when the consuming side has enough buffer space to receive more messages.
                        //
                        // Messages are serialized into a transport-specific transmit buffer. Depending on the size of this buffer,
                        // MANY messages may be buffered, however, they haven't yet been sent to the server. The server must call
                        // request() to pull a buffered message from the client.
                        //
                        // Note: the onReadyHandler's invocation is serialized on the same thread pool as the incoming
                        // StreamObserver's onNext(), onError(), and onComplete() handlers. Blocking the onReadyHandler will prevent
                        // additional messages from being processed by the incoming StreamObserver. The onReadyHandler must return
                        // in a timely manner or else message processing throughput will suffer.
                        requestStream.setOnReadyHandler(new Runnable() {
                            // An iterator is used so we can pause and resume iteration of the request data.
                            Iterator<String> iterator = names().iterator();

                            @Override
                            public void run() {
                                // Start generating values from where we left off on a non-gRPC thread.
                                while (requestStream.isReady()) {
                                    if (iterator.hasNext()) {
                                        // Send more messages if there are more messages to send.
                                        String name = iterator.next();
                                        logger.info("--> " + name);
                                        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
                                        requestStream.onNext(request);
                                    } else {
                                        // Signal completion if there is nothing left to send.
                                        requestStream.onCompleted();
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onNext(HelloReply value) {
                        logger.info("<-- " + value.getMessage());
                        // Signal the sender to send one message.
                        requestStream.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        done.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        logger.info("All Done");
                        done.countDown();
                    }
                };

        // Note: clientResponseObserver is handling both request and response stream processing.
        stub.sayHelloStreaming(clientResponseObserver);

        done.await();

        channel.shutdown();
        channel.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static List<String> names() {
        return Arrays.asList(
                "Sophia",
                "Jackson",
                "Emma",
                "Aiden",
                "Olivia",
                "Lucas",
                "Ava",
                "Liam",
                "Mia",
                "Noah",
                "Isabella",
                "Ethan",
                "Riley",
                "Mason",
                "Aria",
                "Caden",
                "Zoe",
                "Oliver",
                "Charlotte",
                "Elijah",
                "Lily",
                "Grayson",
                "Layla",
                "Jacob",
                "Amelia",
                "Michael",
                "Emily",
                "Benjamin",
                "Madelyn",
                "Carter",
                "Aubrey",
                "James",
                "Adalyn",
                "Jayden",
                "Madison",
                "Logan",
                "Chloe",
                "Alexander",
                "Harper",
                "Caleb",
                "Abigail",
                "Ryan",
                "Aaliyah",
                "Luke",
                "Avery",
                "Daniel",
                "Evelyn",
                "Jack",
                "Kaylee",
                "William",
                "Ella",
                "Owen",
                "Ellie",
                "Gabriel",
                "Scarlett",
                "Matthew",
                "Arianna",
                "Connor",
                "Hailey",
                "Jayce",
                "Nora",
                "Isaac",
                "Addison",
                "Sebastian",
                "Brooklyn",
                "Henry",
                "Hannah",
                "Muhammad",
                "Mila",
                "Cameron",
                "Leah",
                "Wyatt",
                "Elizabeth",
                "Dylan",
                "Sarah",
                "Nathan",
                "Eliana",
                "Nicholas",
                "Mackenzie",
                "Julian",
                "Peyton",
                "Eli",
                "Maria",
                "Levi",
                "Grace",
                "Isaiah",
                "Adeline",
                "Landon",
                "Elena",
                "David",
                "Anna",
                "Christian",
                "Victoria",
                "Andrew",
                "Camilla",
                "Brayden",
                "Lillian",
                "John",
                "Natalie",
                "Lincoln"
        );
    }
}