package machine_coding.coffee_machine;

import java.util.concurrent.Semaphore;

public class Outlet {
    private final String name;
    private OutletStatus status;
    private final Semaphore semaphore;

    public Outlet(String name)
    {
        this.name = name;
        this.status = OutletStatus.AVAILABLE;
        semaphore = new Semaphore(1);
    }

    public void serveBeverage(String beverageName) throws InterruptedException {
        semaphore.acquire();
        updateStatus(OutletStatus.BUSY);
        System.out.println(this.name +" Serving Beverage " + beverageName);
        updateStatus(OutletStatus.AVAILABLE);
        semaphore.release();
    }

    public void updateStatus(OutletStatus status)
    {
        this.status = status;
    }

    public OutletStatus getStatus()
    {
        return this.status;
    }

    public String getName()
    {
        return this.name;
    }
}
