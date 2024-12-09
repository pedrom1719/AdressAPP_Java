import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QueryCEP {
    
    Gson myGson = new GsonBuilder().setPrettyPrinting().create();
    
    public ProfileCEP adressSearch(String codeCEP){
        
        ProfileCEP cepInfo = null;
        URI endPoint = URI.create("https://viacep.com.br/ws/" + codeCEP + "/json/");

        try{
            HttpClient myClient = HttpClient.newHttpClient();
            HttpRequest myRequest = HttpRequest.newBuilder().uri(endPoint).build();

            HttpResponse<String> myResponse = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
            String responseAPI = myResponse.body();

            if (responseAPI.indexOf("erro") != -1){
                System.out.println("\nNão foi possível encontrar nenhum CEP com: " + codeCEP);

            } else if(myResponse.statusCode() == 400){
                System.out.println("\nNão é possível buscar CEPs com letras. Insira somente digitos numéricos.");

            } else {
                cepInfo = myGson.fromJson(responseAPI, ProfileCEP.class);
                System.out.println(cepInfo);
            }

        } catch (Exception e){
            System.out.println("\nERRO: " + e);
            throw new RuntimeException("Houve um erro durante a consulta à API do ViaCEP.");
        }
        
        return cepInfo;
    }
}
