import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.SecureStoreService
import com.sap.it.api.securestore.exception.SecureStoreException

def Message processData(Message message) {

    // Content Modifier'dan gelen Secure Parameter alias'ı
    String alias = message.getProperty("SecureParameterAlias") as String

    if (!alias?.trim()) {
        throw new SecureStoreException(
            "SecureParameterAlias property is missing or empty"
        )
    }

    try {
        SecureStoreService secureStoreService =
                ITApiFactory.getService(SecureStoreService.class, null)

        if (secureStoreService == null) {
            throw new SecureStoreException(
                "SecureStoreService could not be obtained"
            )
        }

        // Secure Parameter'ı alias üzerinden oku
        def credential =
                secureStoreService.getUserCredential(alias.trim())

        if (credential == null) {
            throw new SecureStoreException(
                "No Secure Parameter found for alias: " + alias
            )
        }

        String secureValue =
                credential.getPassword()?.toString()

        if (!secureValue) {
            throw new SecureStoreException(
                "Secure Parameter is empty for alias: " + alias
            )
        }

        /*
         * Buradan sonra secureValue'i doğrudan ihtiyacın olan işlemde kullan.
         *
         * Örnek:
         * message.setHeader(...)
         * message.setProperty(...)
         *
         * Ancak SAP, hassas değeri header/property içinde tutmamayı
         * production için özellikle öneriyor.
         */

        message.setProperty("SecureParameterValue", secureValue)

    } catch (SecureStoreException e) {
        throw e
    } catch (Exception e) {
        throw new SecureStoreException(
            "Error while reading Secure Parameter: " + e.getMessage()
        )
    }

    return message
}