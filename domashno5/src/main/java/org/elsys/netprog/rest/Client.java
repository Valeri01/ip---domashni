package org.elsys.netprog.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
 
public class Client {
    public static void main(String[] args) throws NoSuchAlgorithmException, ParseException, IOException {
            URL url = new URL("http://localhost:8080/jersey-rest-homework/surverget");
           
            while(true) {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
           
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
                    //con.disconnect();      
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);  
                    }
                    in.close();
                   
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(content.toString());
                   
                    JSONObject jsonObject1 = (JSONObject) obj;
                    
                    int len = Integer.parseInt(jsonObject1.get("length").toString()) ;
                    JSONObject jsonObject2 = (JSONObject) obj;
                    String hash = (String) jsonObject2.get("hash");
                    byte[] in1 = new byte[(int) len];
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    String decode = new String(Base64.getUrlDecoder().decode(hash.getBytes()));
                    String myhash = "";
                    int i = 0;
                    long start = System.nanoTime();
                    while(!decode.equals(myhash)) {
                        new Random().nextBytes(in1);
                        myhash =new String(md.digest(in1));
                        i++;
                    }
                    long end = System.nanoTime();
                    double time =  (end - start) / 1000000000.0;

                    String string = Base64.getUrlEncoder().encodeToString(myhash.getBytes());
                    String bytes = Base64.getUrlEncoder().encodeToString(in1);
                    URL urlp = new URL("http://localhost:8080/jersey-rest-homework/surverpost/hash/" + string + "/input/"+ bytes );
                    HttpURLConnection connection = (HttpURLConnection) urlp.openConnection();
                    connection.setRequestMethod("POST");
                     int code= connection.getResponseCode();
                     if(code == 200){
                    	 System.out.println(string);
                    	 System.out.println("Hash is correct!\n Iterations:" + i + "\nTime: "+ time);
                    	 
                     }
            }

            
     }
}