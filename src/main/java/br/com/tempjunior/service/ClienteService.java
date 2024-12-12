package br.com.tempjunior.service;

import br.com.tempjunior.dao.ClienteDAO;
import br.com.tempjunior.model.Cliente;

import java.math.BigDecimal;

public class ClienteService {
        public void cadastrarService() {
            try {
                Cliente cliente = new Cliente("Junior Oliveira", new BigDecimal("24"), "1234");
                Integer result = cadastrarCliente(cliente);
                System.out.println("Resultado: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void buscarTodosService(){
            ClienteDAO clienteDAO = new ClienteDAO(); // Cria uma instância de ClienteDAO
            try {
                clienteDAO.buscarTodos().forEach(System.out::println);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private Integer cadastrarCliente(Cliente cliente) throws Exception {
            ClienteDAO clienteDAO = new ClienteDAO(); // Cria uma instância de ClienteDAO
            return clienteDAO.cadastrar(cliente); // Chama o método não estático através da instância
        }

        public void atualizaService(){
            Cliente cliente = new Cliente("Bruno santos", new BigDecimal("22"), "242469");
            Integer result = null;
            try {
                result = atualizaCliente(cliente);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Resultado" + result);
        }

        private Integer atualizaCliente(Cliente cliente) throws Exception {
            ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.atualizar(cliente);
        }

        public void excluirService(){
            Cliente cliente = new Cliente("Bruno santos", new BigDecimal("22"), "242469");
            Integer result = null;

            try {
                result = excluiCliente(cliente);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Resultado" + result);
        }

        private Integer excluiCliente(Cliente cliente) throws Exception {
            ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.excluir(cliente);
        }

        public void buscarClienteService(String codigo){
            Cliente cliente = new Cliente();
            try {
                buscaCliente(codigo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private Cliente buscaCliente(String codigo) throws Exception{
            ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.buscar(codigo);
        }
    }
