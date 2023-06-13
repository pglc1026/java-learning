package com.example.prometheus.client;

import io.prometheus.client.Counter;

/**
 * @author Will Liu
 * @since 2022/6/20
 */
public class ClientTest {

    public void testClient() {
        Counter counter = Counter.build()
                .name("blog_visit") //这里模拟博客访问量
                .labelNames("blog_id") //博客id
                .help("counter_blog_visit") //这个名字随便起
                .register();

        counter.labels("test").inc(2);
    }
}
