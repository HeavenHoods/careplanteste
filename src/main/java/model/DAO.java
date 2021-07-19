package model;

import com.fasterxml.classmate.AnnotationConfiguration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	private Connection conectar() {
		Connection con = null;
		try {
            
                    if(!checkFileExists()){
                        createNewFile();
                    }

                    con = DriverManager.getConnection("jdbc:h2:tcp:" + "//localhost/C:/data/fileDb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");
                    return con;			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
        
        private boolean checkFileExists(){
            File h2DataFolder = new File("C:/data/fileDb.mv.db");             
            return h2DataFolder.exists();
        }
        
        private void createNewFile() throws IOException{
            File file = new File("C:\\data\\fileDb.mv.db");
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
        }
        
        private static SessionFactory buildSessionFactory(Class clazz){
            return new Configuration()
                    .configure()
                    .addAnnotatedClass(clazz)
                    .buildSessionFactory();
        }
        
        public Procedimentos insertAndGetTest(String proc){
                Procedimentos procedimento = new Procedimentos();
                procedimento.setProcedimento("12345");
                procedimento.setIdade("10");
                procedimento.setSexo("M");
                procedimento.setPermitido(true);
               
                SessionFactory sf = buildSessionFactory(Procedimentos.class);
                Session session = sf.openSession();
                
                
                session.save(procedimento);
                
                Procedimentos proc1 = session.get(Procedimentos.class, 1);//1 is expected id
                System.out.println("Procedimento: " + proc1.getProcedimento());
                System.out.println("Idade: " + proc1.getIdade());
                System.out.println("Sexo: " + proc1.getSexo());
                System.out.println("Permitido: " + proc1.getPermitido());
                
                session.close();
                sf.close();
                
                return proc1;            
        }
        
        public void firstInsertTable(){
            List<Map<String, Object>> listOfProcedures = new ArrayList<>();
            Map<String, Object> procedure = new HashMap<String, Object>();
            
            procedure.put("procedimento", "1234");
            procedure.put("idade", 10);
            procedure.put("sexo", "M");
            procedure.put("permitido", false);
            procedure.put("executado", false);
            
            listOfProcedures.add(procedure);
            procedure = new HashMap<>();
            
            procedure.put("procedimento", "4567");
            procedure.put("idade", 20);
            procedure.put("sexo", "M");
            procedure.put("permitido", true);
            procedure.put("executado", false);

            listOfProcedures.add(procedure);
            procedure = new HashMap<>();        
            
            procedure.put("procedimento", "6789");
            procedure.put("idade", 10);
            procedure.put("sexo", "F");
            procedure.put("permitido", false);
            procedure.put("executado", false);

            listOfProcedures.add(procedure);
            procedure = new HashMap<>();         
           
                        
            procedure.put("procedimento", "6789");
            procedure.put("idade", 10);
            procedure.put("sexo", "M");
            procedure.put("permitido", true);
            procedure.put("executado", false);

            listOfProcedures.add(procedure);
            procedure = new HashMap<>();          
            
                        
            procedure.put("procedimento", "1234");
            procedure.put("idade", 20);
            procedure.put("sexo", "M");
            procedure.put("permitido", true);
            procedure.put("executado", false);

            listOfProcedures.add(procedure);
            procedure = new HashMap<>();
            
                       
            procedure.put("procedimento", "4567");
            procedure.put("idade", 30);
            procedure.put("sexo", "F");
            procedure.put("permitido", true);
            procedure.put("executado", false);

            listOfProcedures.add(procedure);
            procedure = new HashMap<>();


            SessionFactory sf = buildSessionFactory(Procedimentos.class);
                        
            listOfProcedures.forEach((Map<String, Object> proc) -> {
                Session session = sf.openSession();
                Procedimentos procedimento = new Procedimentos();
                procedimento.setProcedimento(String.valueOf(proc.get("procedimento")));
                procedimento.setIdade(String.valueOf(proc.get("idade")));
                procedimento.setSexo(String.valueOf(proc.get("sexo")));
                procedimento.setPermitido(Boolean.valueOf(proc.get("permitido").toString()));
                procedimento.setExecutado(false);

                session.save(procedimento);
                session.close();
            });            
            sf.close();                                                    
        }

	public void inserirProcedimento(Procedimentos proc) {
                Procedimentos procedimento = new Procedimentos();
                procedimento.setProcedimento(proc.getProcedimento());
                procedimento.setIdade(proc.getIdade());
                procedimento.setSexo(proc.getSexo());
                procedimento.setPermitido(proc.getPermitido());
                procedimento.setExecutado(false);
               
                SessionFactory sf = buildSessionFactory(Procedimentos.class);
                
                Session session = sf.openSession();
                session.save(procedimento);
                
                session.close();
                sf.close();
            }

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<Procedimentos> listarProcedimentos() throws SQLException {
                        
            //Connection conn = DriverManager.getConnection("jdbc:h2:tcp:" + "//localhost/C:/data/fileDb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");
            Connection conn = conectar(); //DriverManager.getConnection("jdbc:h2:" + "~/data;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");
            ResultSet rscon = conn.createStatement().executeQuery("SELECT * FROM procedimentos");            
            rscon.first();
            ArrayList<Procedimentos> procedimentos = new ArrayList<>();
            while(rscon.next()){
                Integer idcon = rscon.getInt("IDCON");
                String procedimento = rscon.getString("PROCEDIMENTO");
                String idade = rscon.getString("IDADE");
                String sexo = rscon.getString("SEXO");
                Boolean permitido = rscon.getBoolean("PERMITIDO");
                Boolean executado = rscon.getBoolean("EXECUTADO");
                procedimentos.add(new Procedimentos(idcon, procedimento, idade, sexo, permitido, executado));
            }            
            conn.close();
            return procedimentos;                
	}
        
        public Integer getRowsCount() throws SQLException{
            //Connection conn = DriverManager.getConnection("jdbc:h2:tcp:" + "//localhost/C:/data/fileDb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");                                            
            //DriverManager.getConnection("jdbc:h2:" + "~/data;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");
            Connection conn = conectar();
            try {
                ResultSet rscon = conn.createStatement().executeQuery("SELECT * FROM procedimentos");
                rscon.last();
                return  rscon.getRow();
            } catch(SQLException ex) {
                return 0;
            }
        }

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(Procedimentos procedimento) throws SQLException {
            //Connection conn = DriverManager.getConnection("jdbc:h2:tcp:" + "//localhost/C:/data/fileDb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");
            Connection conn = conectar();//DriverManager.getConnection("jdbc:h2:" + "~/data;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "sa", "sa");
            String getProcQuery = "select * from procedimentos where idcon = ?";
            PreparedStatement pst = conn.prepareStatement(getProcQuery);
            pst.setInt(1, procedimento.getIdcon());
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                procedimento.setIdcon(rs.getInt("IDCON"));
                procedimento.setProcedimento(rs.getString("PROCEDIMENTO"));
                procedimento.setIdade(rs.getString("IDADE"));
                procedimento.setSexo(rs.getString("SEXO"));
                procedimento.setPermitido(rs.getBoolean("PERMITIDO"));
            }            
            conn.close();           
	}

	public void alterarProcedimento(Procedimentos procedimento) {
		String update = "update procedimentos set procedimento=?,idade=?,sexo=?, permitido=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, procedimento.getProcedimento());
			pst.setString(2, procedimento.getIdade());
			pst.setString(3, procedimento.getSexo());
                        pst.setBoolean(4, procedimento.getPermitido());
			pst.setInt(5, procedimento.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletarProcedimento(Procedimentos procedimento) {
		String delete = "delete from procedimentos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, procedimento.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
        
        public Boolean execProcedimento(Procedimentos procedimento) throws SQLException{
            Connection con = conectar();
            String getPermite = "select permitido from procedimentos where idcon=?";
            PreparedStatement pstGetPermite = con.prepareStatement(getPermite);
            pstGetPermite.setInt(1, procedimento.getIdcon());
            ResultSet rsGetPermite = pstGetPermite.executeQuery();
            try {
                while(rsGetPermite.next()){
                    String update = "update procedimentos set executado=? where idcon=?";
                    if(rsGetPermite.getBoolean("PERMITIDO")){
                        PreparedStatement pst = con.prepareStatement(update);
                        pst.setBoolean(1, true);                    
                        pst.setInt(2, procedimento.getIdcon());
                        pst.executeUpdate();
                        con.close();
                        return true;
                    } else {
                        return false;
                    }
                }
            
                      
            

            } catch (Exception e) {
                    System.out.println(e);
            }
            
            return false;
        }

}
