public class NoFactoryClient{
    public static void main(String[] args){
        String type = "Kafka";
        IConnection connection;

        switch (type){
            case "Kafka":
                connection = new KafkaConnection();
                break;
            case "RabbitMQ":
                connection = new RabbitMQConnection();
                break;
            case "AmazonSQS":
                connection = new AmazonSQSConnection();
                break;
            default:
                connection = null;

        }

        connection.connect();
    }
}

interface IConnection{
    public void connect();
}


class KafkaConnection implements IConnection{
    @Override
    public void connect(){
        System.out.println("connecting to Kafka...");
    }
}

class RabbitMQConnection implements IConnection{
    @Override
    public void connect(){
        System.out.println("connecting to Rabbit...");
    }
}

class AmazonSQSConnection implements IConnection{
    @Override
    public void connect(){
        System.out.println("connecting to AmazonSQS...");
    }
}


/**
 * No Factory method
 * Cons: not really possible to have more than one client here..
 *       if we had a factory, we could not only encapsulate and hide away this object creation logic,
 *       but we could have multiple clients using the factory to create connections.
 */

