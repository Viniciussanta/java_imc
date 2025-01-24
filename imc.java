import java.util.Scanner;

// Classe principal, que precisa ter o mesmo nome do arquivo
public class imc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantas pessoas deseja cadastrar? ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        PessoaIMC[] pessoas = new PessoaIMC[quantidade];

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Digite o tipo (Homem ou Mulher): ");
            String tipo = scanner.nextLine();

            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a data de nascimento: ");
            String dataNascimento = scanner.nextLine();

            System.out.print("Digite o peso (kg): ");
            double peso = scanner.nextDouble();

            System.out.print("Digite a altura (m): ");
            double altura = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha

            if (tipo.equalsIgnoreCase("Homem")) {
                pessoas[i] = new Homem(nome, dataNascimento, peso, altura);
            } else if (tipo.equalsIgnoreCase("Mulher")) {
                pessoas[i] = new Mulher(nome, dataNascimento, peso, altura);
            } else {
                System.out.println("Tipo inválido! Tente novamente.");
                i--; // Refazer o cadastro dessa posição
            }
        }

        System.out.println("\nResultados:");
        for (PessoaIMC pessoa : pessoas) {
            System.out.println(pessoa.toString());
            System.out.println("IMC: " + pessoa.calculaIMC(pessoa.getAltura(), pessoa.getPeso()));
            System.out.println(pessoa.resultIMC());
        }

        scanner.close();
    }
}

// Classe Pessoa
class Pessoa {
    private String nome;
    private String dataNascimento;

    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nData de Nascimento: " + dataNascimento;
    }
}

// Classe abstrata PessoaIMC
abstract class PessoaIMC extends Pessoa {
    private double peso;
    private double altura;

    public PessoaIMC(String nome, String dataNascimento, double peso, double altura) {
        super(nome, dataNascimento);
        this.peso = peso;
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public double calculaIMC(double altura, double peso) {
        return peso / (altura * altura);
    }

    public abstract String resultIMC();

    @Override
    public String toString() {
        return super.toString() + "\nPeso: " + peso + "\nAltura: " + altura;
    }
}

// Classe Homem
class Homem extends PessoaIMC {
    public Homem(String nome, String dataNascimento, double peso, double altura) {
        super(nome, dataNascimento, peso, altura);
    }

    @Override
    public String resultIMC() {
        double imc = calculaIMC(getAltura(), getPeso());
        if (imc < 20.7) {
            return "Abaixo do peso ideal";
        } else if (imc <= 26.4) {
            return "Peso ideal";
        } else {
            return "Acima do peso ideal";
        }
    }
}

// Classe Mulher
class Mulher extends PessoaIMC {
    public Mulher(String nome, String dataNascimento, double peso, double altura) {
        super(nome, dataNascimento, peso, altura);
    }

    @Override
    public String resultIMC() {
        double imc = calculaIMC(getAltura(), getPeso());
        if (imc < 19) {
            return "Abaixo do peso ideal";
        } else if (imc <= 25.8) {
            return "Peso ideal";
        } else {
            return "Acima do peso ideal";
        }
    }
}

