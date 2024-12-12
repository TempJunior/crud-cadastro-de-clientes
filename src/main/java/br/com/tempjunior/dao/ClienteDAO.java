package br.com.tempjunior.dao;

import br.com.tempjunior.connectionFactory.ConnectionFactory;
import br.com.tempjunior.model.Cliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDeInsert(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            connection.close();
            stm.close();
        }

    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLUpdate();
            stm = connection.prepareStatement(sql);
            adicionaParametroDeUpdate(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            stm.close();
            connection.close();
        }
    }

    @Override
    public Cliente buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
        connection = ConnectionFactory.getConnection();
        String sql = getSQLSelect();
        stm = connection.prepareStatement(sql);
        adicionaParametroSelect(stm, codigo);
        rs = stm.executeQuery();

        if (rs.next()){
            cliente = new Cliente();
            Long id = rs.getLong("ID");
            String nome = rs.getString("NOME");
            BigDecimal idade = rs.getBigDecimal("IDADE");
            String cod = rs.getString("CODIGO");

            cliente.setId(id);
            cliente.setNome(nome);
            cliente.setIdade(idade);
            cliente.setCodigo(cod);

            System.out.println(cliente);
        }
        }catch (Exception e){
            throw e;
        }
        finally {
            stm.close();
            connection.close();
            rs.close();
        }
        return cliente;

    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSQLBuscarTodos();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                BigDecimal idade = rs.getBigDecimal("IDADE");
                String cod = rs.getString("CODIGO");

                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setIdade(idade);
                cliente.setCodigo(cod);

                listaClientes.add(cliente);

            }
        }catch (Exception e){
            throw e;
        }
        finally {
            stm.close();
            connection.close();
            rs.close();
        }
        return listaClientes;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnection();
            String sql = getSQLDelete();
            stm = connection.prepareStatement(sql);
            adicionaParametroDelete(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        finally {
            stm.close();
            connection.close();
        }
    }

    private String getSQLSelect(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append("FROM cadastros ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }

    private String getSQLDelete(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM cadastros ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }

    private String getSQLUpdate(){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE cadastros ");
        sb.append("SET NOME = ?, CODIGO = ?");
        sb.append("WHERE ID = 1");
        return sb.toString();
    }

    private String getSQLBuscarTodos(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM cadastros");
        return sb.toString();
    }

    private void adicionaParametroSelect(PreparedStatement stm, String codigo) throws Exception{
        stm.setString(1, codigo);
    }

    private void adicionaParametroDelete(PreparedStatement stm, Cliente cliente) throws Exception{
        stm.setString(1, cliente.getCodigo());
    }

    private void adicionaParametroDeUpdate(PreparedStatement stm, Cliente cliente) throws Exception{
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCodigo());
    }

    private void adicionarParametrosDeInsert(PreparedStatement stm, Cliente cliente) throws Exception{
        stm.setString(1, cliente.getNome());
        stm.setBigDecimal(2, cliente.getIdade());
        stm.setString(3, cliente.getCodigo());
    }

    private String getSQLInsert(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO cadastros (NOME, IDADE, CODIGO)");
        sb.append("VALUES (?, ?, ?)");
        return sb.toString();
    }
}
