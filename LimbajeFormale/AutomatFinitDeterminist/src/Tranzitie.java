public class Tranzitie {
    private int st_inceput;
    private char simbol;
    private int st_sfarsit;

    public Tranzitie(int st_inceput, char simbol, int st_sfarsit){
        this.st_inceput = st_inceput;
        this.simbol = simbol;
        this.st_sfarsit = st_sfarsit;
    }

    public int getStInceput(){
        return this.st_inceput;
    }

    public char getSimbol(){
        return this.simbol;
    }

    public int getStSfarsit(){
        return this.st_sfarsit;
    }

    public String scrieDelta(){
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("\u03B4(q" + st_inceput + ", "+ simbol + ") = {q" + st_sfarsit + "}");
        return sbuf.toString();
    }

    
}
