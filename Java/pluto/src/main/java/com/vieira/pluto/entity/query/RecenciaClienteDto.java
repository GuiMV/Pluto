package com.vieira.pluto.entity.query;

import javax.persistence.*;
import java.util.Date;

@Entity
@SqlResultSetMapping(name = "RecenciaClienteDto", entities = {@EntityResult(entityClass = RecenciaClienteDto.class)})
public class RecenciaClienteDto {

    @Id
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
