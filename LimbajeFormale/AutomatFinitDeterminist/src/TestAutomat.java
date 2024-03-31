import javax.swing.JOptionPane;

public class TestAutomat {
    public static void main(String[] args) throws Exception{
        Automat M = new Automat("D:\\JavaProjectVSCode\\AutomatFinitDeterminist\\automat.txt");
        System.out.println("\n\nAutomat:\n" + M.scrieAutomat());
        String sir = JOptionPane.showInputDialog("Input: ");
        System.out.println("Cuvantul de verificat: " + sir + " \n");
        if(M.analizeazaCuvant(sir) == true){
            System.out.println("\nCuvantul este acceptat!\n");
        }
        else System.out.println("\nCuvantul NU este acceptat!\n");
        
        
    }
}
