package design_patterns.prototype.invoice;

import java.util.HashMap;

public class InvoiceRegistry implements InvoicePrototypeRegistry{

    private HashMap<String, Invoice> map = new HashMap<>();

    @Override
    public void addPrototype(Invoice user) {
        map.put(user.getType().toString(), user);
    }

    @Override
    public Invoice getPrototype(InvoiceType type) {
        return map.get(type.toString());
    }

    @Override
    public Invoice clone(InvoiceType type) {
        return map.get(type.toString()).cloneObject();
    }
}
