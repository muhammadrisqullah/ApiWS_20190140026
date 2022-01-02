/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.exercise.apiws;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findByIdGame", query = "SELECT g FROM Game g WHERE g.idGame = :idGame"),
    @NamedQuery(name = "Game.findByNama", query = "SELECT g FROM Game g WHERE g.nama = :nama"),
    @NamedQuery(name = "Game.findByStockgame", query = "SELECT g FROM Game g WHERE g.stockgame = :stockgame"),
    @NamedQuery(name = "Game.findByHarga", query = "SELECT g FROM Game g WHERE g.harga = :harga")})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_game")
    private Integer idGame;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Stock_game")
    private Integer stockgame;
    @Column(name = "Harga")
    private Integer harga;

    public Game() {
    }

    public Game(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getStockgame() {
        return stockgame;
    }

    public void setStockgame(Integer stockgame) {
        this.stockgame = stockgame;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGame != null ? idGame.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.idGame == null && other.idGame != null) || (this.idGame != null && !this.idGame.equals(other.idGame))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.service.exercise.apiws.Game[ idGame=" + idGame + " ]";
    }
    
}
