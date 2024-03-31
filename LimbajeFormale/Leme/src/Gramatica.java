
    import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Gramatica {

    private ArrayList<Productie>temp, listaProductii, listaProductii2;
    private String linie;
    private HashSet<String> terminale, neterminale;
    private HashSet<String> N2, T2;
    private HashSet<Productie> P2;
    
    

    public Gramatica(String nume_fisier) throws Exception{
        listaProductii = new ArrayList<>();
        BufferedReader buf = new BufferedReader(new FileReader(nume_fisier));
        while((linie = buf.readLine()) != null){
            String elems[] = linie.split(" ");
            Productie prod = new Productie(elems[0], elems[1]);
            listaProductii.add(prod);
        }
        buf.close();
    }


    public void pas0Lema1(){ 
        N2 = new HashSet<>();
        P2 = new HashSet<>();
        for(Productie prod:listaProductii){
            if(prod.getCuvant().matches("[a-z]+") || prod.getCuvant().matches("#")) {
                N2.add(prod.getSimbol());
                P2.add(prod);
            }
        }
        StringBuffer sbuf = new StringBuffer();
        for(Productie prod:P2) sbuf.append(prod.getCuvant());
        String[] parts = sbuf.toString().split("");
        T2 = new HashSet<>(Arrays.asList(parts)); T2.remove("#");
    }

    public void pas1Lema1(){
        
        for(int i = 1; i <= listaProductii.size(); i++){
            int productieAdaugata = 0;
            for(Productie prod:listaProductii){
                int ok = 1;
                if(P2.contains(prod)) continue;
                for(char c:prod.getCuvant().toCharArray()){
                    String s = String.valueOf(c);
                    if(!N2.contains(s) && !T2.contains(s)){
                        ok = 0;
                    } 
                }
                if(ok == 1){
                    N2.add(prod.getSimbol()); P2.add(prod);
                    productieAdaugata = 1;
                }
            }
            if (productieAdaugata == 0) break;
        }
        
    }

    public void pas2Lema1(){
        temp = new ArrayList<>(listaProductii);
        temp.removeAll(P2);
    }

    public void pas0Lema2(){
        listaProductii.removeAll(temp);
        listaProductii2 = new ArrayList<>(listaProductii);
        
        N2.clear(); T2.clear(); P2.clear();
        N2.add(listaProductii2.get(0).getSimbol());
    }

    public void pas1Lema2(){
        for(int i = 1; i <= listaProductii2.size(); i++){
            int productieAdaugata = 0;
            for(Productie prod:listaProductii2){
               
                if(P2.contains(prod)) continue;
                if(N2.contains(prod.getSimbol())){
                    for(char c:prod.getCuvant().toCharArray()){
                        String s = String.valueOf(c);
                        if(s.matches("[a-z]")) T2.add(s);
                        else if(s.matches("[A-Z]")) N2.add(s);
                        P2.add(prod);
                        productieAdaugata = 1;
                    }   
                }
            }
            if (productieAdaugata == 0) break;
        }
    }

    public void pas2Lema2(){
        temp.clear();
        temp.addAll(listaProductii2);
        temp.removeAll(P2);
    }

    public String afiseazaTsiN(){
        StringBuffer sbuf = new StringBuffer();
        for(Productie prod:listaProductii) sbuf.append(prod.getCuvant());
    
        String[] arr1 = sbuf.toString().replaceAll("[^a-z]", "").split("");
        String[] arr2 = sbuf.toString().replaceAll("[^A-Z]", "").split("");

        terminale = new HashSet<>(Arrays.asList(arr1));
        neterminale = new HashSet<>(Arrays.asList(arr2));

        for(Productie prod:listaProductii) neterminale.add(prod.getSimbol());

        sbuf.delete(0, sbuf.length());
        sbuf.append("Pentru urmatoarea lista de productii sa se aplice cele 2 leme: \n");
        for(Productie prod:listaProductii) sbuf.append(prod.getSimbol() + " -> " + prod.getCuvant() + "\n");
        //sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("\n");
        sbuf.append("Multimea terminalelor:\n\nT = { ");
        for(String term:terminale) sbuf.append(term + ", ");
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("}");
        sbuf.append("\n\nMultimea nonterminalelor:\n\nN = { ");
        for(String neterm:neterminale) sbuf.append(neterm + ", ");
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("}\n\n");

        return sbuf.toString();

    }

    public String afiseazaLeme(int x){
        StringBuffer sbuf = new StringBuffer();

        sbuf.append("Dupa lema " + x + " multimile vor fi:\n\n");
        sbuf.append("N2 = { ");
        for(String s:N2) sbuf.append(s + ", ");
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("}");
        sbuf.append("\n");

        sbuf.append("T2 = { ");
        for(String s:T2) sbuf.append(s + ", ");
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("}");
        sbuf.append("\n");

        sbuf.append("P2 = { ");
        for(Productie prod:P2) sbuf.append(prod.getSimbol() + " -> " + prod.getCuvant() + ", ");
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("}");
        sbuf.append("\n\n");
        
        sbuf.append("Productiile inutile: ");
        for(Productie prod:temp) sbuf.append(prod.getSimbol() + " -> " + prod.getCuvant() + ", ");
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("\n\n");

        return sbuf.toString();
    }
}

