import com.sap.gateway.ip.core.customdev.util.Message

Message processData(Message message) {
    // 1. Gelen mesaj gövdesini al
    def body = message.getBody(String)
    if (!body) return message

    // 2. Tanımlamak istediğiniz Namespace URL
    def nsUri = "HRIntegrations"

    // 3. Tek tırnak ile regex ve replacement yazımı ($ işaretinde hata vermez)
    def pattern = '<([a-zA-Z0-9_\\-\\:]+)(\\s|>|\\/>)'
    def replacement = '<$1 xmlns="' + nsUri + '"$2'

    // 4. Sadece ilk açılış etiketine (Root) namespace ekle
    def updatedXml = body.replaceFirst(pattern, replacement)

    // 5. Güncellenmiş XML'i mesaja aktar
    message.setBody(updatedXml)

    return message
}