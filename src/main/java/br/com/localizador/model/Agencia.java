package br.com.localizador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "agencia")
public class Agencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pos_x", nullable = false)
    private Integer posX;

    @Column(name = "pos_y", nullable = false)
    private Integer posY;

    protected Agencia() { } // JPA

    public Agencia(Integer posX, Integer posY) { this.posX = posX; this.posY = posY; }

    public Long getId() { return id; }
    public Integer getPosX() { return posX; }
    public Integer getPosY() { return posY; }
    public void setPosX(Integer posX) { this.posX = posX; }
    public void setPosY(Integer posY) { this.posY = posY; }
}

