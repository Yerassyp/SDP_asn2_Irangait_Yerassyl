package logistics;
import transport.Transport;

public abstract class Logistics {
    public void planDelivery(String cargo, String destination){
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }
    public abstract Transport createTransport();
}
