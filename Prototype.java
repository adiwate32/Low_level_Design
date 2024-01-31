public class Prototype {

    private static Prototype prototype;

    public static Prototype getInstance()
    {
        if(prototype == null)
        {
            prototype = new Prototype();
        }
        return prototype;
    }

    public Prototype clone()
    {
        return Prototype.getInstance();
    }
}
