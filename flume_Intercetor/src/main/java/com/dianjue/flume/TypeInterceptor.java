package com.dianjue.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeInterceptor implements Interceptor {
    private List<Event> list = new ArrayList<>();
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String log = new String(body);

        Map<String, String> eventHeaders = event.getHeaders();

        if (log.contains("start")) {
            eventHeaders.put("type", "start");
        } else {
            eventHeaders.put("type", "action");
        }
        //System.out.println("Type" + new String(event.getBody()));
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        list.clear();
        for (Event event : events) {
            list.add(intercept(event));
        }
        return list;
    }

    @Override
    public void close() {

    }
    public static class BuilderType implements Interceptor.Builder{

        @Override
        public Interceptor build() {

            return new TypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
