import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdressMain {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        List<ProfileCEP> listCEP = new ArrayList<>();
        String searchCEP = "";

        Gson myGson = new GsonBuilder().setPrettyPrinting().create();

        while (!searchCEP.equalsIgnoreCase("sair")){

            System.out.println("\n ----- BUSCADOR DE ENDEREÇOS -----\n");

            System.out.println("Digite o CEP (Ou \"SAIR\" para encerrar.):");
            searchCEP = userInput.next();

            if (searchCEP.equalsIgnoreCase("sair")){
                break;
            }

            while (searchCEP.length() != 8){
                System.out.println("\nÉ necessário que o campo receba 8 dígitos númericos.");

                System.out.println("\nDigite o CEP (Ou \"SAIR\" para encerrar.):");
                searchCEP = userInput.next();

                if (searchCEP.equalsIgnoreCase("sair")){
                    break;
                }
            }

            QueryCEP addressQuery = new QueryCEP();

            try{
                ProfileCEP cepInfo = addressQuery.adressSearch(searchCEP);

                if (cepInfo != null){
                    listCEP.add(cepInfo);
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }

        if (!listCEP.isEmpty()){
            int loopCounter = 0;

            System.out.println("\n ----- SEUS ENDEREÇOS:");
            for (loopCounter = 0; loopCounter < listCEP.size(); loopCounter++){
                System.out.println((loopCounter + 1) + ". " + listCEP.get(loopCounter));
            }

            String listCEPJson = myGson.toJson(listCEP);

            try{
                FileGenerator myJsonFile = new FileGenerator();
                myJsonFile.jsonFile(listCEPJson);

                System.out.println("[Arquivo \"Endereços.json\" criado com sucesso!]");

            } catch (IOException e) {
                System.out.println(e);
                throw new RuntimeException(e);

            }
        }

        System.out.println("\nAplicação encerrada corretamente.");

    }
}
