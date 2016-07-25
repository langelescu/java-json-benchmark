package com.github.fabienrenaud.jjb.databind;

import com.alibaba.fastjson.JSON;
import com.bluelinelabs.logansquare.LoganSquare;
import com.github.fabienrenaud.jjb.JsonBench;
import com.google.gson.JsonSyntaxException;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;


/**
 * @author Fabien Renaud
 */
public class Deserialization extends JsonBench {

    @Benchmark
    @Override
    public Object gson() throws Exception {
        return JSON_SOURCE.provider().gson().fromJson(JSON_SOURCE.nextReader(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object jackson() throws Exception {
        return JSON_SOURCE.provider().jackson().readValue(JSON_SOURCE.nextByteArray(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object jackson_afterburner() throws IOException {
        return JSON_SOURCE.provider().jacksonAfterburner().readValue(JSON_SOURCE.nextByteArray(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object genson() throws JsonSyntaxException {
        return JSON_SOURCE.provider().genson().deserialize(JSON_SOURCE.nextByteArray(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object fastjson() {
        return JSON.parseObject(JSON_SOURCE.nextByteArray(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object flexjson() throws JsonSyntaxException {
        return JSON_SOURCE.provider().flexjsonDeser().deserialize(JSON_SOURCE.nextReader(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object boon() throws Exception {
        return JSON_SOURCE.provider().boon().readValue(JSON_SOURCE.nextByteArray(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object johnzon() throws Exception {
        return JSON_SOURCE.provider().johnson().readObject(JSON_SOURCE.nextInputStream(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object jsonsmart() throws Exception {
        return net.minidev.json.JSONValue.parse(JSON_SOURCE.nextByteArray(), JSON_SOURCE.pojoType());
    }

    @Benchmark
    @Override
    public Object dsljson() throws Exception {
        byte[] buffer = JSON_SOURCE.nextByteArray();
        return JSON_SOURCE.provider().dsljson().deserialize(JSON_SOURCE.pojoType(), buffer, buffer.length);
    }

    @Benchmark
    @Override
    public Object logansquare() throws Exception {
        return LoganSquare.parse(JSON_SOURCE.nextInputStream(), JSON_SOURCE.pojoType());
    }
}