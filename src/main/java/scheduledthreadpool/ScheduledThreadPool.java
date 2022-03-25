package scheduledthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdate implements Runnable{

    @Override
    public void run() {
        System.out.println("updating the stock market prices.....");
    }

}
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new StockMarketUpdate(),1000, 3000, TimeUnit.MILLISECONDS);

    }
}
