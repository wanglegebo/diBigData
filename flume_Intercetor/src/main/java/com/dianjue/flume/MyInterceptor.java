package com.dianjue.flume;

import com.dianjue.utils.ETLUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

public class MyInterceptor implements Interceptor {
    private List<Event> list = new ArrayList<>();
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //byte[] body = event.getBody();
        boolean flag;
        String log = new String(event.getBody());
        if (log.contains("start")) {
            flag = ETLUtils.startOption(log);
        }else{
            flag = ETLUtils.logOption(log);
        }

        //System.out.println(flag);

        if(flag){
            //System.out.println("ETL" + new String(event.getBody()));
            return event;
        }else{
            return null;
        }
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        list.clear();
        for (Event event : events) {

            Event intercept = intercept(event);
            if (intercept != null){
                list.add(intercept);
            }
        }
        return list;
    }

    @Override
    public void close() {

    }
    public static class Builders implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new MyInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
