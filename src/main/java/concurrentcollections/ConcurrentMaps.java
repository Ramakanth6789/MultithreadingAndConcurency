package concurrentcollections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapFirstWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public MapFirstWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {

        try {
            map.put("Ram", 27);
            map.put("Dam", 34);
            Thread.sleep(2000);
            map.put("Sin", 34);
            Thread.sleep(1000);
            map.put("Tin", 24);
            map.put("Din", 43);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MapSecondWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public MapSecondWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }
    @Override
    public void run() {

        try {
            System.out.println(map.get("Tin"));
            Thread.sleep(2000);
            System.out.println(map.get("Ram"));
            System.out.println(map.get("Dam"));
            Thread.sleep(2000);
            System.out.println(map.get("Tin"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentMaps {

    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        MapFirstWorker first = new MapFirstWorker(map);
        MapSecondWorker second = new MapSecondWorker(map);

        new Thread(first).start();
        new Thread(second).start();


    }
}
