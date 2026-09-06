import com.sap.gateway.ip.core.customdev.util.Message;
    import java.util.HashMap;
    
    def Message processData(Message message) {
        println "You can print and see the result in the console!"
        //Body 
        def body = message.getBody(String).replace("ns0:","");
        message.setBody(body);
        //Headers 
       
        return message;
        }
  