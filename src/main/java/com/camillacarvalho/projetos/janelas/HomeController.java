package com.camillacarvalho.projetos.janelas;

import com.camillacarvalho.projetos.entidades.Cliente;
import com.camillacarvalho.projetos.entidades.Fornecedor;
import com.camillacarvalho.projetos.entidades.Produto;
import com.camillacarvalho.projetos.entidades.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Camilla Carvalho, Fatec Botucatu 2022
 */
public class HomeController extends javax.swing.JFrame {
    /**
     * CARREGAR os OBJs das TELAS DE APRESENTAÇÃO em VARIÁVEIS para controlarmos seus estados
     */
    //private Home home = new Home();
    private CadastroLogin cadastroLogin = null;
    private CadastroUsuarios cadastroUsuarios = null;
    private CadastroClientes cadastroClientes = null;
    private CadastroFonecedores cadastroFonecedores = null;
    private CadastroProdutos cadastroProdutos = null;

    public static java.util.List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    public static java.util.List<Cliente> listaClientes = new ArrayList<Cliente>();
    public static java.util.List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
    public static java.util.List<Produto> listaProdutos = new ArrayList<Produto>();

    private static String mensagemTela = "Seja muito bem-vinda(o)!";
    public boolean loginIsAdmin = false;

    /**
     * Criar a Home
     */
    public HomeController() {
        cadastroLogin = new CadastroLogin();
        initComponents();
        trocarTela("login");

        // usuarios
        HomeController.carregarDadosUsuarios();
        cadastroUsuarios = new CadastroUsuarios();

        // clientes
        HomeController.carregarDadosClientes();
        cadastroClientes = new CadastroClientes();

        // fornecedores
        HomeController.carregarDadosFornecedores();
        cadastroFonecedores = new CadastroFonecedores();

        // produtos
        HomeController.carregarDadosProdutos();
        cadastroProdutos = new CadastroProdutos();
    }

    /**
     * @return the listaUsuarios
     */
    public static java.util.List<Usuario> getListaUsuarios() {
        return new ArrayList<Usuario>(listaUsuarios);
    }

    /**
     * @return the listaClientes
     */
    public static java.util.List<Cliente> getListaClientes() {
        return new ArrayList<Cliente>(listaClientes);
    }

    /**
     * @param _listaUsuarios
     */
    public static void setListaUsuarios(java.util.List<Usuario> _listaUsuarios) {
        listaUsuarios = _listaUsuarios;
    }

    /**
     * @param _listaClientes
     */
    public static void setListaClientes(java.util.List<Cliente> _listaClientes) {
        listaClientes = _listaClientes;
    }

    /**
     * @param _listaFornecedores
     */
    public static void setListaFornecedores(java.util.List<Fornecedor> _listaFornecedores) {
        listaFornecedores = _listaFornecedores;
    }

    public static void setListaProdutos(List<Produto> _listaProdutos) {
        listaProdutos = _listaProdutos;
    }

    /**
     * @param _usuario Novo usuário a ser adicionado na lista
     */
    public static void addUsuario2Lista(Usuario _usuario) {
        int ii = maiorIdUsuarioLista();
        _usuario.setId(ii);
        listaUsuarios.add(_usuario);
        /// atualizar
        HomeController.salvarDadosUsuarios();
        HomeController.carregarDadosUsuarios();
    }

    /**
     * @param _produto Novo produto a ser adicionado na lista
     */
    public static void addProduto2Lista(Produto _produto) {
        int ii = maiorIdProdutoLista();
        _produto.setId(ii);
        listaProdutos.add(_produto);
        /// atualizar
        HomeController.salvarDadosProdutos();
        HomeController.carregarDadosProdutos();
    }

    /**
     * @param _fornecedor Novo fornecedor a ser adicionado na lista
     */
    public static void addFornecedor2Lista(Fornecedor _fornecedor) {
        int ii = maiorIdFornecedorLista();
        _fornecedor.setId(ii);
        listaFornecedores.add(_fornecedor);
        /// atualizar
        HomeController.salvarDadosFornecedores();
        HomeController.carregarDadosFornecedores();
    }

    /**
     * @param _cliente Novo cliente a ser adicionado na lista
     */
    public static void addCliente2Lista(Cliente _cliente) {
        int ii = maiorIdClienteLista();
        _cliente.setId(ii);
        listaClientes.add(_cliente);
        /// atualizar
        HomeController.salvarDadosClientes();
        HomeController.carregarDadosClientes();
    }

    private static void carregarDadosClientes() {
        print("carregarDadosClientes()");
        File file = new File("projetoestoque.clientes.persistencia.db");

        ObjectInputStream in = null;
        java.util.List<Cliente> listaClientesTmp = null;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            listaClientesTmp = (ArrayList<Cliente>) in.readObject();
            //listaUsuario = listaClientesTmp;
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        if (listaClientesTmp == null) {
            listaClientesTmp = new ArrayList<Cliente>();
        }

        print("carregarDadosClientes() size: " + listaClientesTmp.size());

        setListaClientes(listaClientesTmp);
    }

    private static void carregarDadosFornecedores() {
        print("carregarDadosFornecedores()");
        File file = new File("projetoestoque.fornecedores.persistencia.db");

        ObjectInputStream in = null;
        java.util.List<Fornecedor> listaFornecedoresTmp = null;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            listaFornecedoresTmp = (ArrayList<Fornecedor>) in.readObject();
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        if (listaFornecedoresTmp == null) {
            listaFornecedoresTmp = new ArrayList<Fornecedor>();
        }

        print("carregarDadosFornecedores() size: " + listaFornecedoresTmp.size());

        setListaFornecedores(listaFornecedoresTmp);
    }

    private static int maiorIdUsuarioLista() {
        // próximo Id
        int ii = 0;
        for (Usuario usuarioFromLista : listaUsuarios) {
            if (ii <= usuarioFromLista.getId()) {
                ii = usuarioFromLista.getId() + 1;
            }
        }
        if (ii == 0) {
            ii++;
        }
        return ii;
    }

    private static int maiorIdClienteLista() {
        // próximo Id
        int ii = 0;
        for (Cliente clienteFromLista : listaClientes) {
            if (ii <= clienteFromLista.getId()) {
                ii = clienteFromLista.getId() + 1;
            }
        }
        if (ii == 0) {
            ii++;
        }
        return ii;
    }

    private static int maiorIdFornecedorLista() {
        // próximo Id
        int ii = 0;
        for (Fornecedor fornecedorFromLista : listaFornecedores) {
            if (ii <= fornecedorFromLista.getId()) {
                ii = fornecedorFromLista.getId() + 1;
            }
        }
        if (ii == 0) {
            ii++;
        }
        return ii;
    }

    /**
     *
     * @param _usuario
     */
    public static void updateUsuario2Lista(Usuario _usuario) {
        int i = 0;

        for (Usuario usuarioFromLista : listaUsuarios) {

            if (usuarioFromLista.getLogin().equals(_usuario.getLogin())) {
                listaUsuarios.remove(i);

                if (_usuario.getId() == 0) {
                    _usuario.setId(maiorIdUsuarioLista());
                }

                //_usuario.setId(ii);
                listaUsuarios.add(_usuario);
            }
            i++;
        }
        /// atualizar
        HomeController.salvarDadosUsuarios();
        HomeController.carregarDadosUsuarios();
        HomeController.carregarTabelaUsuarios();
    }

    /**
     *
     * @param _cliente
     */
    public static void updateCliente2Lista(Cliente _cliente) {
        int i = 0;

        for (Cliente clienteFromLista : listaClientes) {

            if (clienteFromLista.getUsuario().equals(_cliente.getUsuario())) {
                listaUsuarios.remove(i);

                if (_cliente.getId() == 0) {
                    _cliente.setId(maiorIdClienteLista());
                }

                //_usuario.setId(ii);
                listaClientes.add(_cliente);
            }
            i++;
        }
        /// atualizar
        HomeController.salvarDadosClientes();
        HomeController.carregarDadosClientes();
        HomeController.carregarTabelaClientes();
        //jScrollPanelClientes.setViewportView(_jTableUsuarios);
    }

    /**
     * Salvar os Dados dos Usuarios
     */
    @SuppressWarnings("null")
    public static void salvarDadosUsuarios() {
        File file = new File("projetoestoque.usuarios.persistencia.db");

        @SuppressWarnings("UnusedAssignment")
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(listaUsuarios);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mensagemTela = "Arquivo usuários salvo com sucesso!";
    }

    private static void salvarDadosClientes() {
        File file = new File("projetoestoque.clientes.persistencia.db");

        @SuppressWarnings("UnusedAssignment")
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(listaClientes);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mensagemTela = "Arquivo Clientes salvo com sucesso!";
    }

    /**
     * Salvar Dados dos Fornecedores
     */
    private static void salvarDadosFornecedores() {
        File file = new File("projetoestoque.fornecedores.persistencia.db");

        @SuppressWarnings("UnusedAssignment")
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(listaFornecedores);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mensagemTela = "Arquivo Fornecedores salvo com sucesso!";
    }

    private static void salvarDadosProdutos() {
        File file = new File("projetoestoque.produtos.persistencia.db");

        @SuppressWarnings("UnusedAssignment")
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(listaProdutos);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mensagemTela = "Arquivo Produtos salvo com sucesso!";
    }

    private static void carregarDadosProdutos() {
        print("carregarDadosProdutos()");

        File file = new File("projetoestoque.produtos.persistencia.db");

        ObjectInputStream in = null;
        java.util.List<Produto> listaProdutosTmp = null;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            listaProdutosTmp = (ArrayList<Produto>) in.readObject();
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        if (listaProdutosTmp == null) {
            listaProdutosTmp = new ArrayList<Produto>();
        }

        setListaProdutos(listaProdutosTmp);
    }

    private static int maiorIdProdutoLista() {
        // próximo Id
        int ii = 0;
        for (Produto produtoFromLista : listaProdutos) {
            if (ii <= produtoFromLista.getId()) {
                ii = produtoFromLista.getId() + 1;
            }
        }
        if (ii == 0) {
            ii++;
        }
        return ii;
    }

    /**
     * Carregar Dados dos Usuarios
     */
    public static synchronized void carregarDadosUsuarios() {

        print("carregarDadosUsuarios()");

        File file = new File("projetoestoque.usuarios.persistencia.db");

        ObjectInputStream in = null;
        java.util.List<Usuario> listaUsuariosTmp = null;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            listaUsuariosTmp = (ArrayList<Usuario>) in.readObject();
            //listaUsuario = listaUsuariosTmp;
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        if (listaUsuariosTmp == null) {
            listaUsuariosTmp = new ArrayList<Usuario>();
        }

        setListaUsuarios(listaUsuariosTmp);
    }

    protected static void print(String texto) {
        System.out.println(texto);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelOne = new javax.swing.JPanel();
        jPanelBaseLogin = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jButtonLoginAction = new javax.swing.JButton();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jPanelLogin = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSplitPanelOne = new javax.swing.JSplitPane();
        jPanelLadoA = new javax.swing.JPanel();
        jButtonUsuario = new javax.swing.JButton();
        jButtonFornecedores = new javax.swing.JButton();
        jButtonProdutos = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelLadoB = new javax.swing.JPanel();
        jPanelBaseHome = new javax.swing.JPanel();
        jLabelCadastrosTitulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelBaseUsuarios = new javax.swing.JPanel();
        jScrollPaneUsuarios = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jButtonNovoUsuario = new javax.swing.JButton();
        jLabelCadastrosTitulo1 = new javax.swing.JLabel();
        jPanelBaseClientes = new javax.swing.JPanel();
        jScrollPanelClientes = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonNovoClientes = new javax.swing.JButton();
        jLabelCadastrosTituloClientes = new javax.swing.JLabel();
        jPanelBaseFornecedores = new javax.swing.JPanel();
        jScrollPanelFornecedores = new javax.swing.JScrollPane();
        jTableClientes1 = new javax.swing.JTable();
        jButtonNovoFornecedores = new javax.swing.JButton();
        jLabelCadastrosTituloFornecedores = new javax.swing.JLabel();
        jPanelBaseProdutos = new javax.swing.JPanel();
        jScrollPanelProdutos = new javax.swing.JScrollPane();
        jTableClientes2 = new javax.swing.JTable();
        jButtonNovoProduto = new javax.swing.JButton();
        jLabelCadastrosTituloProdutos = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemHome = new javax.swing.JMenuItem();
        jMeniItemLogout = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemFecharApp = new javax.swing.JMenuItem();
        jMenuCadastros = new javax.swing.JMenu();
        menuUsuarios = new javax.swing.JMenuItem();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemFornecedores = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemProdutos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1900, 1024));
        setMinimumSize(new java.awt.Dimension(1050, 550));
        setPreferredSize(new java.awt.Dimension(1050, 550));
        setResizable(false);

        jPanelOne.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanelBaseLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelBaseLogin.setMaximumSize(new java.awt.Dimension(350, 350));
        jPanelBaseLogin.setMinimumSize(new java.awt.Dimension(350, 350));
        jPanelBaseLogin.setName(""); // NOI18N
        jPanelBaseLogin.setNextFocusableComponent(jTextFieldUsuario);

        jLabel3.setText("Usuário:");

        jLabel4.setText("Senha:");

        jTextFieldUsuario.setText("admin");

        jButtonLoginAction.setText("Iniciar");
        jButtonLoginAction.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonLoginAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionActionPerformed(evt);
            }
        });

        jPasswordFieldSenha.setForeground(new java.awt.Color(102, 102, 102));
        jPasswordFieldSenha.setText("admin");

        jLabel5.setText("Fatec Botucatu, ADS 2022");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jLabel6.setText("Projeto Controle de Estoque");

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBaseLoginLayout = new javax.swing.GroupLayout(jPanelBaseLogin);
        jPanelBaseLogin.setLayout(jPanelBaseLoginLayout);
        jPanelBaseLoginLayout.setHorizontalGroup(
            jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBaseLoginLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonLoginAction, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBaseLoginLayout.createSequentialGroup()
                        .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42))
            .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBaseLoginLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBaseLoginLayout.setVerticalGroup(
            jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBaseLoginLayout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelBaseLoginLayout.createSequentialGroup()
                        .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(47, 47, 47))
                    .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(jButtonLoginAction, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanelBaseLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBaseLoginLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(232, Short.MAX_VALUE)))
        );

        jPanelOne.add(jPanelBaseLogin);
        jPanelBaseLogin.getAccessibleContext().setAccessibleName("Login");

        jSplitPanelOne.setEnabled(false);
        jSplitPanelOne.setMaximumSize(new java.awt.Dimension(1900, 1024));
        jSplitPanelOne.setMinimumSize(new java.awt.Dimension(948, 323));

        jButtonUsuario.setText("Usuários");
        jButtonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioActionPerformed(evt);
            }
        });

        jButtonFornecedores.setText("Fornecedores");
        jButtonFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFornecedoresActionPerformed(evt);
            }
        });

        jButtonProdutos.setText("Produtos");
        jButtonProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutosActionPerformed(evt);
            }
        });

        jButtonClientes.setText("Clientes");
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Cadastros");

        javax.swing.GroupLayout jPanelLadoALayout = new javax.swing.GroupLayout(jPanelLadoA);
        jPanelLadoA.setLayout(jPanelLadoALayout);
        jPanelLadoALayout.setHorizontalGroup(
            jPanelLadoALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLadoALayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLadoALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanelLadoALayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLadoALayout.setVerticalGroup(
            jPanelLadoALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLadoALayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonFornecedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonProdutos)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jSplitPanelOne.setLeftComponent(jPanelLadoA);

        jPanelLadoB.setPreferredSize(null);
        jPanelLadoB.setLayout(new javax.swing.OverlayLayout(jPanelLadoB));

        jPanelBaseHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelCadastrosTitulo.setText("Home >>");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("FATEC Botucatu, ADS  >>  Projeto para Controle de Estoque");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel7.setText("Desenvolvimento do Projeto: Camilla Carvalho");

        jLabel8.setText("Orientador: Osvaldo Cesar Pinheiro");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBaseHomeLayout = new javax.swing.GroupLayout(jPanelBaseHome);
        jPanelBaseHome.setLayout(jPanelBaseHomeLayout);
        jPanelBaseHomeLayout.setHorizontalGroup(
            jPanelBaseHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBaseHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBaseHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelCadastrosTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                .addGap(120, 120, 120))
        );
        jPanelBaseHomeLayout.setVerticalGroup(
            jPanelBaseHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseHomeLayout.createSequentialGroup()
                .addComponent(jLabelCadastrosTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelLadoB.add(jPanelBaseHome);

        jPanelBaseUsuarios.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPaneUsuarios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPaneUsuariosPropertyChange(evt);
            }
        });

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableUsuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableUsuarios.setColumnSelectionAllowed(false);
        jTableUsuarios.setDoubleBuffered(true);
        jTableUsuarios.setFillsViewportHeight(true);
        jTableUsuarios.setRowSelectionAllowed(false);
        jTableUsuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableUsuariosFocusGained(evt);
            }
        });
        jTableUsuarios.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTableUsuariosInputMethodTextChanged(evt);
            }
        });
        jTableUsuarios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableUsuariosPropertyChange(evt);
            }
        });
        jScrollPaneUsuarios.setViewportView(jTableUsuarios);

        jButtonNovoUsuario.setText("Novo Usuário");
        jButtonNovoUsuario.setActionCommand("");
        jButtonNovoUsuario.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonNovoUsuarioStateChanged(evt);
            }
        });
        jButtonNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoUsuarioActionPerformed(evt);
            }
        });

        jLabelCadastrosTitulo1.setText("Usuários");

        javax.swing.GroupLayout jPanelBaseUsuariosLayout = new javax.swing.GroupLayout(jPanelBaseUsuarios);
        jPanelBaseUsuarios.setLayout(jPanelBaseUsuariosLayout);
        jPanelBaseUsuariosLayout.setHorizontalGroup(
            jPanelBaseUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBaseUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addGroup(jPanelBaseUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabelCadastrosTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(90, 90, 90)
                        .addComponent(jButtonNovoUsuario)))
                .addContainerGap())
        );
        jPanelBaseUsuariosLayout.setVerticalGroup(
            jPanelBaseUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseUsuariosLayout.createSequentialGroup()
                .addGroup(jPanelBaseUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoUsuario)
                    .addComponent(jLabelCadastrosTitulo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelLadoB.add(jPanelBaseUsuarios);

        jPanelBaseClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes.setEnabled(false);
        jScrollPanelClientes.setViewportView(jTableClientes);

        jButtonNovoClientes.setText("Novo Cliente");
        jButtonNovoClientes.setActionCommand("");
        jButtonNovoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoClientesActionPerformed(evt);
            }
        });

        jLabelCadastrosTituloClientes.setText("Clientes");

        javax.swing.GroupLayout jPanelBaseClientesLayout = new javax.swing.GroupLayout(jPanelBaseClientes);
        jPanelBaseClientes.setLayout(jPanelBaseClientesLayout);
        jPanelBaseClientesLayout.setHorizontalGroup(
            jPanelBaseClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBaseClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addGroup(jPanelBaseClientesLayout.createSequentialGroup()
                        .addComponent(jLabelCadastrosTituloClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNovoClientes)))
                .addContainerGap())
        );
        jPanelBaseClientesLayout.setVerticalGroup(
            jPanelBaseClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseClientesLayout.createSequentialGroup()
                .addGroup(jPanelBaseClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoClientes)
                    .addComponent(jLabelCadastrosTituloClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelLadoB.add(jPanelBaseClientes);

        jPanelBaseFornecedores.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableClientes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes1.setEnabled(false);
        jScrollPanelFornecedores.setViewportView(jTableClientes1);

        jButtonNovoFornecedores.setText("Novo Fornecedor");
        jButtonNovoFornecedores.setActionCommand("");
        jButtonNovoFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoFornecedoresActionPerformed(evt);
            }
        });

        jLabelCadastrosTituloFornecedores.setText("Fornecedores");

        javax.swing.GroupLayout jPanelBaseFornecedoresLayout = new javax.swing.GroupLayout(jPanelBaseFornecedores);
        jPanelBaseFornecedores.setLayout(jPanelBaseFornecedoresLayout);
        jPanelBaseFornecedoresLayout.setHorizontalGroup(
            jPanelBaseFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseFornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBaseFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPanelFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addGroup(jPanelBaseFornecedoresLayout.createSequentialGroup()
                        .addComponent(jLabelCadastrosTituloFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNovoFornecedores)))
                .addContainerGap())
        );
        jPanelBaseFornecedoresLayout.setVerticalGroup(
            jPanelBaseFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseFornecedoresLayout.createSequentialGroup()
                .addGroup(jPanelBaseFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoFornecedores)
                    .addComponent(jLabelCadastrosTituloFornecedores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelLadoB.add(jPanelBaseFornecedores);

        jPanelBaseProdutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableClientes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes2.setEnabled(false);
        jScrollPanelProdutos.setViewportView(jTableClientes2);

        jButtonNovoProduto.setText("Novo Produto");
        jButtonNovoProduto.setActionCommand("");
        jButtonNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoProdutoActionPerformed(evt);
            }
        });

        jLabelCadastrosTituloProdutos.setText("Produtos");

        javax.swing.GroupLayout jPanelBaseProdutosLayout = new javax.swing.GroupLayout(jPanelBaseProdutos);
        jPanelBaseProdutos.setLayout(jPanelBaseProdutosLayout);
        jPanelBaseProdutosLayout.setHorizontalGroup(
            jPanelBaseProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBaseProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPanelProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addGroup(jPanelBaseProdutosLayout.createSequentialGroup()
                        .addComponent(jLabelCadastrosTituloProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNovoProduto)))
                .addContainerGap())
        );
        jPanelBaseProdutosLayout.setVerticalGroup(
            jPanelBaseProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseProdutosLayout.createSequentialGroup()
                .addGroup(jPanelBaseProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoProduto)
                    .addComponent(jLabelCadastrosTituloProdutos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelLadoB.add(jPanelBaseProdutos);

        jSplitPanelOne.setRightComponent(jPanelLadoB);

        jPanelOne.add(jSplitPanelOne);

        jMenuFile.setText("Arquivo");

        jMenuItemHome.setText("Home");
        jMenuItemHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHomeActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemHome);

        jMeniItemLogout.setText("Logout do Usuário");
        jMeniItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMeniItemLogoutActionPerformed(evt);
            }
        });
        jMenuFile.add(jMeniItemLogout);
        jMenuFile.add(jSeparator2);

        jMenuItemFecharApp.setText("Fechar App");
        jMenuItemFecharApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFecharAppActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFecharApp);

        jMenuBar.add(jMenuFile);

        jMenuCadastros.setText("Cadastros");

        menuUsuarios.setText("Cadastrar Usuários");
        menuUsuarios.setActionCommand("menuCadastroUsuario");
        menuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuariosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(menuUsuarios);

        jMenuItemClientes.setText("Cadastrar Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemClientes);

        jMenuItemFornecedores.setText("Cadastrar Fornecedores");
        jMenuItemFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedoresActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemFornecedores);
        jMenuCadastros.add(jSeparator1);

        jMenuItemProdutos.setText("Cadastrar Produtos");
        jMenuItemProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutosActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuItemProdutos);

        jMenuBar.add(jMenuCadastros);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOne, javax.swing.GroupLayout.DEFAULT_SIZE, 1317, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelOne, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        carregarDadosClientes();
        jScrollPanelClientes.setViewportView(carregarTabelaClientes());
        trocarTela("clientes");
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jMenuItemFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFornecedoresActionPerformed
        carregarDadosFornecedores();
        jScrollPanelFornecedores.setViewportView(carregarTabelaFornecedores());
        trocarTela("fornecedores");
    }//GEN-LAST:event_jMenuItemFornecedoresActionPerformed

    private void jButtonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioActionPerformed
        carregarDadosUsuarios();
        jScrollPaneUsuarios.setViewportView(carregarTabelaUsuarios());
        trocarTela("usuarios");
    }//GEN-LAST:event_jButtonUsuarioActionPerformed

    private void jButtonFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFornecedoresActionPerformed
        carregarDadosFornecedores();
        jScrollPanelFornecedores.setViewportView(carregarTabelaFornecedores());
        trocarTela("fornecedores");
    }//GEN-LAST:event_jButtonFornecedoresActionPerformed

    private void jButtonProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutosActionPerformed
        carregarDadosProdutos();
        jScrollPanelProdutos.setViewportView(carregarTabelaProdutos());
        trocarTela("produtos");
    }//GEN-LAST:event_jButtonProdutosActionPerformed

    private void jButtonNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoUsuarioActionPerformed
        cadastroUsuarios.setCamposEntrada();
        cadastroUsuarios.carregarDados();
        cadastroUsuarios.setVisible(true);
    }//GEN-LAST:event_jButtonNovoUsuarioActionPerformed

    private void jMenuItemHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHomeActionPerformed
        trocarTela("home");
    }//GEN-LAST:event_jMenuItemHomeActionPerformed

    private void jButtonNovoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoClientesActionPerformed
        cadastroClientes.setCamposEntrada();
        cadastroClientes.carregarDados();
        cadastroClientes.setVisible(true);
    }//GEN-LAST:event_jButtonNovoClientesActionPerformed

    private void jButtonNovoUsuarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonNovoUsuarioStateChanged

    }//GEN-LAST:event_jButtonNovoUsuarioStateChanged

    private void jScrollPaneUsuariosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPaneUsuariosPropertyChange

    }//GEN-LAST:event_jScrollPaneUsuariosPropertyChange

    private void jTableUsuariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableUsuariosFocusGained

    }//GEN-LAST:event_jTableUsuariosFocusGained

    private void jButtonLoginActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionActionPerformed

        // LOGIN ADMINISTRADORES
        for (Usuario listaUsuario : listaUsuarios) {
            if (this.jTextFieldUsuario.getText().equalsIgnoreCase(listaUsuario.getLogin())
                    && this.jPasswordFieldSenha.getText().equalsIgnoreCase(listaUsuario.getSenha())) {
//               this.jTextFieldUsuario.setText("");
//               this.jPasswordFieldSenha.setText("");
                this.loginIsAdmin = true;
                trocarTela("home");
            }
        }

        if (this.jTextFieldUsuario.getText().equalsIgnoreCase("admin") && this.jPasswordFieldSenha.getText().equalsIgnoreCase("admin")) {
            // Runs outside of the Swing UI thread
//            this.jTextFieldUsuario.setText("");
//            this.jPasswordFieldSenha.setText("");
            this.loginIsAdmin = true;
            trocarTela("home");
        } else {
//            this.jTextFieldUsuario.setText("");
//            this.jPasswordFieldSenha.setText("");
        }

        // LOGIN CLIENTES
        for (Cliente clienteFromLista : listaClientes) {

            String inputLogin = this.jTextFieldUsuario.getText();
            String inputSenha = this.jPasswordFieldSenha.getText();

            String clienteLogin = clienteFromLista.getUsuario();

            if (clienteLogin.equalsIgnoreCase(inputLogin)) {
                //&& this.jPasswordFieldSenha.getText().equalsIgnoreCase( clienteFromLista.getSenha()) ){
                this.jTextFieldUsuario.setText("");
                this.jPasswordFieldSenha.setText("");
                this.loginIsAdmin = false;
                trocarTela("home");
            }
        }

    }//GEN-LAST:event_jButtonLoginActionActionPerformed

    private void jTableUsuariosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableUsuariosPropertyChange
        print(evt.toString());
    }//GEN-LAST:event_jTableUsuariosPropertyChange

    private void jTableUsuariosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTableUsuariosInputMethodTextChanged
        print(evt.toString());
    }//GEN-LAST:event_jTableUsuariosInputMethodTextChanged

    private void jMeniItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMeniItemLogoutActionPerformed
        trocarTela("login");
    }//GEN-LAST:event_jMeniItemLogoutActionPerformed

    private void jMenuItemFecharAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFecharAppActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemFecharAppActionPerformed

    private void jButtonNovoFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoFornecedoresActionPerformed
        cadastroFonecedores.carregarDados();
        cadastroFonecedores.setVisible(true);
    }//GEN-LAST:event_jButtonNovoFornecedoresActionPerformed

    private void jButtonNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoProdutoActionPerformed
        cadastroProdutos.carregarDados();
        cadastroProdutos.setVisible(true);
    }//GEN-LAST:event_jButtonNovoProdutoActionPerformed

    private void menuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuariosActionPerformed
        if (this.loginIsAdmin) {
            carregarDadosUsuarios();
            jScrollPaneUsuarios.setViewportView(carregarTabelaUsuarios());
            trocarTela("usuarios");
        }
    }//GEN-LAST:event_menuUsuariosActionPerformed

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        if (this.loginIsAdmin) {
            carregarDadosClientes();
            jScrollPanelClientes.setViewportView(carregarTabelaClientes());
            trocarTela("clientes");
        }
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutosActionPerformed
        carregarDadosProdutos();
        jScrollPanelProdutos.setViewportView(carregarTabelaProdutos());
        trocarTela("produtos");
    }//GEN-LAST:event_jMenuItemProdutosActionPerformed

    private void trocarTela(String tela) {
        ////
        if ("login".equals(tela)) {
            jPanelBaseLogin.setVisible(true);
            jSplitPanelOne.setVisible(false);
            jMenuBar.setVisible(false);
        } else {
            jPanelBaseLogin.setVisible(false);
            jSplitPanelOne.setVisible(true);
            jMenuBar.setVisible(true);
        }
        ////
        if (this.loginIsAdmin) {
            jButtonUsuario.setVisible(true);
            jButtonClientes.setVisible(true);
            //
            jButtonNovoFornecedores.setVisible(false);
            jButtonNovoProduto.setVisible(false);
        } else {
            jButtonUsuario.setVisible(false);
            jButtonClientes.setVisible(false);
            //
            jButtonNovoFornecedores.setVisible(true);
            jButtonNovoProduto.setVisible(true);
        }
        ////
        if ("usuarios".equals(tela)) {
            carregarTabelaUsuarios();
            jPanelBaseHome.setVisible(false);
            jPanelBaseUsuarios.setVisible(true);
            jPanelBaseClientes.setVisible(false);
            jPanelBaseFornecedores.setVisible(false);
            jPanelBaseProdutos.setVisible(false);
        } else if ("clientes".equals(tela)) {
            carregarTabelaClientes();
            jPanelBaseHome.setVisible(false);
            jPanelBaseUsuarios.setVisible(false);
            jPanelBaseClientes.setVisible(true);
            jPanelBaseFornecedores.setVisible(false);
            jPanelBaseProdutos.setVisible(false);
        } else if ("fornecedores".equals(tela)) {
            carregarTabelaFornecedores();
            jPanelBaseHome.setVisible(false);
            jPanelBaseUsuarios.setVisible(false);
            jPanelBaseClientes.setVisible(false);
            jPanelBaseFornecedores.setVisible(true);
            jPanelBaseProdutos.setVisible(false);
        } else if ("produtos".equals(tela)) {
            carregarTabelaFornecedores();
            jPanelBaseHome.setVisible(false);
            jPanelBaseUsuarios.setVisible(false);
            jPanelBaseClientes.setVisible(false);
            jPanelBaseFornecedores.setVisible(false);
            jPanelBaseProdutos.setVisible(true);
        } else {
            jPanelBaseHome.setVisible(true);
            jPanelBaseUsuarios.setVisible(false);
            jPanelBaseClientes.setVisible(false);
            jPanelBaseFornecedores.setVisible(false);
            jPanelBaseProdutos.setVisible(false);
        }
    }

    private static JTable carregarTabelaUsuarios() {
        // Set LISTA USUARIOS
        java.util.List<Usuario> listaUsuariosTmp = getListaUsuarios();
        String[][] dataUsuario = new String[0][0];

        if (listaUsuariosTmp != null) {
            dataUsuario = new String[listaUsuariosTmp.size()][5];
            //
            for (int i = 0; i < listaUsuariosTmp.size(); i++) {
                dataUsuario[i][0] = "" + listaUsuariosTmp.get(i).getId();
                dataUsuario[i][1] = listaUsuariosTmp.get(i).getNome();
                dataUsuario[i][2] = listaUsuariosTmp.get(i).getLogin();
                dataUsuario[i][3] = listaUsuariosTmp.get(i).getSenha();
                dataUsuario[i][4] = listaUsuariosTmp.get(i).getNivel();
            }
        }

        System.out.println(Arrays.toString(dataUsuario));

        ////////////////////////////
        // Column Names
        String[] columnNames = {"Id", "Nome", "Login", "Senha", "Nível de Acesso"};
        //String[][] data = {
        //{ "Camilla", "camilla", "123", "Adminstrador" },
        //};

        //jScrollPaneUsuarios
        JTable _jTableUsuarios = new JTable(dataUsuario, columnNames);
        _jTableUsuarios.setBounds(20, 20, 200, 300);

        //jScrollPaneUsuarios.setViewportView(_jTableUsuarios);
        return _jTableUsuarios;
    }

    private static JTable carregarTabelaClientes() {
        // Set LISTA CLIENTES
        java.util.List<Cliente> listaClientesTmp = getListaClientes();
        String[][] dataCliente = new String[0][0];
        // Column Names
        String[] columnNames = {"Id", "Nome", "Usuário", "Senha", "CPF", "Endereco", "Telefone", "e-Mail", "Data de Nascimento"};

        if (listaClientesTmp != null) {
            dataCliente = new String[listaClientesTmp.size()][columnNames.length];
            //
            for (int i = 0; i < listaClientesTmp.size(); i++) {
                dataCliente[i][0] = "" + listaClientesTmp.get(i).getId();
                dataCliente[i][1] = listaClientesTmp.get(i).getNome();
                dataCliente[i][2] = listaClientesTmp.get(i).getUsuario();
                dataCliente[i][3] = listaClientesTmp.get(i).getSenha();
                dataCliente[i][4] = listaClientesTmp.get(i).getEndereco().getRua();
                dataCliente[i][5] = listaClientesTmp.get(i).getTelefone();
                dataCliente[i][6] = listaClientesTmp.get(i).getTelefone();
                dataCliente[i][7] = listaClientesTmp.get(i).getEmail();
                dataCliente[i][8] = listaClientesTmp.get(i).getNascimento();
            }
        }

        System.out.println(Arrays.toString(dataCliente));

        ////////////////////////////
        String[][] data = {
            {"1", "C.1", "c1", "11111111", "rua ABC, n. 10", "11-999999", "abc@email.com", "10-10-2000"},};

        //jScrollPaneUsuarios
        JTable _jTableClientes = new JTable(dataCliente, columnNames);
        _jTableClientes.setBounds(20, 20, 200, 300);

        //jScrollPaneUsuarios.setViewportView(_jTableUsuarios);
        return _jTableClientes;
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HomeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HomeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HomeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HomeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HomeController().setVisible(true);
//            }
//        });
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonFornecedores;
    private javax.swing.JButton jButtonLoginAction;
    private javax.swing.JButton jButtonNovoClientes;
    private javax.swing.JButton jButtonNovoFornecedores;
    private javax.swing.JButton jButtonNovoProduto;
    private javax.swing.JButton jButtonNovoUsuario;
    private javax.swing.JButton jButtonProdutos;
    private javax.swing.JButton jButtonUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCadastrosTitulo;
    private javax.swing.JLabel jLabelCadastrosTitulo1;
    private javax.swing.JLabel jLabelCadastrosTituloClientes;
    private javax.swing.JLabel jLabelCadastrosTituloFornecedores;
    private javax.swing.JLabel jLabelCadastrosTituloProdutos;
    private javax.swing.JMenuItem jMeniItemLogout;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuCadastros;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemFecharApp;
    private javax.swing.JMenuItem jMenuItemFornecedores;
    private javax.swing.JMenuItem jMenuItemHome;
    private javax.swing.JMenuItem jMenuItemProdutos;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBaseClientes;
    private javax.swing.JPanel jPanelBaseFornecedores;
    private javax.swing.JPanel jPanelBaseHome;
    private javax.swing.JPanel jPanelBaseLogin;
    private javax.swing.JPanel jPanelBaseProdutos;
    private javax.swing.JPanel jPanelBaseUsuarios;
    private javax.swing.JPanel jPanelLadoA;
    private javax.swing.JPanel jPanelLadoB;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelOne;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JScrollPane jScrollPaneUsuarios;
    private javax.swing.JScrollPane jScrollPanelClientes;
    private javax.swing.JScrollPane jScrollPanelFornecedores;
    private javax.swing.JScrollPane jScrollPanelProdutos;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPanelOne;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableClientes1;
    private javax.swing.JTable jTableClientes2;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JMenuItem menuUsuarios;
    // End of variables declaration//GEN-END:variables

    private JTable carregarTabelaFornecedores() {
        // Set LISTA FORNECEDORES
        java.util.List<Fornecedor> listaFornecedoresTmp = getListaFornecedores();
        String[][] dataFornecedor = new String[0][0];

        // Column Names
        String[] columnNames = {"Id", "Nome", "Login", "Senha", "CNPJ", "Setor de Atuação"};

        if (listaFornecedoresTmp != null) {
            dataFornecedor = new String[listaFornecedoresTmp.size()][columnNames.length];
            //
            for (int i = 0; i < listaFornecedoresTmp.size(); i++) {
                dataFornecedor[i][0] = "" + listaFornecedoresTmp.get(i).getId();
                dataFornecedor[i][1] = listaFornecedoresTmp.get(i).getNome();
                dataFornecedor[i][2] = listaFornecedoresTmp.get(i).getLogin();
                dataFornecedor[i][3] = listaFornecedoresTmp.get(i).getSenha();
                dataFornecedor[i][4] = listaFornecedoresTmp.get(i).getCnpj();
                dataFornecedor[i][5] = listaFornecedoresTmp.get(i).getSetorAtuacao();
            }
        }

        System.out.println(Arrays.toString(dataFornecedor));

        ////////////////////////////
        String[][] data = {
            {"1", "Camilla's Company", "cc", "123", "3903230293", "Tecnologia da Informação"},
            {"2", "Coca-Cola Company", "coke", "coke123", "8284723499", "Alimentício"},};

        //jScrollPaneFornecedores
//        JTable _jTableFornecedores = new JTable(data, columnNames);
        JTable _jTableFornecedores = new JTable(dataFornecedor, columnNames);
        _jTableFornecedores.setBounds(20, 20, 200, 300);

        return _jTableFornecedores;
    }

    private JTable carregarTabelaProdutos() {
        // Set LISTA PRODUTOS
        java.util.List<Produto> listaProdutosTmp = getListaProdutos();
        String[][] dataProdutos = new String[0][0];

        // Column Names
        String[] columnNames = {"Id", "Nome", "Descrição", "Preço"};

        if (listaProdutosTmp != null) {
            dataProdutos = new String[listaProdutosTmp.size()][columnNames.length];
            //
            for (int i = 0; i < listaProdutosTmp.size(); i++) {
                dataProdutos[i][0] = "" + listaProdutosTmp.get(i).getId();
                dataProdutos[i][1] = listaProdutosTmp.get(i).getNome();
                dataProdutos[i][2] = listaProdutosTmp.get(i).getDescricao();
                dataProdutos[i][3] = listaProdutosTmp.get(i).getPreco();
            }
        }

        System.out.println(Arrays.toString(dataProdutos));

        ////////////////////////////
        String[][] data = {
            {"1", "Produto 01", "Este é o produto 01", "1000"},
            {"2", "Produto 02", "Este é o produto 02", "2000"},};

        //jScrollPaneFornecedores
//        JTable _jTableProdutos = new JTable(data, columnNames);
        JTable _jTableProdutos = new JTable(dataProdutos, columnNames);
        _jTableProdutos.setBounds(20, 20, 200, 300);

        return _jTableProdutos;
    }

    private List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    private List<Produto> getListaProdutos() {
        return listaProdutos;
    }

}
