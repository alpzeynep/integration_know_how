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

        def datetime = parsed.time.split("T")

        def datePart = datetime[0]

        def timePart = datetime[1].replace("Z","")

        def typePart = parsed.type.split("\\.")
        println(typePart)
        def splittedType = typePart[6]


        builder{

            partner_id parsed.data.BusinessPartner
            partner_name parsed.data.BusinessPartnerName
            source parsed.source
            type splittedType
            time timePart
            date datePart
            address([
                {
                    city_name parsed.data.City 
                    street_name parsed.data.Street
                    door_number parsed.data.DoorNumber 
                    post_code parsed.data.Postcode 
                }
            ])
        }

        return builder.toPrettyString()

    }
    catch(Exception e) {

        throw new RuntimeException("Exception ${e.message}")
    }
} 