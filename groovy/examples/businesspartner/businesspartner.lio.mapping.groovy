import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

Message processData(Message message) {
    // 1. Gelen mesaj gövdesini al
    def body = message.getBody(String)          
    

    //Transform fonksiyonu cagrilarak input json updates json a donusturulur
    def updatedJson = Transform(body) 

    //Yeni yapidaki json message body si olarak atanir(after mapping)
    message.setBody(updatedJson)

    return message
}
    

def String Transform(String jsontextinp){

    try{
        //Json slurper olusturarak input json strigine parse ettik
        def slurper = new JsonSlurper()

        def parsed = slurper.parseText(jsontextinp)

        def builder = new JsonBuilder()

        builder{

            id parsed.data.BusinessPartner
        }

        return builder.toString()

    }
    catch(Exception e) {

        throw new RuntimeException("Exception ${e.message}")
    }
} 