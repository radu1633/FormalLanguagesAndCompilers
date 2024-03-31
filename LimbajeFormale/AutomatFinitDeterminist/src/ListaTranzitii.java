import java.util.*;

public class ListaTranzitii {
    private ArrayList<Tranzitie> lista;
    private HashSet<Character> simboluri;
    private HashSet<Integer> stari;
    
    public ListaTranzitii(){
        lista = new ArrayList<>();
    }

    public void adaugaTranzitie(Tranzitie tr){
        lista.add(tr);
    }

    public Tranzitie gasesteTranzitie(int stare, char simbol){
        for(Tranzitie temp : lista){
            if((stare == temp.getStInceput()) && (temp.getSimbol() == simbol)) return temp;
        }
        return null;
    }

    public HashSet<Character> getSimboluri(){
        simboluri = new HashSet<>();
        for(Tranzitie temp : lista){
            simboluri.add(temp.getSimbol());
        }
        return simboluri;
    }

    public HashSet<Integer> getStari(){
        stari = new HashSet<>();
        for(Tranzitie temp : lista){
            stari.add(temp.getStInceput()); stari.add(temp.getStSfarsit());
        }
        return stari;
    }

    public String citesteLista(){
        StringBuffer sb = new StringBuffer();
        for(Tranzitie temp : lista){
            sb.append(temp.getStInceput() + " " + temp.getSimbol() + " " + temp.getStSfarsit() + "\n");
        }
        return sb.toString();
    }
}
