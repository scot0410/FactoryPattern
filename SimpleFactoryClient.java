public class SimpleFactoryClient{
    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ConnectionFactory();

        IConnection kafkaConnection = connectionFactory.createConnection("Kafka");

        kafkaConnection.connect();
    }
}

class ConnectionFactory{
    //object creation is encapsulated here
    IConnection createConnection(String type){
        switch (type){
            case "Kafka":
                return new KafkaConnection();
            case "RabbitMQ":
                return new RabbitMQConnection();
            case "AmazonSQS":
                return new AmazonSQSConnection();
            default:
                return null;
        }
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
 * Simple Factory Pattern -> one class designed to return different objects based on a given input.
 * Pros: we can encapsulate object creation in one place
 * Cons: as available Queue/Message Streaming services grows, so does our conditional :(
 */

