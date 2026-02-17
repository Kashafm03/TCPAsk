package tcpclient;

import java.io.*;
import java.net.Socket; // Importerar Socket-klassen för nätverkskommunikation

public class TCPClient {
    
    public TCPClient() {
    }

    public byte[] askServer(String hostname, int port, byte [] toServerBytes) throws IOException {
        Socket socket = new Socket(hostname, port); // Skapar en socket-anslutning till servern på angivet värdnamn och port


        try {

            OutputStream outputStream = socket.getOutputStream(); // För att skicka data
            InputStream inputStream = socket.getInputStream(); // För att ta emot data


            // Skapar en ByteArrayOutputStream för att spara data som tas emot från servern
            ByteArrayOutputStream byteArrayOutputStream  =  new ByteArrayOutputStream();
        
    // Om det finns data att skicka till servern
    if (toServerBytes != null & toServerBytes.length > 0) {
        outputStream.write(toServerBytes); // Skickar data till servern
        outputStream.flush(); // Säkerställer att all data skickas direkt
    }

    // Buffer för att läsa inkommande data i små delar
    byte[] buffer = new byte[512]; //  buffertstorlek
    int bytesRead; // Variabel för att lagra antal lästa bytes

    // Läser data från servern tills det inte finns mer att läsa (dvs. -1)
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        byteArrayOutputStream.write(buffer, 0, bytesRead); // Lagrar de lästa byten i en buffer
    }

    // Returnerar all mottagen data som en byte-array
    return byteArrayOutputStream.toByteArray();
  } finally {
    socket.close(); // Stänger socketen i "finally"-blocket för att säkerställa att den alltid stängs
  }
}
     

    public byte[] askServer(String hostname, int port) throws IOException {
        return askServer(hostname, port, new byte[0]);  //Anropar huvudmetoden men skickar ingen data
    }

}
