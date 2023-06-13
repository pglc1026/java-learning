package com.example.es;


import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;

import java.util.List;
import java.util.Map;

/**
 * @author Will Liu
 * @since 2022/6/24
 */
public class MainTest {


    public static Map<String, Object> test(MultiBucketsAggregation.Bucket bucket, Map<String, Object> result) {
        if (bucket.getAggregations() == null) {
            result.put("key", bucket.getKeyAsString());
            result.put("doc_count", bucket.getDocCount());
            return result;
        }
        result.put("key", bucket.getKeyAsString());
        result.put("doc_count", bucket.getDocCount());
        for (Map.Entry<String, Aggregation> entry : bucket.getAggregations().asMap().entrySet()) {
            MultiBucketsAggregation multi = (MultiBucketsAggregation) entry;
            for (MultiBucketsAggregation.Bucket b : multi.getBuckets()) {
                test(b, result);
            }
        }

        return result;
    }

    private static class Result {

        private String key1;

        private Object value1;

        private String key2;

        private Object value2;

        private String key3;

        private Object value3;

        public String getKey1() {
            return key1;
        }

        public void setKey1(String key1) {
            this.key1 = key1;
        }

        public Object getValue1() {
            return value1;
        }

        public void setValue1(Object value1) {
            this.value1 = value1;
        }

        public String getKey2() {
            return key2;
        }

        public void setKey2(String key2) {
            this.key2 = key2;
        }

        public Object getValue2() {
            return value2;
        }

        public void setValue2(Object value2) {
            this.value2 = value2;
        }

        public String getKey3() {
            return key3;
        }

        public void setKey3(String key3) {
            this.key3 = key3;
        }

        public Object getValue3() {
            return value3;
        }

        public void setValue3(Object value3) {
            this.value3 = value3;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "key1='" + key1 + '\'' +
                    ", value1=" + value1 +
                    ", key2='" + key2 + '\'' +
                    ", value2=" + value2 +
                    ", key3='" + key3 + '\'' +
                    ", value3=" + value3 +
                    '}';
        }
    }
}
