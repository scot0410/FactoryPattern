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

        connection = connectionFactory.createConnection(type);

        connection.setConfigs();

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
    public void setConfigs();
    public void sendMessage();
}


class KafkaConnection implements IConnection{
    @Override
    public void setConfigs(){
        System.out.println("~~ setting up Kafka configs ~~");
    }

    @Override
    public void sendMessage(){
        System.out.println("-> SENDING message to Kafka topic -> ");
    }
}

class RabbitMQConnection implements IConnection{
    @Override
    public void setConfigs(){
        System.out.println("~~ setting up RabbitMQ configs ~~");
    }

    @Override
    public void sendMessage(){
        System.out.println("-> SENDING message to RabbitMQ exchange ->");
    }
}

class AmazonSQSConnection implements IConnection{
    @Override
    public void setConfigs(){
        System.out.println("~~ setting up AmazonSQS configs ~~");
    }

    @Override
    public void sendMessage(){
        System.out.println("-> SENDING message to AmazonSQS queue ->");
    }
}


/**
 * Simple Factory Pattern -> one class designed to return different objects based on a given input.
 * Pros: we can encapsulate object creation in one place
 * Cons: as available Queue/Message Streaming services grows, so does our conditional :(
 */

