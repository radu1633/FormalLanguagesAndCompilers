import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Chomsky {
    ArrayList<Productie> listaProductii;
    String linie;
    Set<String>[][] tabel;
    StringBuffer sbuf = new StringBuffer();

    public Chomsky(String nume_fisier) throws Exception{
        listaProductii = new ArrayList<>();
        
        BufferedReader buf = new BufferedReader(new FileReader(nume_fisier));
        while((linie = buf.readLine()) != null){
            Productie prod = null;
            String elems[] = linie.split(" ");
            if(elems[1].contains("|")){
                String elems1[] = elems[1].split("\\|");
                for(String s:elems1) {
                    prod = new Productie(elems[0], s);
                    listaProductii.add(prod);
                }
            }
            else {
                prod = new Productie(elems[0], elems[1]);
                listaProductii.add(prod);
            }
            
        }
        buf.close();
    }

    public void afiseazaLista(){
        for(Productie prod:listaProductii) System.out.println(prod.getSimbol() + " -> " + prod.getCuvant());
    }

    public void tabel(String input){
        int length = input.length() + 1;
        tabel = new Set[length][length];
        
        for(int i = 1; i < length; i++){
            Set<String> set = new HashSet<>();
            for(Productie prod:listaProductii){
                if(prod.getCuvant().equals(String.valueOf(input.charAt(i-1)))){
                    set.add(prod.getSimbol());
                }
            }
            tabel[1][i] = set; 
        }

        for(int j = 2; j < length; j++){
            for(int i = 1; i < length - j + 1; i++){
                Set<String> set = new HashSet<>();
                for(int k = 1; k <= j - 1; k++){
                    for(String simbol1 : tabel[k][i]){
                        for(String simbol2 : tabel[j-k][i+k]){
                            StringBuffer sbuf = new StringBuffer();
                            sbuf.append(simbol1);
                            sbuf.append(simbol2);
                            for(Productie prod: listaProductii){
                                if(prod.getCuvant().equals(sbuf.toString())){
                                    set.add(prod.getSimbol());
                                }
                            }
                            
                        }
                    }
                }
                if(set.isEmpty() == true) set.add("0");
                tabel[j][i] = set;
            }
        }
    }

    public void afiseazaTabel(String input){
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("\n");
        for(char ch : input.toCharArray()) sbuf.append("  "+ ch +"   ");
        sbuf.append("\n");

        int length = tabel.length;
        for(int i = 1; i < length; i++) sbuf.append("-----+");
        sbuf.append("\n");
        for(int i = 1; i < tabel.length; i++){
            for(int j = 1; j < tabel.length; j++)
                if(tabel[i][j] == null) continue;
            else{
                if(tabel[i][j].size() == 1) sbuf.append("  " + tabel[i][j].toString().replaceAll("[\\s\\[\\]]", "") + "  |");
                else sbuf.append(" " + tabel[i][j].toString().replaceAll("[\\s\\[\\]]", "") + " |");
            } 
            sbuf.append("\n");
            for(int k = 1; k < length; k++) sbuf.append("-----+"); length--;
            sbuf.append("\n");
        }

        System.out.println(sbuf);
    }

    public String verificaInput(){
        if(tabel[tabel.length-1][1].contains(listaProductii.get(0).getSimbol())) return "Cuvantul este acceptat de gramatica!";
        return "Cuvantul nu este acceptat de gramatica!";
    }

}
