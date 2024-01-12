import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    static NumberFormat numberFormat = Utils.getNumberFormat();

    static Farmacia farmacia = new Farmacia();

    public static void printReceipt(int clientIndex, int ultimaVenda) {
        System.out.println("-------- Compra Finalizada! --------");
        System.out.println("NIF: " + farmacia.getClientes().get(clientIndex).getNif());
        System.out.println("Produtos comprados:");
        for (Produto produtoVendido : farmacia.getVendas().get(ultimaVenda).getProduto()) {
            System.out.println(produtoVendido.getNome());
        }
        System.out.println("Total: " + numberFormat.format(farmacia.getVendas().get(ultimaVenda).getTotal()));
    }

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
        int opcao = -1;

        do {
            do {
                try {
                    System.out.println(" \n - - - Menu Principal - - - ");
                    System.out.println(" 1 - Menu de Administração");
                    System.out.println(" 2 - Menu Estatísticas");
                    System.out.println(" 0 - Fechar");

                    System.out.print("Opção: ");
                    opcao = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalido! Digite um numero.");
                    scan.nextLine();
                }
            } while (opcao < 0 || opcao > 2);

            switch (opcao) {
                case 1:
                    System.out.println(" \n - - Menu de Administração - - ");
                    System.out.println(" 1 - Registar Venda");
                    System.out.println(" 2 - Registar Produto");
                    System.out.println(" 3 - Registar Cliente");
                    System.out.println(" 4 - Listar Produtos");
                    System.out.println(" 5 - Listar Clientes");
                    System.out.println(" 6 - Alterar Produtos");
                    System.out.println(" 7 - Alterar Clientes");
                    System.out.println(" 8 - Voltar");

                    int opcao2 = -1;
                    do {
                        try {
                            System.out.print("Insira a sua opção: ");
                            opcao2 = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalido! Digite um numero.");
                            scan.nextLine();
                        }
                    } while (opcao2 < 1 || opcao2 > 8);

                    switch (opcao2) {
                        case 1:
                            System.out.println("\n1 - Procurar Cliente por:");
                            System.out.println("   1 - Nome");
                            System.out.println("   2 - NIF");
                            System.out.print("Escolha a opção: ");
                            int o = -1;

                            try {
                                o = scan.nextInt();
                                scan.nextLine();
                            } catch (Exception e) {
                                System.out.println("Invalido! Digite um numero.");
                                scan.nextLine();
                            }

                            System.out.println();
                            boolean clienteExist = false;
                            int clientIndex = -1;

                            if (o == 1) {
                                System.out.print("Nome do Cliente: ");
                                String name = scan.nextLine();
                                ArrayList<Cliente> matchingClients = new ArrayList<>();
                                System.out.println();
                                for (Cliente cliente : farmacia.getClientes()) {
                                    if (cliente.getNome().toLowerCase().contains(name.toLowerCase())) {
                                        matchingClients.add(cliente);
                                    }
                                }
                                if (matchingClients.isEmpty()) {
                                    System.out.println("Não existem clientes com esse nome: " + name);
                                } else {
                                    for (int i = 0; i < matchingClients.size(); i++) {
                                        System.out.println(
                                                "   " + (i + 1) + " - " + matchingClients.get(i).getNome() + " - "
                                                        + matchingClients.get(i).getNif());
                                    }
                                    System.out.println();
                                    System.out.print("Qual Cliente: ");

                                    try {
                                        clientIndex = scan.nextInt() - 1;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalido! Digite um numero.");
                                        scan.nextLine();
                                    }

                                    Cliente selectedClient = matchingClients.get(clientIndex);
                                    System.out.println();
                                    System.out.println("Selecionou: " + selectedClient);
                                    clienteExist = true;
                                }
                            } else if (o == 2) {
                                System.out.print("NIF do Cliente: ");
                                int NIF = -1;

                                try {
                                    NIF = scan.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalido! Digite um numero.");
                                    scan.nextLine();
                                }

                                for (Cliente cliente : farmacia.getClientes()) {
                                    if (cliente.getNif() == NIF) {
                                        System.out.println(cliente);
                                        clienteExist = true;
                                        clientIndex = farmacia.getClientes().indexOf(cliente);
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("Opção INVALIDA!!!");
                            }

                            if (clienteExist) {
                                ArrayList<Produto> produtosEscolhicos = new ArrayList<>();
                                int categoriaEscolhidaIndex = -1;
                                int produtoEscolhidoIndex = -1;

                                do {
                                    System.out.println();
                                    int categoriaIndex = 1;
                                    for (Categoria categoria : Categoria.values()) {
                                        System.out.println(categoriaIndex + " - " + categoria.getDescricao());
                                        categoriaIndex++;
                                    }
                                    System.out.println("0 - Finalizar compra");
                                    System.out.println();
                                    System.out.print("Categoria: ");

                                    try {
                                        categoriaEscolhidaIndex = scan.nextInt();
                                        scan.nextLine();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalido! Digite um numero.");
                                        scan.nextLine();
                                    }

                                    if (categoriaEscolhidaIndex == 0) {
                                        System.out.print("Tem certeza que deseja finalizar a compra? (Y/N): ");
                                        char confirm = scan.next().charAt(0);
                                        if (confirm == 'Y' || confirm == 'y') {
                                            break;
                                        } else {
                                            continue;
                                        }
                                    }

                                    Categoria categoriaEscolhida = Categoria.values()[categoriaEscolhidaIndex - 1];

                                    ArrayList<Produto> produtosCategoria = new ArrayList<>();
                                    for (Produto produto : farmacia.getProdutos()) {
                                        if (produto.getCategoria() == categoriaEscolhida) {
                                            produtosCategoria.add(produto);
                                        }
                                    }

                                    do {
                                        int produtoIndex = 1;
                                        for (Produto produto : produtosCategoria) {
                                            System.out.println(produtoIndex + " - " + produto);
                                            produtoIndex++;
                                        }

                                        System.out.println(
                                                "Selecione todos os produtos - Pressione 0 para voltar ao menu de categorias.");
                                        System.out.print("Produto: ");

                                        try {
                                            produtoEscolhidoIndex = scan.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalido! Digite um numero.");
                                            scan.nextLine();
                                        }

                                        if (produtoEscolhidoIndex != 0) {
                                            produtosEscolhicos.add(produtosCategoria.get(produtoEscolhidoIndex - 1));
                                            System.out.println("Produto adicionado ao carrinho!");
                                            break;
                                        }
                                    } while (produtoEscolhidoIndex != 0);
                                } while (categoriaEscolhidaIndex != 0);

                                int ultimaVenda = farmacia.getVendas().size();
                                Vendas venda = new Vendas(ultimaVenda + 1,
                                        LocalDate.now(),
                                        farmacia.getClientes().get(clientIndex),
                                        produtosEscolhicos);
                                farmacia.getClientes().get(clientIndex).addVendaToHistorico(venda);
                                farmacia.insereVenda(venda);

                                printReceipt(clientIndex, ultimaVenda);

                                ArrayList<Produto> produtosRegistados = farmacia.getProdutos();
                                for (Produto pEscolhido : produtosEscolhicos) {
                                    for (Produto pRegistado : produtosRegistados) {
                                        if (pEscolhido.equals(pRegistado)) {
                                            pRegistado.setStock(pRegistado.getStock() - 1);
                                            break;
                                        }
                                    }
                                }

                                farmacia.setProdutos(produtosRegistados);

                            } else {
                                System.out.println("Cliente nao existe!!!.");
                            }
                            break;
                        case 2:
                            System.out.println("Registar Produto: \n");

                            for (Categoria categoria : Categoria.values()) {
                                System.out.println((categoria.ordinal() + 1) + " - " + categoria.getDescricao());
                            }

                            int escolha = -1;
                            try {
                                System.out.print("Escolha a categoria:");
                                escolha = scan.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalido! Insira um valor correto!.");
                                scan.nextLine();
                            }

                            scan.nextLine();

                            if (escolha < 1 || escolha > Categoria.values().length) {
                                System.out.println("Categoria nao existente!");
                            } else {
                                Categoria categoriaEscolhida = Categoria.values()[escolha - 1];

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

                                farmacia.insereProduto(produto);

                                System.out.println("Produto: " + nomeMedi + " adicionado com sucesso!!!");
                            }
                            break;
                        case 3:
                            System.out.println("Registar Cliente\n");

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
                            break;

                        case 4:
                            System.out.println(" \n Lista de produtos: ");
                            System.out.println("    1 - Listar todos");
                            System.out.println("    2 - Listar por categorias");
                            System.out.println("    3 - Listar produtos indisponiveis");
                            System.out.println("    4 - Voltar");

                            int listP = -1;

                            try {
                                System.out.print("Escolha: ");
                                listP = scan.nextInt();
                                scan.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalido! Digite um numero.");
                                scan.nextLine();
                            }

                            switch (listP) {
                                case 1:
                                    for (Produto listProduto : farmacia.getProdutos()) {
                                        System.out.println("    " + listProduto.getNome() + " | " + "Stock: "
                                                + listProduto.getStock());
                                    }
                                    break;
                                case 2:
                                    for (Categoria categorias : Categoria.values()) {
                                        System.out.println(
                                                (categorias.ordinal() + 1) + " - " + categorias.getDescricao());
                                    }

                                    int categoriaEs = -1;

                                    try {
                                        System.out.print("Qual categoria: ");
                                        categoriaEs = scan.nextInt() - 1;
                                        scan.nextLine();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalido. Tente novamente");
                                        scan.nextLine();
                                    }

                                    Categoria catEs = Categoria.values()[categoriaEs];

                                    System.out.println();
                                    System.out.println(
                                            "Produtos da categoria: " + Categoria.values()[categoriaEs].getDescricao());
                                    for (Produto listProduto : farmacia.getProdutos()) {
                                        if (listProduto.getCategoria() == catEs) {
                                            System.out.println("    " + listProduto.getNome() + " | " + "Stock: "
                                                    + listProduto.getStock());
                                        }
                                    }

                                    break;
                                case 3:
                                    for (Produto produtoIndisponivel : farmacia.getProdutosIndisponiveis()) {
                                        System.out.println("    " + produtoIndisponivel.getNome());
                                    }
                                    break;

                                case 4:
                                    break;

                                default:
                                    System.out.println("Opção Invalida");
                                    break;
                            }
                            break;
                        case 5:
                            System.out.println(" \n Listar Clientes: ");
                            int listaClientesIndex = 1;
                            for (Cliente listaClientes : farmacia.getClientes()) {
                                System.out.println("    " + listaClientesIndex + " - " + listaClientes.getNome() + " | "
                                        + listaClientes.getNif());
                                listaClientesIndex++;
                            }
                            int escolhaCliente = -1;

                            try {
                                System.out.print(
                                        "Escolha cliente para ver mais informaçao ou pressione 0 para voltar!");
                                escolhaCliente = scan.nextInt() - 1;
                                scan.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalido. Digite um numero.");
                                scan.nextLine();
                            }

                            if (escolhaCliente == -1) {
                                continue;
                            }

                            System.out.println("Informação do cliente: ");
                            Cliente clienteInfo = farmacia.getClientes().get(escolhaCliente);

                            System.out.println(clienteInfo);

                            break;
                        case 6:
                            System.out.println(" \n Alterar produtos: ");
                            System.out.println("    1 - Alterar");
                            System.out.println("    2 - Remover");
                            System.out.println("    3 - Voltar");

                            int prodAlterar = -1;

                            try {
                                System.out.print("Qual categoria: ");
                                prodAlterar = scan.nextInt();
                                scan.nextLine();
                            } catch (Exception e) {
                                System.out.println("Invalido. Tente novamente");
                                scan.nextLine();
                            }

                            switch (prodAlterar) {
                                case 1:
                                    System.out.println("-- Alterar produto --");
                                    int listaProdutosIndex = 1;
                                    for (Produto listProduto : farmacia.getProdutos()) {
                                        System.out.println("    " + listaProdutosIndex + " - " + listProduto.getNome());
                                        listaProdutosIndex++;
                                    }

                                    System.out.println("Digite 0 para voltar!");

                                    int listaChoice = -1;

                                    try {
                                        System.out.print("Qual Produto: ");
                                        listaChoice = scan.nextInt() - 1;
                                        scan.nextLine();
                                        if (listaChoice == -1) {
                                            break;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalido. Tente novamente");
                                        scan.nextLine();
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
                                    System.out.println(" 7 - Voltar");

                                    int changeChoice = -1;

                                    try {
                                        System.out.print("Oque deseja alterar: ");
                                        changeChoice = scan.nextInt();
                                        scan.nextLine();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalido. Tente novamente");
                                        scan.nextLine();
                                    }

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
                                        case 7:
                                            break;

                                        default:
                                            System.out.println("Escolha Invalida!");
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("-- Remover produto --");
                                    int listaProdutosIndex2 = 1;
                                    for (Produto listProduto : farmacia.getProdutos()) {
                                        System.out
                                                .println("    " + listaProdutosIndex2 + " - " + listProduto.getNome());
                                        listaProdutosIndex2++;
                                    }

                                    System.out.println(" \n Digite 0 para voltar!");

                                    int listaChoice2 = -1;

                                    try {
                                        System.out.print("Qual Produto: ");
                                        listaChoice2 = scan.nextInt() - 1;
                                        scan.nextLine();
                                        if (listaChoice2 == -1) {
                                            break;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalido. Tente novamente");
                                        scan.nextLine();
                                    }

                                    farmacia.removeProduto(farmacia.getProdutos().get(listaChoice2));

                                    break;
                                case 3:
                                    break;

                                default:
                                    System.out.println("Invalido. Digite um numero!");
                                    break;
                            }
                            break;
                        case 7:
                            System.out.println(" \n Resultado para Apagar Cliente: ");
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        default:
                            System.out.println("Opção INVALIDA!!!");
                    }
                    break;

                case 2:

                    System.out.println("\n- - Menu de Estatísticas - -");
                    System.out.println("1 - Lista de vendas");
                    System.out.println("2 - Número total de vendas");
                    System.out.println("3 - A maior venda");
                    System.out.println("4 - Voltar");
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
                            System.out.println(" \n Resultado da maior venda: ");
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opção INVALIDA!!!");

                    }
                    break;

                default:
                    System.out.println("Opção INVALIDA!!!");
                    break;
            }
        } while (opcao != 0);

        scan.close();
    }
}