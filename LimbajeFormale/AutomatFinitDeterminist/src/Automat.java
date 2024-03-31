import java.io.*;
import java.util.*;

public class Automat {
    private int st_init;
    private int st_fin[];
    private ListaTranzitii lista;
    private HashSet<Character> simboluri = null;
    private HashSet<Integer> stari = null;

    public Automat(String nume_fisier) throws Exception{
        lista = new ListaTranzitii();
        BufferedReader buf = new BufferedReader(new FileReader(nume_fisier));
        this.st_init = Integer.parseInt(buf.readLine().charAt(1) + "");
        String stf = buf.readLine();
        String[] split = stf.split(" ");
        st_fin = new int[split.length];
        for(int i =0; i < split.length; i++){
            st_fin[i] = Integer.parseInt(split[i].substring(1));
        }
        while(true){
            String linie = buf.readLine();
            if(linie != null){
                String elems[] = linie.split(" ");
                Tranzitie tr = new Tranzitie(Integer.parseInt(elems[0].substring(1)), elems[1].charAt(0), Integer.parseInt(elems[2].substring(1)));
                lista.adaugaTranzitie(tr);
            }else break;
        }
    }


    public boolean analizeazaCuvant(String sir){
        int stareCurenta = st_init;

        Tranzitie temp = null;
        for(int i = 0; i < sir.length(); i++){
            temp = lista.gasesteTranzitie(stareCurenta, sir.charAt(i));
            if(temp != null){
                System.out.println(temp.scrieDelta());
                stareCurenta = temp.getStSfarsit();
            }
        }

        if(temp != null && verificaStareFinala(stareCurenta) == true){
            return true;
        }
        return false;
    }

    public boolean verificaStareFinala(int stare){
        for(int stareVector : st_fin){
            if(stareVector == stare) return true;
        }
        return false;
    }

    public String scrieAutomat(){
        stari = lista.getStari();
        simboluri = lista.getSimboluri();
        int i;
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("Q = {");
        for(i = 0; i < stari.size() - 1; i++){
            sbuf.append(" q" + i + ",");
        }
        sbuf.append(" q" + i + " }\n");
        sbuf.append("\u03A3 = { ");
        for(Character ch : simboluri){
            sbuf.append(ch + ", ");
        }
        sbuf.deleteCharAt(sbuf.length() - 2);
        sbuf.append("}\n");
        
        return sbuf.toString();
    }

    
    
    
}
