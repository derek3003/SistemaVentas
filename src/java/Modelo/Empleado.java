
package Modelo;

public class Empleado {
    int id;
    String dni;
    String nom;
    String tel;
    String user;
    String Estado;
    public Empleado() {
    }

    public Empleado(int id, String dni, String nom, String tel, String user,String Estado) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.tel = tel;
        this.user = user;
        this.Estado=Estado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
