import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class csvtoxml {

    public static void main(String[] args) {
        String in = "addresses.csv";      
        String out = "addresses.xml";
        
        String xml = "";
        xml = xml + "<MT_3RD_CSV_TO_XML>"; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(in))) {
            String satir;
            
            while ((satir = br.readLine()) != null) {
                if (satir.trim().isEmpty()) {
                    continue;
                }
                
                String[] deger = satir.split(","); 
                
                xml = xml + "<Address>";
                xml = xml + "<name>" + deger[0] + "</name>";
                xml = xml + "<surname>" + deger[1] + "</surname>";
                xml = xml + "<street>" + deger[2] + "</street>";
                xml = xml + "<state>" + deger[3] + "</state>";
                xml = xml + "<city>" + deger[4] + "</city>";
                xml = xml + "<postCode>" + deger[5] + "</postCode>";
                xml = xml + "</Address>";
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        xml = xml + "</MT_3RD_CSV_TO_XML>"; 
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            bw.write(xml);
            System.out.println("Başarıyla oluşturuldu");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}