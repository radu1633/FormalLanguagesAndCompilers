import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Lema3 {
    String linie;
    ArrayList<Productie> listaProductii;
    ArrayList<String> elementeNulabile;
    Productie prod;
    String sir;
    Map<Integer, String> map = new HashMap<>();
    StringBuffer sb = new StringBuffer();

    public Lema3(String nume_fisier) throws Exception{
        listaProductii = new ArrayList<>();

        BufferedReader buf = new BufferedReader(new FileReader(nume_fisier));
        while((linie = buf.readLine()) != null){
            String elems[] = linie.split(" ");
            Productie prod = new Productie(elems[0], elems[1]);
            listaProductii.add(prod);
        }
        buf.close();
    }
    
    public void pas0(){ //determinam  elementele nulabile
        sir = listaProductii.get(0).getCuvant();
        elementeNulabile = new ArrayList<>();
        for(Productie prod:listaProductii) 
            if(prod.getCuvant().equals("#")) 
                elementeNulabile.add(prod.getSimbol());

        String pattern = elementeNulabile.toString().replaceAll("[,\\s]+", "") + "*";
        
        for(Productie prod:listaProductii){
            if(prod.getCuvant().matches(pattern)) elementeNulabile.add(prod.getSimbol());
        }
        for(int i = 0; i < sir.length(); i++){
            if(elementeNulabile.contains(String.valueOf(sir.charAt(i)))) map.put(i, String.valueOf(sir.charAt(i)));
        }
    }

    public void pas1(){
        int[] vectorCaracteristic =  new int[sir.length()];

        StringBuffer sbuf0 = new StringBuffer(), sbuf1 = new StringBuffer();
        for(int i = 0; i < sir.length(); i++){
            sbuf0.append("0");
            sbuf1.append("1");
        } 
        
        String sirCu0 = sbuf0.toString(), sirCu1 = sbuf1.toString(); 
        int j = 0;
        scrieLema3();
        while(!sirCu1.equals(sirCu0)){
            j = 0;
            for(int i = sirCu1.length() - 1; i >= 0; i--){
                if(sirCu1.charAt(i) == '0') {
                    sirCu1 = sirCu1.substring(0, i) + '1' + sirCu1.substring(i+1); 
                }
                else if(sirCu1.charAt(i) == '1'){
                    sirCu1 = sirCu1.substring(0, i) + '0' + sirCu1.substring(i+1);
                    break;
                }
            }
            for(char ch : sirCu1.toCharArray()) vectorCaracteristic[j++] = Integer.parseInt(String.valueOf(ch));
            checkPosibilitate(vectorCaracteristic);  
        }   
    }
    

    public void checkPosibilitate(int[] vector){
        boolean conditie = true;
        for(int i = 0; i < sir.length(); i++){
            if(!map.containsKey(i) && vector[i] != 1){
                conditie = false; break;
            }
        }
        if(conditie){
            scrieSirModificat(vector);
        }
    }

    public void scrieLema3(){
        sb.append("\n\nPentru urmatoarea lista de productii sa se aplice lema 3:\n");
        for(Productie prod : listaProductii) sb.append(prod.getSimbol() + " -> " + prod.getCuvant() + "\n");
        sb.append("\nElementele nulabile = " + elementeNulabile.toString());
        sb.append("\n\nDupa eliminarea elementelor nulabile din sirul " + sir + ": \n");
    }

    public void scrieSirModificat(int[] array){
        sb.append("A -> ");
        for(int i = 0; i < array.length; i++){ 
            if(array[i] == 1){
                sb.append(sir.charAt(i));
            }
        }
        sb.append("\n");
    }
    
    public void scrieRezultat(){
        System.out.println(sb.toString());
    }
}
    
    

