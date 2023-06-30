package principal;

import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import br.com.caelum.stella.validation.CPFValidator;
import model.dao.CartaoDependenteDAO;
import model.dao.CartaoPrincipalDAO;
import model.dao.ConectaBD;
import model.dao.ContaDAO;
import model.dao.DependenteDAO;
import model.dao.EstabelecimentoDAO;
import model.dao.PessoaDAO;
import model.dao.TransacoesDAO;
import model.entity.CartaoDependente;
import model.entity.CartaoPrincipal;
import model.entity.Conta;
import model.entity.Dependente;
import model.entity.Estabelecimento;
import model.entity.Pessoa;
import model.entity.Transacoes;

public class Main {
	
	public static String leString(String msg) {
		String valor = JOptionPane.showInputDialog(null, msg);
		return valor;
	}
	
	public static String leString2(String msg) {
		String valor = JOptionPane.showInputDialog(null, new JTextArea(msg));
		return valor;
	}
	
	public static boolean validaCPF(String cpf) { 
	    CPFValidator cpfValidator = new CPFValidator(); 
	    try { 
	    	cpfValidator.assertValid(cpf); 
	    	return true;
	    } catch(Exception e) { 
	        e.printStackTrace(); 
	        return false;
	    }
	}
	
	public static boolean validaIdade(int ano) {
		Calendar dataAtual = GregorianCalendar.getInstance();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		
		if ((anoAtual - ano < 0) || (anoAtual - ano > 150)) {
			return false;
			
		} else {
			return true;
		}
	}
	
	public static String geraChavePix() {
		String chavePix = "";
		
		chavePix = UUID.randomUUID().toString().replace("-", "");
		
		return chavePix;
	}
	
	public static String geraBoleto() {
		String codBoleto = "";
		
		String codBanco = "368";
		String moeda = "9";
		String agencia = "0294";
		String cod1 = "";
		String cod2 = "";
		String cod3 = "";
		String codVerificador = "";
		
		for (int i = 0; i < 8; i++) {
			cod1 += new Random().nextInt(10);
		}
		
		for (int i = 0; i < 14; i++) {
			cod2 += new Random().nextInt(10);
		}
		
		for (int i = 0; i < 16; i++) {
			cod3 += new Random().nextInt(10);
		}
		
		for (int i = 0; i < 2; i++) {
			codVerificador += new Random().nextInt(10);
		}
		
		codBoleto = codBanco + " " + agencia + " " + moeda + " " + cod1 + " " + cod2 + " " + cod3 + "-" + codVerificador;
		
		return codBoleto;
	}
	
//------------------------------------------------------------------//
//								Menu								//
//------------------------------------------------------------------//
	
	public static int menu() {
		String saida = "";
		saida += "Menu Principal: \n\n";
		saida += "1 - Pessoa \n";
		saida += "2 - Conta \n";
		saida += "3 - Cartão \n";
		saida += "4 - Comprar \n";
		saida += "5 - Transações \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menu = Integer.parseInt(leString(saida));
		
		return menu;
	}
	
	public static int menuPessoa() {
		String saida = "";
		
		saida = "Menu Pessoa: \n\n";
		saida += "1 - Cadastrar \n";
		saida += "2 - Consultar \n";
		saida += "3 - Atualizar \n";
		saida += "4 - Excluir Cadastro \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menuPessoa = Integer.parseInt(leString(saida));
		
		return menuPessoa;
	}
	
	public static int menuAtualizaPessoa() {
		String saida = "";
		
		saida = "Menu Atualiza Pessoa: \n\n";
		saida += "1 - Atualizar CPF \n";
		saida += "2 - Atualizar nome \n";
		saida += "3 - Atualizar ano nascimento \n";
		saida += "4 - Atualizar salario \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menuAtualizaPessoa = Integer.parseInt(leString(saida));
		
		return menuAtualizaPessoa;
	}
	
	public static int menuConta() {
		String saida = "";
		
		saida = "Menu Conta: \n\n";
		saida += "1 - Cadastrar \n";
		saida += "2 - Consultar \n";
		saida += "3 - Excluir Conta \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
				
		int menuConta = Integer.parseInt(leString(saida));
		
		return menuConta;
	}
	
	public static int menuCartao() {
		String saida = "";
		
		saida = "Menu Cartão: \n\n";
		saida += "1 - Cartão Principal \n";
		saida += "2 - Cartão Dependente \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menuCartao = Integer.parseInt(leString(saida));
		
		return menuCartao;
	}
	
	public static int menuCartaoPrinc() {
		String saida = "";
		
		saida = "Menu Cartão Principal: \n\n";
		saida += "1 - Cadastrar \n";
		saida += "2 - Consultar Cartão \n";
		saida += "3 - Consultar Limite e Saldo \n";
		saida += "4 - Visualizar Fatura \n";
		saida += "5 - Excluir Cartão Principal \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menuCartaoPrinc = Integer.parseInt(leString(saida));
		
		return menuCartaoPrinc;
	}
	
	public static int menuCartaoDepend() {
		String saida = "";
		
		saida = "Menu Cartão Dependente: \n\n";
		saida += "1 - Cadastrar \n";
		saida += "2 - Consultar Cartão \n";
		saida += "3 - Consultar Limite e Saldo \n";
		saida += "4 - Visualizar Fatura \n";
		saida += "5 - Excluir Cartão Dependente \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menuCartaoDepend = Integer.parseInt(leString(saida));
		
		return menuCartaoDepend;
	}
	
	public static int menuTransacoes() {
		String saida = "";
		
		saida = "Menu Transações: \n\n";
		saida += "1 - Consultar Transações Pendentes \n";
		saida += "2 - Consultar Transações Pagas \n";
		saida += "3 - Consultar Todas Transações \n";
		saida += "0 - Sair \n\n";
		saida += "Digite a opção desejada: \n";
		
		int menuTransacoes = Integer.parseInt(leString(saida));
		
		return menuTransacoes;
	}
	
//------------------------------------------------------------------//
//							Metodos Pessoa							//
//------------------------------------------------------------------//
	
	public static boolean metodoConsultarCPF(String cpf) {
		Pessoa p = new PessoaDAO().consultarCPF(cpf.replace(".", "").replace("-", ""));
		if(p != null) {
			//Possui Cadastro
			return true;
		} else {
			//Não Possui Cadastro
			return false;
		}
	}
	
	public static void metodoInserirPessoa() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultarCPF(cpf) == false) {
				String nome = leString("Digite o nome");
				String anoNasciStr = leString("Digite o ano de nascimento");
				int anoNasciInt = Integer.parseInt(anoNasciStr);
				
				if (validaIdade(anoNasciInt) == true) {
					String salarioStr = leString("Digite o salario");
					double salarioDbl = Double.parseDouble(salarioStr);
					
					Pessoa p = new Pessoa(nome, cpf, anoNasciInt, salarioDbl, false);
					PessoaDAO pDAO = new PessoaDAO();
					pDAO.inserir(p);
					
					JOptionPane.showMessageDialog(null, new JTextArea("Cadastro criado com sucesso!"));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Ano Nascimento Inválida!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado já possui cadastro!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultaPessoa() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			Pessoa p = new PessoaDAO().consultarCPF(cpf);
			String saida = "";
			
			if (p != null) {
				saida = "CPF: " + p.getCpf() + "\n";
				saida += "Nome: " + p.getNome() + "\n";
				saida += "Ano Nascimento: " + p.getAnoNasci() + "\n";
				saida += "Salario: " + p.getSalario() + "\n";
			} else {
				saida = "CPF não possui cadastro!";
			}
			
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoAtualizaPessoa(String opcao) {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultarCPF(cpf) == true) {
				String novaInfo = leString("Digite o novo " + opcao);
				PessoaDAO pDAO = new PessoaDAO();
				
				if (pDAO.atualizar(opcao, cpf, novaInfo)) {
					JOptionPane.showMessageDialog(null, new JTextArea("CPF " + cpf + " atualizado!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui cadastro!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoDeletarPessoa() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultarCPF(cpf) == true) {
				PessoaDAO pDAO = new PessoaDAO();
				
				if (pDAO.deletar(cpf)) {
					JOptionPane.showMessageDialog(null, new JTextArea("CPF " + cpf + " excluído!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui cadastro!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
//------------------------------------------------------------------//
//							Metodos Conta							//
//------------------------------------------------------------------//
	
	public static boolean metodoConsultaNumConta(String cpf) {
		String numConta = new ContaDAO().consultaContaPorCPF(cpf.replace(".", "").replace("-", ""));
		if(numConta != null) {
			//Possui Conta
			return true;
		} else {
			//Não Possui Conta
			return false;
		}
	}
	
	public static void metodoInserirConta() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumConta(cpf) == false) {
				String numConta = "";
				for (int i = 0; i < 8; i++) {
					numConta += new Random().nextInt(10);
				}
				
				int idpessoa = new PessoaDAO().consultarCPF(cpf).getIdpessoa();
				System.out.println(idpessoa);
				
				Conta c = new Conta(numConta, idpessoa);
				ContaDAO cDAO = new ContaDAO();
				cDAO.inserirConta(c);
				
				JOptionPane.showMessageDialog(null, new JTextArea("Conta criada com sucesso!"));
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado já possui Conta cadastrada!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultarConta() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			String saida = "";
			
			if (metodoConsultaNumConta(cpf) == true) {
				String numConta = new ContaDAO().consultaContaPorCPF(cpf);
				String nome = new PessoaDAO().consultarCPF(cpf).getNome();
				
				saida += "Numero Conta: " + numConta + "\n";
				saida += "Nome: " + nome + "\n";
				saida += "CPF: " + cpf + "\n";
						
			} else {
				saida = "CPF informado não possui Conta cadastrada!";
			}
			
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoDeletarConta() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumConta(cpf) == true) {
				if (metodoConsultaNumCartaoP(cpf) == false) {
					DependenteDAO dDAO = new DependenteDAO();
					String cpfD = dDAO.consultaCPFDependente(cpf);
					if (cpfD != null) {
						if (metodoConsultaNumCartaoD(cpfD) == false) {
							
							ContaDAO cDAO = new ContaDAO();
							String numConta = cDAO.consultaContaPorCPF(cpf);
							cDAO.deletarConta(numConta);
							
						} else {
							JOptionPane.showMessageDialog(null, new JTextArea("Conta não pode ser excluido pois possui cartão dependente cadastrado!"));
						}
					} else {
						
						ContaDAO cDAO = new ContaDAO();
						String numConta = cDAO.consultaContaPorCPF(cpf);
						cDAO.deletarConta(numConta);
						
					}
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Conta não pode ser excluido pois possui cartão principal cadastrado!"));
				}
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Conta cadastrada!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
//------------------------------------------------------------------//
//							Metodos Cartão							//
//------------------------------------------------------------------//
	
	public static void metodoPagarComPix(String numCartao, double vlrFatura, int idtransacao) {
		String chavePix = geraChavePix();
		String saida = "";
		
		saida += "Valor a Pagar: \n";
		saida += vlrFatura + "\n\n";
		saida += "Chave PIX: \n";
		saida += chavePix + "\n\n";
		saida += "Digite o valor do pagamento: \n";
		
		Double vlrPago = Double.parseDouble(leString(saida));
		
		TransacoesDAO tDAO = new TransacoesDAO();
		tDAO.pagarTransacao(idtransacao, vlrPago);
		
		int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
		double saldo = new CartaoPrincipalDAO().consultaSaldoCartaoP(numCartao);
		
		ConectaBD con = new ConectaBD();
		String sql = "UPDATE cartao SET saldo = ? WHERE idcartao = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setDouble(1, saldo + vlrPago);
			pst.setInt(2, idcartao);
			pst.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		JOptionPane.showMessageDialog(null, new JTextArea("Valor Pago com Sucesso!"));
		
	}
	
	public static void metodoPagarComBoleto(String numCartao, double vlrFatura, int idtransacao) {
		String codBoleto = geraBoleto();
		String saida = "";
		
		saida += "Valor a Pagar: \n";
		saida += vlrFatura + "\n\n";
		saida += "Codigo Boleto: \n";
		saida += codBoleto + "\n\n";
		saida += "Digite o valor do pagamento: \n";
		
		Double vlrPago = Double.parseDouble(leString(saida));
		
		TransacoesDAO tDAO = new TransacoesDAO();
		tDAO.pagarTransacao(idtransacao, vlrPago);
		
		int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
		double saldo = new CartaoPrincipalDAO().consultaSaldoCartaoP(numCartao);
		
		ConectaBD con = new ConectaBD();
		String sql = "UPDATE cartao SET saldo = ? WHERE idcartao = ?;";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setDouble(1, saldo + vlrPago);
			pst.setInt(2, idcartao);
			pst.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		JOptionPane.showMessageDialog(null, new JTextArea("Valor Pago com Sucesso!"));
		
	}
	
	//------------------------------------------------------------------//
	//							Cartão Principal						//
	//------------------------------------------------------------------//
	
	public static boolean metodoConsultaNumCartaoP(String cpf) {
		String numCartaoP = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf.replace(".", "").replace("-", ""));
		if(numCartaoP != null) {
			//Possui Cartao Principal
			return true;
		} else {
			//Não Possui Cartao Principal
			return false;
		}
	}
	
	public static void metodoInserirCartaoP() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoP(cpf) == false) {
				double salario = new PessoaDAO().consultarCPF(cpf).getSalario();
				String numConta = new ContaDAO().consultaContaPorCPF(cpf);
				int idconta = new ContaDAO().consultaConta(numConta).getIdconta();
				
				CartaoPrincipal cP = new CartaoPrincipal(idconta, true, false);
				CartaoPrincipalDAO cPDAO = new CartaoPrincipalDAO();
				cPDAO.inserirCartaoPrinc(cP, salario, idconta);
				
				JOptionPane.showMessageDialog(null, new JTextArea("Cartão Principal criado com sucesso!"));
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado já possui Cartão Principal criado!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultarCartaoP() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			String saida = "";
			
			if (metodoConsultaNumCartaoP(cpf) == true) {
				String numCartaoP = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				String numConta = new ContaDAO().consultaContaPorCPF(cpf);
				String nome = new PessoaDAO().consultarCPF(cpf).getNome();
				
				saida += "Numero Cartão Principal: " + numCartaoP + "\n";
				saida += "Numero Conta: " + numConta + "\n";
				saida += "Nome: " + nome + "\n";
				saida += "CPF: " + cpf + "\n";
						
			} else {
				saida = "CPF informado não possui Cartão Principal cadastrado!";
			}
			
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultarLimSalCartaoP() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			String saida = "";
			
			if (metodoConsultaNumCartaoP(cpf) == true) {
				String numCartaoP = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				String numConta = new ContaDAO().consultaContaPorCPF(cpf);
				String nome = new PessoaDAO().consultarCPF(cpf).getNome();
				double limite = new CartaoPrincipalDAO().consultaLimiteCartaoP(numCartaoP);
				double saldo = new CartaoPrincipalDAO().consultaSaldoCartaoP(numCartaoP);
				
				saida += "Numero Cartão Principal: " + numCartaoP + "\n";
				saida += "Numero Conta: " + numConta + "\n";
				saida += "Nome: " + nome + "\n";
				saida += "CPF: " + cpf + "\n\n";
				saida += "Limite: " + limite + "\n";
				saida += "Saldo: " + saldo + "\n";
						
			} else {
				saida = "CPF informado não possui Cartão Principal cadastrado!";
			}
			
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultaFaturaCartaoP() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		int opPagamento = 0;
		if (validaCPF(cpf) == true) {
			String saida = "";
			
			if (metodoConsultaNumCartaoP(cpf) == true) {
				
				String numCartao = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPendentes(idcartao);
				if (!listaTransacoes.isEmpty()) {
		            saida += "ID Transação\tEstab\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					saida += "\n0 - Sair\n\n";
					saida += "Digite o id da transação que deseja pagar";
					
					int idtransacao = Integer.parseInt(leString2(saida));
					if (idtransacao != 0) {
						
						TransacoesDAO tDAO = new TransacoesDAO();
						if(tDAO.consulta(idtransacao) != null) {
							Double vlrPendente = tDAO.consulta(idtransacao).getValorPendente();
							
							String saida2 = "";
							
							saida2 += "Valor a Pagar: " + vlrPendente + "\n\n";
							saida2 += "1 - Pagar com PIX \n";
							saida2 += "2 - Pagar com Boleto \n";
							saida2 += "0 - Sair \n\n";
							saida2 += "Digite a opção desejada: \n\n";
							
							opPagamento = Integer.parseInt(leString(saida2));
							
							switch (opPagamento) {
							case 1: {
								metodoPagarComPix(numCartao, vlrPendente, idtransacao);
								break;
							} case 2: {
								metodoPagarComBoleto(numCartao, vlrPendente, idtransacao);
								break;
							} case 0: {
								break;
							} default:
								erroOpcao();
							}
						} else {
							JOptionPane.showMessageDialog(null, new JTextArea("Opção inválida!"));
						}
					} else {
						menuCartaoPrinc();
					}
				
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}
							
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão Principal cadastrado!"));
			}
					
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoDeletarCartaoP() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoP(cpf) == true) {
				Dependente d = new DependenteDAO().consultaDependente(cpf);
				if (d == null) {
					String numCartao = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
					int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
					
					List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPendentes(idcartao);
					if (listaTransacoes.isEmpty()) {
						CartaoPrincipalDAO cpDAO = new CartaoPrincipalDAO();
						
						if (cpDAO.deletarCartaoPrinc(numCartao)) {
							JOptionPane.showMessageDialog(null, new JTextArea("Cartão Principal Nº " + numCartao + " excluído!"));
						}
						
					} else {
						JOptionPane.showMessageDialog(null, new JTextArea("Cartão não pode ser excluido pois possui valor pendente de pagamento!"));
					}
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Cartão não pode ser excluido pois possui Dependente cadastrado!"));
				}
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão Principal cadastrado!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	//------------------------------------------------------------------//
	//							Cartão Dependente						//
	//------------------------------------------------------------------//
	
	public static boolean metodoConsultaNumCartaoD(String cpf) {
		String numCartaoD = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpf.replace(".", "").replace("-", ""));
		if(numCartaoD != null) {
			//Possui Cartao Dependente
			return true;
		} else {
			//Não Possui Cartao Dependente
			return false;
		}
	}
	
	public static void metodoInserirCartaoD() {
		String cpfP = leString("Digite o CPF do Titular da Conta");
		cpfP = cpfP.replace(".", "").replace("-", "");
		if (validaCPF(cpfP) == true) {
			if (metodoConsultaNumConta(cpfP) == true) {
				if (metodoConsultaNumCartaoP(cpfP) == true) {
					Dependente d = new DependenteDAO().consultaDependente(cpfP);
					if (d == null) {
						String cpfD = leString("Digite o CPF do Dependente");
						cpfD = cpfD.replace(".", "").replace("-", "");
						if (validaCPF(cpfD) == true) {
							double limite = Double.parseDouble(leString("Digite o limite desejado para o Cartão Dependente"));
							
							String numCartaoP = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpfP);
							double limiteCartaoP = new CartaoPrincipalDAO().consultaLimiteCartaoP(numCartaoP);
							
							if (limite <= limiteCartaoP) {
								
								String numConta = new ContaDAO().consultaContaPorCPF(cpfP);
								int idconta = new ContaDAO().consultaConta(numConta).getIdconta();
								
								CartaoDependente cD = new CartaoDependente(idconta, false, true);
								CartaoDependenteDAO cDDAO = new CartaoDependenteDAO();
								cDDAO.inserirCartaoDepend(cD, limite, idconta);
								
								DependenteDAO dDAO = new DependenteDAO();
								dDAO.inserirDependente(cpfP, cpfD);
								
								JOptionPane.showMessageDialog(null, new JTextArea("Cartão Dependente criado com sucesso!"));
								
							} else {
								JOptionPane.showMessageDialog(null, new JTextArea("Limite do cartão dependente não pode ser maior que o limite do cartão Principal!"));
							}
						} else {
							JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
						}
					} else {
						JOptionPane.showMessageDialog(null, new JTextArea("CPF informado já possui dependente cadastrado!"));
					}
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão Principal cadastrado!"));
				}
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Conta cadastrada!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultarCartaoD() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			String saida = "";
			
			String cpfPrinc = new DependenteDAO().consultaPrincipal(cpf);
			String numConta = new ContaDAO().consultaContaPorCPF(cpfPrinc);
			
			if (metodoConsultaNumCartaoD(cpfPrinc) == true) {
				String numCartaoD = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpfPrinc);
				String nome = new PessoaDAO().consultarCPF(cpf).getNome();
				
				saida += "Numero Cartão Dependente: " + numCartaoD + "\n";
				saida += "Numero Conta: " + numConta + "\n";
				saida += "Nome: " + nome + "\n";
				saida += "CPF: " + cpf + "\n";
						
			} else {
				saida = "CPF informado não possui Cartão Dependente cadastrado!";
			}
			
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultarLimSalCartaoD() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			String saida = "";
			String cpfPrinc = new DependenteDAO().consultaPrincipal(cpf);
			
			if (metodoConsultaNumCartaoD(cpfPrinc) == true) {
				String numCartaoD = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpfPrinc);
				String numConta = new ContaDAO().consultaContaPorCPF(cpfPrinc);
				String nome = new PessoaDAO().consultarCPF(cpf).getNome();
				double limite = new CartaoDependenteDAO().consultaLimiteCartaoD(numCartaoD);
				double saldo = new CartaoDependenteDAO().consultaSaldoCartaoD(numCartaoD);
				
				saida += "Numero Cartão Dependente: " + numCartaoD + "\n";
				saida += "Numero Conta: " + numConta + "\n";
				saida += "Nome: " + nome + "\n";
				saida += "CPF: " + cpf + "\n\n";
				saida += "Limite: " + limite + "\n";
				saida += "Saldo: " + saldo + "\n";
						
			} else {
				saida = "CPF informado não possui Cartão Dependente cadastrado!";
			}
			
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultaFaturaCartaoD() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		int opPagamento = 0;
		if (validaCPF(cpf) == true) {
			String saida = "";
			String cpfPrinc = new DependenteDAO().consultaPrincipal(cpf);
			
			if (metodoConsultaNumCartaoD(cpfPrinc) == true) {
				
				String numCartao = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpfPrinc);
				int idcartao = new CartaoDependenteDAO().consultaCartaoDepend(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPendentes(idcartao);
				if (!listaTransacoes.isEmpty()) {
		            saida += "ID Transação\tEstab\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					saida += "\n0 - Sair\n\n";
					saida += "Digite o id da transação que deseja pagar";
					
					int idtransacao = Integer.parseInt(leString2(saida));
					if (idtransacao != 0) {
						
						TransacoesDAO tDAO = new TransacoesDAO();
						if(tDAO.consulta(idtransacao) != null) {
							Double vlrPendente = tDAO.consulta(idtransacao).getValorPendente();
							
							String saida2 = "";
							
							saida2 += "Valor a Pagar: " + vlrPendente + "\n\n";
							saida2 += "1 - Pagar com PIX \n";
							saida2 += "2 - Pagar com Boleto \n";
							saida2 += "0 - Sair \n\n";
							saida2 += "Digite a opção desejada: \n\n";
							
							opPagamento = Integer.parseInt(leString(saida2));
							
							switch (opPagamento) {
							case 1: {
								metodoPagarComPix(numCartao, vlrPendente, idtransacao);
								break;
							} case 2: {
								metodoPagarComBoleto(numCartao, vlrPendente, idtransacao);
								break;
							} case 0: {
								break;
							} default:
								erroOpcao();
							}
						} else {
							JOptionPane.showMessageDialog(null, new JTextArea("Opção inválida!"));
						}
					} else {
						menuCartaoDepend();
					}
				
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}
							
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão Dependente cadastrado!"));
			}
					
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoDeletarCartaoD() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoD(cpf) == true) {
				String numCartao = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpf);
				int idcartao = new CartaoDependenteDAO().consultaCartaoDepend(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPendentes(idcartao);
				if (listaTransacoes.isEmpty()) {
					CartaoDependenteDAO cdDAO = new CartaoDependenteDAO();
					
					if (cdDAO.deletarCartaoDepend(numCartao)) {
						DependenteDAO dDAO = new DependenteDAO();
						String cpfP = dDAO.consultaPrincipal(cpf);
						dDAO.retirarDependente(cpfP, cpf);
						
						JOptionPane.showMessageDialog(null, new JTextArea("Cartão Dependente Nº " + numCartao + " excluído!"));
					}
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Cartão não pode ser excluido pois possui valor pendente de pagamento!"));
				}
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão Dependente cadastrado!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	//------------------------------------------------------------------//
	//						Metodos Estabelecimento                     //
	//------------------------------------------------------------------//
	
	public static boolean metodoconsultaEstab(String estabelecimento) {
		Estabelecimento nomeestabelecimento = new EstabelecimentoDAO().consultaestab(estabelecimento);
		if(nomeestabelecimento != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int metodoInserirEstab(String estab) {
		String estabelecimento = estab;
		EstabelecimentoDAO eDAO = new EstabelecimentoDAO();
		if (metodoconsultaEstab(estabelecimento) == false) {
			Estabelecimento d = new Estabelecimento(estabelecimento);
			eDAO.inserir(d);		
		} 
		int idestabelecimento = eDAO.consultaestab(estabelecimento).getIdestabelecimento();
		return idestabelecimento;
	}
	
	//------------------------------------------------------------------//
	//							Metodo Comprar    	    	            //
	//------------------------------------------------------------------//
	
	public static void metodoComprar() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoP(cpf) == true) {
				String estabelecimento = leString("Digite o nome do estabelecimento");
				int idestabelecimento = metodoInserirEstab(estabelecimento);
				
				String valorStr = leString("Digite o valor");
				double valorDbl = Double.parseDouble(valorStr);
				
				String numCartao = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
				
				double limite = new CartaoPrincipalDAO().consultaLimiteCartaoP(numCartao);
				double saldo = new CartaoPrincipalDAO().consultaSaldoCartaoP(numCartao);
				
				if (valorDbl < limite) {
					if (saldo - valorDbl >= 0) {
						Transacoes t = new Transacoes(1, valorDbl, idestabelecimento, idcartao);
						TransacoesDAO tDAO = new TransacoesDAO();
						tDAO.inserirTransacao(t);
						
						ConectaBD con = new ConectaBD();
						String sql = "UPDATE cartao SET saldo = ? WHERE idcartao = ?;";
						try {
							PreparedStatement pst = con.getConexao().prepareStatement(sql);
							pst.setDouble(1, saldo - valorDbl);
							pst.setInt(2, idcartao);
							pst.execute();
							
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						
						JOptionPane.showMessageDialog(null, new JTextArea("Compra realizada com sucesso!"));
						
					} else {
						JOptionPane.showMessageDialog(null, new JTextArea("Compra não foi realizada, saldo insuficiente!"));
					}
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Compra não foi realizada, limite insuficiente!"));
				}	
				
			} else if (metodoConsultaNumCartaoD(cpf) == true) {
				String estabelecimento = leString("Digite o nome do estabelecimento");
				int idestabelecimento = metodoInserirEstab(estabelecimento);
				
				String valorStr = leString("Digite o valor");
				double valorDbl = Double.parseDouble(valorStr);
				
				String numCartao = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpf);
				int idcartao = new CartaoDependenteDAO().consultaCartaoDepend(numCartao).getIdcartao();
				
				double limite = new CartaoDependenteDAO().consultaLimiteCartaoD(numCartao);
				double saldo = new CartaoDependenteDAO().consultaSaldoCartaoD(numCartao);
				
				if (valorDbl < limite) {
					if (saldo - valorDbl >= 0) {
						Transacoes t = new Transacoes(1, valorDbl, idestabelecimento, idcartao);
						TransacoesDAO tDAO = new TransacoesDAO();
						tDAO.inserirTransacao(t);
						
						JOptionPane.showMessageDialog(null, new JTextArea("Compra realizada com sucesso!"));
						
					} else {
						JOptionPane.showMessageDialog(null, new JTextArea("Compra não foi realizada, saldo insuficiente!"));
					}
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Compra não foi realizada, limite insuficiente!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão criado!"));
			}
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	//------------------------------------------------------------------//
	//						Metodos Transações    	    	            //
	//------------------------------------------------------------------//
	
	public static void metodoConsultaTransacoesPendentes() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoP(cpf) == true) {
				
				String numCartao = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPendentes(idcartao);
				if (!listaTransacoes.isEmpty()) {
					String saida = "";
		            saida += "ID Transação\tEstab\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					JOptionPane.showMessageDialog(null, new JTextArea(saida));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}	
				
			} else if (metodoConsultaNumCartaoD(cpf) == true) {
				
				String numCartao = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpf);
				int idcartao = new CartaoDependenteDAO().consultaCartaoDepend(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPendentes(idcartao);
				if (!listaTransacoes.isEmpty()) {
					String saida = "";
		            saida += "ID Transação\tEstab\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					JOptionPane.showMessageDialog(null, new JTextArea(saida));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão criado!"));
			}	
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultaTransacoesPagas() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoP(cpf) == true) {
				
				String numCartao = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPagas(idcartao);
				if (!listaTransacoes.isEmpty()) {
					String saida = "";
		            saida += "ID Transação\tEstab\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					JOptionPane.showMessageDialog(null, new JTextArea(saida));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}	
				
			} else if (metodoConsultaNumCartaoD(cpf) == true) {
				
				String numCartao = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpf);
				int idcartao = new CartaoDependenteDAO().consultaCartaoDepend(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoesPagas(idcartao);
				if (!listaTransacoes.isEmpty()) {
					String saida = "";
		            saida += "ID Transação\tEstab\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					JOptionPane.showMessageDialog(null, new JTextArea(saida));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão criado!"));
			}	
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
	public static void metodoConsultaTodasTransacoes() {
		String cpf = leString("Digite o CPF");
		cpf = cpf.replace(".", "").replace("-", "");
		if (validaCPF(cpf) == true) {
			if (metodoConsultaNumCartaoP(cpf) == true) {
				
				String numCartao = new CartaoPrincipalDAO().consultaCartaoPrincPorCpf(cpf);
				int idcartao = new CartaoPrincipalDAO().consultaCartaoPrinc(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoes(idcartao);
				if (!listaTransacoes.isEmpty()) {
					String saida = "";
		            saida += "ID Transação\tEstab\tTipo\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						int idtipo = t.getIdtipo();
						String tipo = idtipo == 1 ? "Compra" : "Pagamento";
						saida += tipo + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					JOptionPane.showMessageDialog(null, new JTextArea(saida));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}	
				
			} else if (metodoConsultaNumCartaoD(cpf) == true) {
				
				String numCartao = new CartaoDependenteDAO().consultaCartaoDependPorCpf(cpf);
				int idcartao = new CartaoDependenteDAO().consultaCartaoDepend(numCartao).getIdcartao();
				
				List<Transacoes> listaTransacoes = new TransacoesDAO().consultaTransacoes(idcartao);
				if (!listaTransacoes.isEmpty()) {
					String saida = "";
		            saida += "ID Transação\tEstab\tTipo\tValor\tValor Pago\tValor Pendente\n";
					for (Transacoes t : listaTransacoes) {
						saida += t.getIdtransacao() + "\t";
						String estabelecimento = new EstabelecimentoDAO().consultaNomeEstab(t.getIdestabelecimento());
						saida += estabelecimento + "\t";
						int idtipo = t.getIdtipo();
						String tipo = idtipo == 1 ? "Compra" : "Pagamento";
						saida += tipo + "\t";
						saida += t.getValor() + "\t";
						saida += t.getValorPago() + "\t";
						saida += t.getValorPendente() + "\n";
					}
					
					JOptionPane.showMessageDialog(null, new JTextArea(saida));
					
				} else {
					JOptionPane.showMessageDialog(null, new JTextArea("Sem informação de transações!"));
				}
				
			} else {
				JOptionPane.showMessageDialog(null, new JTextArea("CPF informado não possui Cartão criado!"));
			}	
		} else {
			JOptionPane.showMessageDialog(null, new JTextArea("CPF inválido!"));
		}
	}
	
//------------------------------------------------------------------//
//																	//
//------------------------------------------------------------------//
	
	public static void erroOpcao() {
		JOptionPane.showMessageDialog(null, new JTextArea("Opção Inválida!"));
	}

	public static void main(String[] args) {
		int op;
		
		do {
			op = menu();
			switch (op) {
			
			  //-------------------------------------------------------------//
			 //							Menu Pessoa							//
			//-------------------------------------------------------------//
			
			case 1: {
				op = menuPessoa();
				switch (op) {
				
				case 1: {
					metodoInserirPessoa();
					break;
				} case 2: {
					metodoConsultaPessoa();
					break;
				} case 3: {
					op = menuAtualizaPessoa();
					switch (op) {
					case 1: {
						metodoAtualizaPessoa("cpf");
						break;
					} case 2: {
						metodoAtualizaPessoa("nome");
						break;
					} case 3: {
						metodoAtualizaPessoa("anoNascimento");
						break;
					} case 4: {
						metodoAtualizaPessoa("salario");
						break;
					} case 0: {
						break;
					} default:
						erroOpcao();
					}
					break;
				} case 4: {
					metodoDeletarPessoa();
					break;
				} case 0: {
					break;
				} default:
					erroOpcao();
				}
				break;
			} 
			
			  //-------------------------------------------------------------//
			 //							Menu Conta							//
			//-------------------------------------------------------------//
			
			case 2: {
				op = menuConta();
				switch (op) {
				case 1: {
					metodoInserirConta();
					break;
				} case 2: {
					metodoConsultarConta();
					break;
				} case 3: {
					metodoDeletarConta();
					break;
				} case 0: {
					break;
				} default:
					erroOpcao();
				}
				break;
			}
			
			  //-------------------------------------------------------------//
			 //							Menu Cartão							//
			//-------------------------------------------------------------//
			
			case 3: {
				op = menuCartao();
				switch (op) {
				case 1: {
					op = menuCartaoPrinc();
					switch (op) {
					case 1: {
						metodoInserirCartaoP();
						break;
					} case 2: {
						metodoConsultarCartaoP();
						break;
					} case 3: {
						metodoConsultarLimSalCartaoP();
						break;
					} case 4: {
						metodoConsultaFaturaCartaoP();
						break;
					} case 5: {
						metodoDeletarCartaoP();
						break;
					} case 0: {
						break;
					} default:
						erroOpcao();;
					}
					break;
				} case 2: {
					op = menuCartaoDepend();
					switch (op) {
					case 1: {
						metodoInserirCartaoD();
						break;
					} case 2: {
						metodoConsultarCartaoD();
						break;
					} case 3: {
						metodoConsultarLimSalCartaoD();
						break;
					} case 4: {
						metodoConsultaFaturaCartaoD();
						break;
					} case 5: {
						metodoDeletarCartaoD();
						break;
					} case 0: {
						break;
					} default:
						erroOpcao();;
					}
					break;
				} case 0: {
					break;
				} default:
					erroOpcao();
				}
				break;
			}
			
			  //-------------------------------------------------------------//
			 //						Menu Comprar							//
			//-------------------------------------------------------------//
			
			case 4: {
				metodoComprar();
				break;
			}
			
			  //-------------------------------------------------------------//
			 //						Menu Transações							//
			//-------------------------------------------------------------//
			
			case 5: {
				op = menuTransacoes();
				switch (op) {
				case 1: {
					metodoConsultaTransacoesPendentes();
					break;
				} case 2: {
					metodoConsultaTransacoesPagas();
					break;
				} case 3: {
					metodoConsultaTodasTransacoes();
					break;
				} case 0: {
					break;
				}
				default:
					erroOpcao();
				}
				break;
			}
			
			  //-------------------------------------------------------------//
			 //																//
			//-------------------------------------------------------------//
			
			case 0: {
				break;
			} default:
				erroOpcao();
			}
			
		} while (op != 0);
	}
}
