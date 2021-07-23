public class FactoryMethodExample{
    public static void main(String[] args){
       //we have an app that needs to send a Kafka message...
        KafkaClient kafkaClient = new KafkaClient();
        kafkaClient.processMessage("topic1");
    }
}

abstract class Client {

    void processMessage(String type){
        IConnection connection;

        connection = createConnection(type);

        connection.sendMessage();
    }

    abstract IConnection createConnection(String type);
}

class KafkaClient extends Client{
    IConnection createConnection(String topic){
        switch (topic){
            case "topic1":
                return new KafkaConnectionToTopic1();
            case "topic2":
                //return new KafkaConnectionToTopic2();
            default:
                return null;
        }
    }
}

class RabbitMQClient extends Client{
    IConnection createConnection(String exchange){
        switch (exchange){
            case "exchange1":
                return new RabbitMQConnectionToExchange1();
            case "exchange2":
                //return new RabbitMQConnectionToExchange2();
            default:
                return null;
        }
    }
}

class AmazonSQSClient extends Client{
    IConnection createConnection(String queue){
        switch (queue){
            case "queue1":
                return new AmazonSQSToQueue1();
            case "queue2":
                //return new AmazonSQSToQueue2();
            default:
                return null;
        }
    }
}

class KafkaConnectionToTopic1 extends KafkaConnection{
    String details;

    public KafkaConnectionToTopic1(){
        this.details = "~~ Initializing connection to Kafka Topic1 ~~";
        System.out.println(details);
    }
}
class RabbitMQConnectionToExchange1 extends RabbitMQConnection{
    String details;

    public RabbitMQConnectionToExchange1(){
        this.details = "~~ Initializing connection to RabbitMQ Exchange1 ~~";
        System.out.println(details);
    }
}
class AmazonSQSToQueue1 extends AmazonSQSConnection{
    String details;

    public AmazonSQSToQueue1(){
        this.details = "~~ Initializing connection to AmazonSQS Queue1 ~~";
        System.out.println(details);
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
 * Factory Method Pattern ->
 *  With this pattern, we allow subclasses to decide which objects to create.
 *
 */

