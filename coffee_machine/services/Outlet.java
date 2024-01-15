package coffee_machine.services;

import java.util.concurrent.Callable;

public class Outlet implements Callable {
    private boolean serving;
    private String name;
    private BeverageMachine beverageMachine;

    private String beverageName;

    public Outlet(String name, BeverageMachine beverageMachine)
    {
        this.name = name;
        this.beverageMachine = beverageMachine;
    }

    public boolean isBusy()
    {
        return this.serving;
    }

    public void setBeverage(String beverageName)
    {
        this.beverageName = beverageName;
    }

    @Override
    public Object call() throws Exception {
        this.serving = true;
        return this.beverageMachine.serveBeverage(beverageName);
    }
}
