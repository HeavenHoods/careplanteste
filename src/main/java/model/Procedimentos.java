package model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Procedimentos implements Serializable {
	
	/** The idcon. */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcon;
	
	/** The Procedure. */
	private String procedimento;
	
	/** The age. */
	private String idade;
	
	/** The gender. */
	private String sexo;
        
        /** The allowed. */
	private boolean permitido;
        
        private boolean executado;

	public Procedimentos() {
		super();
	}

	/**
	 * Instantiates 
	 *
	 * @param idcon the idcon
	 * @param procedimento the nome
	 * @param idade the fone
	 * @param sexo the email
         * @param permitido the email
         * @param executado
	 */
	public Procedimentos(Integer idcon, String procedimento, String idade, String sexo, Boolean permitido, Boolean executado) {
		super();
		this.idcon = idcon;
		this.procedimento = procedimento;
		this.idade = idade;
                this.sexo = sexo;
		this.permitido = permitido;
                this.executado = executado;
	}

	/**
	 * Gets the idcon.
	 *
	 * @return the idcon
	 */
	public Integer getIdcon() {
		return idcon;
	}

	/**
	 * Sets the idcon.
	 *
	 * @param idcon the new idcon
	 */
	public void setIdcon(Integer idcon) {
		this.idcon = idcon;
	}
        
            public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getPermitido() {
        return permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }
    
    public boolean isExecutado() {
        return executado;
    }

    public void setExecutado(boolean executado) {
        this.executado = executado;
    }

}
