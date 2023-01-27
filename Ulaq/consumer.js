const amqp = require("amqplib");
const queueName = process.argv[2] || "patika-send-message";
const sendMail = require("./sendMail");



connect_rabbitmq();

async function connect_rabbitmq() {
  try {
    const connection = await amqp.connect("amqp://localhost:5672");
    const channel = await connection.createChannel();
    const assertion = await channel.assertQueue(queueName);
    channel.purgeQueue(queueName);
    
    
    // Mesajın Alınması...
    console.log("Mesaj bekleniyor...");
    channel.consume(queueName, message => {
      const veri =JSON.parse(message.content.toString()) ;
      
      sendMail(veri)
    });
    
  } catch (error) {
    console.log("Error", error);
  }
}
