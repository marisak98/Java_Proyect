package hotel_service2;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marisak
 */

@Entity
@Table(name="USUARIO")

public class Usuarios {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    
    @Column(name = "PASS_USUARIO")
    private String password;
    
    @Column(name = "NICKNAME_USUARIO")
    private String nickname;
    
    @Column(name = "OBS_USUARIO")
    private String observaciones;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String toString(){
    return "Usuario [ID_Usuario= "+idUsuario +", NAME "+nickname+" PASS "+password+"]";
    }
}
