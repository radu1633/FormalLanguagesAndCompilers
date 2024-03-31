import javax.swing.JOptionPane;

public class TestChomsky {
    public static void main(String ... args) throws Exception{
        Chomsky test = new Chomsky("D:\\JavaProjectVSCode\\LimbajeFormale\\Chomsky\\chomsky.txt");
        String input = JOptionPane.showInputDialog("Introduceti un cuvant de verificat:");
        test.tabel(input);
        test.afiseazaTabel(input);
        System.out.println();
        System.out.println(test.verificaInput() + "\n");
    }
}
