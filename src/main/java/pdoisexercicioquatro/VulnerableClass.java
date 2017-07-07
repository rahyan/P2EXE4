package pdoisexercicioquatro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {

  //Verifiar de Pattern para o nome do arquivo
  //OBS: eu fiz um pattern simples para poder demonstrar, um codigo mais eficiente
  //deveria ter um patter bem mais complexo
  /**
   * Multiple lines of Javadoc text are written here,
   * wrapped normally...
   */
  public boolean isValidFileName(String text) {
    Pattern pattern = Pattern.compile("(([A-Za-z0-9_])+)\\.txt");
    Matcher matcher = pattern.matcher(text);
    boolean isMatch = matcher.matches();
    return isMatch;
  }

  /**
   * Multiple lines of Javadoc text are written here,
   * wrapped normally...
   */
  public boolean vulnerableMethod(String filename, NextLineAsker nla) {

    if (!isValidFileName(filename)) {
      System.out.println("Nome do Arquivo Invalido");
      return false; //retorna falsa pq o nome do arquivo nao eh valido
    } else {
      boolean exit = false; //boolean para decidir se deve sair do metodo

      while (!exit) {
        Scanner console = new Scanner(System.in);

        System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um "
            + "arquivo, W para escrever em um arquivo, E para encerrar>: ");

        String opr = nla.ask(console);

        if (opr.equals("E") || opr.equals("e")) {
          //foi criada a opcao de sair do programa para o Scanner
          //console poder ser encerrado corretamente
          console.close();
          exit = true;
        }

        if (opr.equals("R") || opr.equals("r")) {
          BufferedReader br = null;
          FileReader fr = null;

          try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String scurrentline;

            br = new BufferedReader(fr);

            while ((scurrentline = br.readLine()) != null) {
              System.out.println(scurrentline);
            }

            //Devem ser fechados para encerramento correto do programa
            fr.close();
            br.close();

          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else if (opr.equals("W") || opr.equals("w")) {
          BufferedWriter bw;
          FileWriter fw; //deve ser referenciado para poder ser fechado depois de utilizado

          try {
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);

            String linha = "";
            System.out.println("Escreva algo: ");
            linha = console.nextLine();

            bw.append(linha + "\n");

            //Devem ser fechados para encerramento correto do programa
            //No programa original, o arquivo nao era atualizado pois nunca era fechado
            bw.close();
            fw.close();

          } catch (IOException e) {
            e.printStackTrace();
          } 
        }

      }
      //se chegou aqui � porque o nome de arquivo eh valido
      return true;
    }
  }
}
