public class SimpleFactoryClient{
    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //create listener container...
        //gather configurations...

        IConnection kafkaConnection = connectionFactory.createConnection("Kafka");

        kafkaConnection.sendMessage();
        //process message
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
    public void sendMessage();
}


class KafkaConnection implements IConnection{
    @Override
    public void sendMessage(){
        System.out.println("sending message to Kafka topic...");
    }
}

class RabbitMQConnection implements IConnection{
    @Override
    public void sendMessage(){
        System.out.println("sending message to RabbitMQ exchange...");
    }
}

class AmazonSQSConnection implements IConnection{
    @Override
    public void sendMessage(){
        System.out.println("sending message to AmazonSQS queue...");
    }
}


/**
 * Simple Factory Pattern -> one class designed to return different objects based on a given input.
 * Pros: we can encapsulate object creation in one place
 * Cons: as available Queue/Message Streaming services grows, so does our conditional :(
 */

