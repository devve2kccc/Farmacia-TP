import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {

    private static final String INVALID_OPTION = "Digito Invalido. Tente Novamente.";
    private static NumberFormat numberFormat = Utils.getNumberFormat();

    private static Farmacia farmacia = new Farmacia();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        farmacia.insereProduto(new Produto(Categoria.MEDICAMENTO, "Aspirin", "Pain reliever", 100, 10.0, 20,
                LocalDate.now().plusMonths(6)));
        farmacia.insereProduto(new Produto(Categoria.COSMETICO, "Lip Balm", "For dry lips", 50, 10.0, 20,
                LocalDate.now().plusMonths(12)));
        farmacia.insereProduto(new Produto(Categoria.HIGIENE, "Hand Sanitizer", "Kills germs", 200, 10.0, 20,
                LocalDate.now().plusMonths(24)));
        farmacia.insereProduto(new Produto(Categoria.NUTRICIONAL, "Vitamin C", "Boosts immunity", 150, 10.0, 20,
                LocalDate.now().plusMonths(18)));
        farmacia.insereProduto(new Produto(Categoria.EQUIPAMENTO_MEDICO, "Thermometer", "Measures body temperature", 30,
                10.0, 20, LocalDate.now().plusMonths(60)));

        farmacia.insereCliente(
                new Cliente("Tiago Gomes", 227311389, new Morada("Travessa", "Barcelos"), new ArrayList<>()));
        farmacia.insereCliente(
                new Cliente("Alvaro Fernandes", 227311388, new Morada("Travessa", "Barcelos"), new ArrayList<>()));

        int opcao;
        do {
            opcao = getMainMenuChoice(scan);

            switch (opcao) {
                case 1:
                    int opcao2 = getAdminMenuChoice(scan);

                    switch (opcao2) {
                        case 1:
                            System.out.println("\n Procurar Cliente por:");
                            System.out.println("   1 - Nome");
                            System.out.println("   2 - NIF");
                            System.out.println("   0 - Voltar");
                            int o = -1;

                            o = getMenuChoice(scan, 0, 2);

                            if (o == 0) {
                                break;
                            }

                            System.out.println();
                            boolean clienteExist = false;
                            int clientIndex = -1;

                            switch (o) {
                                case 1:
                                    clientIndex = farmacia.getClientByName(scan);
                                    if (clientIndex != -1) {
                                        clienteExist = true;
                                    }
                                    break;
                                case 2:
                                    clientIndex = farmacia.getClientByNIF(scan);
                                    if (clientIndex != -1) {
                                        clienteExist = true;
                                    }
                                    break;

                                default:
                                    System.out.println(INVALID_OPTION);
                            }

                            if (clienteExist) {
                                ArrayList<Produto> produtosEscolhicos = new ArrayList<>();
                                int categoriaEscolhidaIndex;
                                int produtoEscolhidoIndex;
                                do {
                                    System.out.println();

                                    farmacia.mostrarCategorias();
                                    System.out.println("0 - Finalizar compra");
                                    System.out.println();

                                    categoriaEscolhidaIndex = getMenuChoiceWithIndex(scan, 0, 8);

                                    if (categoriaEscolhidaIndex == 0) {
                                        if (produtosEscolhicos.isEmpty()) {
                                            System.out.println(
                                                    "Você não tem produtos no carrinho. Deseja cancelar a compra?");
                                            if (confirmar(scan)) {
                                                return;
                                            } else {
                                                continue;
                                            }
                                        } else {
                                            break;
                                        }
                                    }

                                    Categoria categoriaEscolhida = Categoria.values()[categoriaEscolhidaIndex];

                                    ArrayList<Produto> produtosCategoria = farmacia
                                            .getProductosPorCategoria(categoriaEscolhida);

                                    do {
                                        int produtoIndex = 1;
                                        for (Produto produto : produtosCategoria) {
                                            System.out.println(produtoIndex + " - " + produto);
                                            produtoIndex++;
                                        }

                                        System.out.println(
                                                "Selecione todos os produtos - Pressione 0 para voltar ao menu de categorias.");

                                        produtoEscolhidoIndex = getMenuChoiceWithIndex(scan, 0,
                                                produtosCategoria.size());

                                        if (produtoEscolhidoIndex != 0) {
                                            produtosEscolhicos.add(produtosCategoria.get(produtoEscolhidoIndex));
                                            System.out.println("Produto adicionado ao carrinho!");
                                            break;
                                        }
                                    } while (produtoEscolhidoIndex != 0);
                                } while (categoriaEscolhidaIndex != 0);

                                int ultimaVenda = farmacia.getVendas().size();
                                Vendas venda = new Vendas(ultimaVenda + 1, LocalDate.now(),
                                        farmacia.getClientes().get(clientIndex), produtosEscolhicos);
                                farmacia.getClientes().get(clientIndex).addVendaToHistorico(venda);
                                farmacia.insereVenda(venda);

                                printReceipt(clientIndex, ultimaVenda);

                                farmacia.updateStock(produtosEscolhicos);

                            } else {
                                System.out.println("Cliente nao existe. Deseja criar?");

                                if (confirmar(scan)) {
                                    scan.nextLine();
                                    registarCliente(scan, farmacia);
                                } else {
                                    break;
                                }
                            }
                            break;
                        case 2:
                            Produto produto = registarProduto(scan, farmacia);
                            if (produto != null) {
                                farmacia.insereProduto(produto);
                                System.out.println("Produto: " + produto.getNome() + " adicionado com sucesso.");
                            } else {
                                System.out.println("Registro de produto cancelado.");
                            }
                            break;
                        case 3:
                            registarCliente(scan, farmacia);
                            break;

                        case 4:
                            System.out.println(" \n Lista de produtos: ");
                            System.out.println("    1 - Listar todos");
                            System.out.println("    2 - Listar por categorias");
                            System.out.println("    3 - Listar produtos indisponiveis");
                            System.out.println("    0 - Voltar");

                            int listP = getMenuChoice(scan, 0, 3);

                            switch (listP) {
                                case 1:
                                    for (Produto listProduto : farmacia.getProdutos()) {
                                        System.out.println("    " + listProduto.getNome() + " | " + "Stock: "
                                                + listProduto.getStock());
                                    }
                                    break;
                                case 2:
                                    farmacia.mostrarCategorias();

                                    int categoriaEs = getMenuChoiceWithIndex(scan, 1, 8);

                                    Categoria catEs = Categoria.values()[categoriaEs];

                                    System.out.println();
                                    System.out.println(
                                            "Produtos da categoria: " + Categoria.values()[categoriaEs].getDescricao());
                                    ArrayList<Produto> produtosCategoria = farmacia.getProductosPorCategoria(catEs);
                                    for (Produto listProduto : produtosCategoria) {
                                        System.out.println("    " + listProduto.getNome() + " | " + "Stock: "
                                                + listProduto.getStock());
                                    }

                                    break;
                                case 3:
                                    for (Produto produtoIndisponivel : farmacia.getProdutosIndisponiveis()) {
                                        System.out.println("    " + produtoIndisponivel.getNome());
                                    }
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println(INVALID_OPTION);
                            }
                            break;
                        case 5:
                            farmacia.listarClientes();
                            System.out.println("Digite 0 para voltar!");
                            int escolhaCliente = getMenuChoiceWithIndex(scan, 0, farmacia.getClientes().size());

                            if (escolhaCliente == -1) {
                                break;
                            }

                            farmacia.mostrarInformacaoCliente(escolhaCliente);

                            break;
                        case 6:
                            System.out.println(" \n Alterar produtos: ");
                            System.out.println("    1 - Alterar");
                            System.out.println("    2 - Remover");
                            System.out.println("    3 - OutOfStock");
                            System.out.println("    0 - Voltar");

                            int prodAlterar = getMenuChoice(scan, 0, 3);

                            switch (prodAlterar) {
                                case 1:
                                    alterarProduto(farmacia, scan);
                                    break;
                                case 2:
                                    System.out.println("-- Remover produto --");
                                    farmacia.listarProdutos();

                                    System.out.println(" \n Digite 0 para voltar!");

                                    int listaChoice2 = getMenuChoiceWithIndex(scan, 0, farmacia.getProdutos().size());

                                    if (listaChoice2 == -1) {
                                        break;
                                    }

                                    farmacia.removeProduto(farmacia.getProdutos().get(listaChoice2));

                                    break;
                                case 3:
                                    System.out.println("-- Reativar produto --");

                                    farmacia.listarProdutosIndisponiveis();

                                    System.out.println(" \n Digite 0 para voltar!");

                                    int listaIndsChoice2 = getMenuChoiceWithIndex(scan, 0,
                                            farmacia.getClientesIndisponiveis().size());

                                    if (listaIndsChoice2 == -1) {
                                        break;
                                    }

                                    System.out.println();
                                    System.out.print("  Stock: ");
                                    int newStock = scan.nextInt();
                                    farmacia.getProdutosIndisponiveis().get(listaIndsChoice2).setStock(newStock);
                                    farmacia.insereProduto(farmacia.getProdutosIndisponiveis().get(listaIndsChoice2));
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println(INVALID_OPTION);
                            }
                            break;
                        case 7:
                            System.out.println(" \n Alterar Cliente: ");

                            System.out.println("    1 - Alterar");
                            System.out.println("    2 - Remover");
                            System.out.println("    3 - Clientes Inativos");
                            System.out.println("    0 - Voltar");

                            int clientAlterar = getMenuChoice(scan, 0, 3);

                            switch (clientAlterar) {
                                case 1:
                                    alterarCliente(farmacia, scan);
                                    break;

                                case 2:
                                    System.out.println("-- Remover cliente --");
                                    farmacia.listarClientes();

                                    System.out.println("Digite 0 para voltar!");

                                    int listaClienteChoiceRemove = getMenuChoiceWithIndex(scan, 0,
                                            farmacia.getClientes().size());

                                    if (listaClienteChoiceRemove == -1) {
                                        break;
                                    }

                                    farmacia.removeCliente(farmacia.getClientes().get(listaClienteChoiceRemove));
                                    break;

                                case 3:
                                    System.out.println("-- Reativar cliente --");
                                    farmacia.listarClientesIndisponiveis();

                                    System.out.println("Digite 0 para voltar!");

                                    int listaClienteChoiceReativar = getMenuChoiceWithIndex(scan, 0,
                                            farmacia.getClientesIndisponiveis().size());

                                    if (listaClienteChoiceReativar == -1) {
                                        break;
                                    }

                                    farmacia.insereCliente(
                                            farmacia.getClientesIndisponiveis().get(listaClienteChoiceReativar));
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println(INVALID_OPTION);
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println(INVALID_OPTION);
                    }
                    break;

                case 2:

                    System.out.println("\n- - Menu de Estatísticas - -");
                    System.out.println("1 - Lista de vendas");
                    System.out.println("2 - Número total de vendas");
                    System.out.println("3 - A maior venda");
                    System.out.println("0 - Voltar");
                    System.out.print("Insira a sua opção: ");

                    int opcao3 = scan.nextInt();

                    switch (opcao3) {
                        case 1:
                            System.out.println(" \n Lista de Vendas ");
                            for (Vendas v : farmacia.getVendas()) {
                                System.out.println(v);
                            }
                            break;
                        case 2:
                            System.out.println(" \n Numero total de vendas: ");
                            double totalVenda = 0;
                            for (Vendas totalVendas : farmacia.getVendas()) {
                                totalVenda += totalVendas.getTotal();
                            }

                            int totalNvendas = farmacia.getVendas().size();

                            System.out.println("    " + "Nº Total de vendas: " + totalNvendas + " | " + "Faturamento: "
                                    + numberFormat.format(totalVenda));
                            break;
                        case 3:
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println(INVALID_OPTION);
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println(INVALID_OPTION);
            }
        } while (opcao != 0);

        scan.close();
    }

    public static int getAdminMenuChoice(Scanner scan) {
        int opcao;

        System.out.println(" \n - - Menu de Administração - - ");
        System.out.println(" 1 - Registar Venda");
        System.out.println(" 2 - Registar Produto");
        System.out.println(" 3 - Registar Cliente");
        System.out.println(" 4 - Listar Produtos");
        System.out.println(" 5 - Listar Clientes");
        System.out.println(" 6 - Alterar Produtos");
        System.out.println(" 7 - Alterar Clientes");
        System.out.println(" 0 - Voltar");

        opcao = getMenuChoice(scan, 0, 7);
        return opcao;
    }

    public static int getMainMenuChoice(Scanner scan) {
        System.out.println(" \n - - - Menu Principal - - - ");
        System.out.println(" 1 - Menu de Administração");
        System.out.println(" 2 - Menu Estatísticas");
        System.out.println(" 0 - Fechar");

        int opcao = getMenuChoice(scan, 0, 2);
        return opcao;
    }

    private static int getMenuChoice(Scanner scan, int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("Escolha: ");
                choice = scan.nextInt();
                scan.nextLine();
                if (choice < min || choice > max) {
                    System.out.println("Escolha fora do intervalo. Tente novamente.");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println(INVALID_OPTION);
                scan.nextLine();
            }
        }
    }

    public static int getMenuChoiceWithIndex(Scanner scan, int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("Escolha: ");
                choice = scan.nextInt();
                scan.nextLine();
                if (choice < min || choice > max) {
                    System.out.println("Escolha fora do intervalo. Tente novamente.");
                } else {
                    return choice - 1;
                }
            } catch (InputMismatchException e) {
                System.out.println(INVALID_OPTION);
                scan.nextLine();
            }
        }
    }

    public static boolean confirmar(Scanner scan) {
        String confirm;
        do {
            System.out.print("Confirmar (Y/N): ");
            confirm = scan.next().toLowerCase();
            if (!confirm.equals("y") && !confirm.equals("n")) {
                System.out.println("Entrada inválida. Por favor, insira Y para confirmar ou N para cancelar.");
            }
        } while (!confirm.equals("y") && !confirm.equals("n"));

        return confirm.equals("y");
    }

    public static Cliente registarCliente(Scanner scan, Farmacia farmacia) {
        System.out.println("\nRegistar Cliente");
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scan.nextLine();

        System.out.print("NIF: ");
        int nifCliente = scan.nextInt();
        scan.nextLine();

        System.out.print("Morada do Cliente: ");
        String localCliente = scan.nextLine();

        System.out.print("Localidade do Cliente: ");
        String localidadeCliente = scan.nextLine();

        Cliente novoCliente = new Cliente(nomeCliente, nifCliente,
                new Morada(localCliente, localidadeCliente), new ArrayList<>());
        farmacia.insereCliente(novoCliente);

        System.out.println("Cliente registado com sucesso! \n");

        return novoCliente;
    }

    public static Produto registarProduto(Scanner scan, Farmacia farmacia) {
        System.out.println("Registar Produto: \n");

        farmacia.mostrarCategorias();
        System.out.println("0 - Voltar");

        int escolha = getMenuChoiceWithIndex(scan, 0, 8);

        if (escolha == -1) {
            return null;
        } else if (escolha >= Categoria.values().length) {
            System.out.println("Categoria nao existente!");
            return null;
        } else {
            Categoria categoriaEscolhida = Categoria.values()[escolha];

            System.out.print("Nome: ");
            String nomeMedi = scan.nextLine();
            System.out.print("Descrição: ");
            String descricaoMedi = scan.nextLine();
            System.out.print("Stock: ");
            int stock = scan.nextInt();
            scan.nextLine();
            System.out.print("Preço: ");
            double precoMedi = scan.nextDouble();
            System.out.print("Iva: ");
            int ivaMedi = scan.nextInt();
            scan.nextLine();
            System.out.print("Validade (yyyy-mm-dd): ");
            String validadeMedi = scan.nextLine();
            LocalDate validade = LocalDate.parse(validadeMedi);

            Produto produto = new Produto(categoriaEscolhida, nomeMedi, descricaoMedi, stock,
                    precoMedi, ivaMedi, validade);

            return produto;
        }
    }

    public static void alterarProduto(Farmacia farmacia, Scanner scan) {
        System.out.println("-- Alterar produto --");

        farmacia.listarProdutos();

        System.out.println("Digite 0 para voltar!");

        int listaChoice = getMenuChoiceWithIndex(scan, 0, farmacia.getProdutos().size());

        if (listaChoice == -1) {
            return;
        }
        System.out.println();
        System.out.println(farmacia.getProdutos().get(listaChoice));

        System.out.println("Produto: " + farmacia.getProdutos().get(listaChoice).getNome());
        System.out.println("  1 - Nome");
        System.out.println("  2 - Descrição");
        System.out.println("  3 - Stock");
        System.out.println("  4 - Preço");
        System.out.println("  5 - Iva");
        System.out.println("  6 - Validade");
        System.out.println("  0 - Voltar");

        int changeChoice = getMenuChoice(scan, 0, 6);

        switch (changeChoice) {
            case 1:
                System.out.print("Nome: ");
                String nome = scan.nextLine();
                farmacia.getProdutos().get(listaChoice).setNome(nome);
                break;
            case 2:
                System.out.print("Descrição: ");
                String descricao = scan.nextLine();
                farmacia.getProdutos().get(listaChoice).setDescricao(descricao);
                break;
            case 3:
                System.out.print("Stock: ");
                int stock = scan.nextInt();
                farmacia.getProdutos().get(listaChoice).setStock(stock);
                break;
            case 4:
                System.out.print("Preço: ");
                double preco = scan.nextDouble();
                farmacia.getProdutos().get(listaChoice).setPreco(preco);
                break;
            case 5:
                System.out.print("Iva: ");
                int iva = scan.nextInt();
                farmacia.getProdutos().get(listaChoice).setIva(iva);
                break;
            case 6:
                System.out.print("Validade: ");
                String validadeMedi = scan.nextLine();
                try {
                    LocalDate validade = LocalDate.parse(validadeMedi);
                    farmacia.getProdutos().get(listaChoice).setValidade(validade);
                } catch (DateTimeParseException e) {
                    System.out.println(
                            "Formato errado. Tente novamente com: yyyy-mm-dd.");
                }
                break;
            case 0:
                break;

            default:
                System.out.println(INVALID_OPTION);
        }
    }

    public static void alterarCliente(Farmacia farmacia, Scanner scan) {
        System.out.println("-- Alterar cliente --");
        farmacia.listarClientes();

        System.out.println("Digite 0 para voltar!");

        int listaClienteChoice = getMenuChoiceWithIndex(scan, 0, farmacia.getClientes().size());

        if (listaClienteChoice == -1) {
            return;
        }
        System.out.println();
        System.out.println(farmacia.getClientes().get(listaClienteChoice));

        System.out.println("Cliente: " + farmacia.getClientes().get(listaClienteChoice).getNome());
        System.out.println("    1 - Nome");
        System.out.println("    2 - NIF");
        System.out.println("    3 - Morada");
        System.out.println("    4 - Vendas");
        System.out.println("    0 - Voltar");

        int changeClienteChoice = getMenuChoice(scan, 0, 4);

        switch (changeClienteChoice) {
            case 1:
                System.out.print("Nome: ");
                String nomeClienteSet = scan.nextLine();
                farmacia.getClientes().get(listaClienteChoice).setNome(nomeClienteSet);
                break;

            case 2:
                System.out.print("NIF: ");
                int nifClienteSet = scan.nextInt();
                farmacia.getClientes().get(listaClienteChoice).setNif(nifClienteSet);
                break;

            case 3:
                System.out.print("Rua: ");
                String localClienteSet = scan.nextLine();
                System.out.print("Localidade: ");
                String localidadeClienteSet = scan.nextLine();
                farmacia.getClientes().get(listaClienteChoice)
                        .setMorada(new Morada(localClienteSet, localidadeClienteSet));
                break;

            case 4:
                int comprasCounter = 1;
                for (Vendas comprasClientes : farmacia.getClientes().get(listaClienteChoice)
                        .getHistorico()) {
                    System.out.println("    " + comprasCounter + " - "
                            + comprasClientes.getnumVenda() + " | "
                            + comprasClientes.getDate() + " | "
                            + comprasClientes.getTotal() + "\n");
                    comprasCounter++;
                }

                System.out.println("Digite 0 para voltar!");

                int listaComprasChoice = getMenuChoiceWithIndex(scan, 0, farmacia.getClientes().get(listaClienteChoice)
                        .getHistorico().size());

                System.out.println();
                if (confirmar(scan)) {
                    farmacia.getClientes().get(listaClienteChoice).getHistorico()
                            .remove(listaComprasChoice);
                } else {
                    break;
                }

                break;

            case 0:
                break;

            default:
                System.out.println(INVALID_OPTION);
        }
    }

    public static void printReceipt(int clientIndex, int ultimaVenda) {
        System.out.println("-------- Compra Finalizada! --------");
        System.out.println("NIF: " + farmacia.getClientes().get(clientIndex).getNif());
        System.out.println("Produtos comprados:");
        for (Produto produtoVendido : farmacia.getVendas().get(ultimaVenda).getProduto()) {
            System.out.println(produtoVendido.getNome());
        }
        System.out.println("Total: " + numberFormat.format(farmacia.getVendas().get(ultimaVenda).getTotal()));
    }

}