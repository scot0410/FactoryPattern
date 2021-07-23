public class SimpleFactoryExample{
    public static void main(String[] args){
        Client client = new Client(new ConnectionFactory());
        client.processMessage("Kafka");
    }
}

class Client {
    ConnectionFactory connectionFactory;

    public Client(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }

    void processMessage(String type){
        IConnection connection;

        //create listener container...

        //gather configurations...

        connection = connectionFactory.createConnection(type);

        connection.sendMessage();
    }
}

class ConnectionFactory{

    //object creation is encapsulated here
    IConnection createConnection(String type){
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
                return null;
        }

        return connection;
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

