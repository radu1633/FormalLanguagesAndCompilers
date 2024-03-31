public class TestGramatica {
    public static void main(String... args) throws Exception{
        Gramatica gramatica = new Gramatica("D:\\JavaProjectVSCode\\LimbajeFormale\\Leme\\gramatica.txt");
        System.out.println(gramatica.afiseazaTsiN());
        gramatica.pas0Lema1();
        gramatica.pas1Lema1();
        gramatica.pas2Lema1();
        System.out.print(gramatica.afiseazaLeme(1));
        gramatica.pas0Lema2();
        gramatica.pas1Lema2();
        gramatica.pas2Lema2();
        System.out.print(gramatica.afiseazaLeme(2));

        Lema3 lema = new Lema3("D:\\JavaProjectVSCode\\LimbajeFormale\\Leme\\lema3.txt");
        lema.pas0();
        lema.pas1();
        lema.scrieRezultat();
    }
}
