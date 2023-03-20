package br.com.fiap.domain.equipamento.model;

import br.com.fiap.domain.cliente.model.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "tb_equipamento")
public class Equipamento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_equipamento"
    )
    @SequenceGenerator(
            name = "seq_equipamento",
            sequenceName = "seq_equipamento",
            allocationSize = 1
    )
    @Column(name = "id_equipamento")
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(
            name = "id_tp_equipamento",
            referencedColumnName = "id_tp_equipamento",
            foreignKey = @ForeignKey(
                    name = "fk_tb_tp_equipamento",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private TipoEquipamento tipo;

    @NotNull(message = "O nome do equipamento não pode ser nulo.")
    @Column(name = "nm_equipamento")
    private String nome;

    @NotNull(message = "O número de série não pode ser nulo.")
    @Column(name = "nmr_serie_equipamento")
    private String numeroDeSerie;

    @NotNull(message = "Todo equipamento deve pertencer a um cliente.")
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(
            name = "id_cliente",
            referencedColumnName = "id_cliente",
            foreignKey = @ForeignKey(
                    name = "fk_tb_cliente",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Cliente cliente;

    public Equipamento() {}

    public Equipamento(Long id, TipoEquipamento tipo, String nome, String numeroDeSerie) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.numeroDeSerie = numeroDeSerie;
    }

    public Equipamento(TipoEquipamento tipo, String nome, String numeroDeSerie) {
        this.tipo = tipo;
        this.nome = nome;
        this.numeroDeSerie = numeroDeSerie;
    }

    public Long getId() {
        return id;
    }

    public Equipamento setId(Long id) {
        this.id = id;

        return this;
    }

    public TipoEquipamento getTipo() {
        return tipo;
    }

    public Equipamento setTipo(TipoEquipamento tipo) {
        this.tipo = tipo;

        return this;
    }

    public String getNome() {
        return nome;
    }

    public Equipamento setNome(String nome) {
        this.nome = nome;

        return this;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public Equipamento setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipamento that = (Equipamento) o;

        return Objects.equals(id, that.id) && Objects.equals(tipo, that.tipo) && Objects.equals(nome, that.nome) && Objects.equals(numeroDeSerie, that.numeroDeSerie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nome, numeroDeSerie);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Equipamento {");
        sb.append("id = ").append(id);
        sb.append(", tipo = ").append(tipo);
        sb.append(", nome = '").append(nome).append('\'');
        sb.append(", numeroDeSerie = '").append(numeroDeSerie).append('\'');
        sb.append('}');

        return sb.toString();
    }
}
