package br.com.sebrae.manter_produto.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Preenchimento do nome é obrigatório")
    @Size(min = 3, max = 255, message = "Campo nome deve conter entre 3 e 255 caracteres")
    private String nome;
    @Size(min = 3, max = 400, message = "Campo descrição deve conter entre 3 e 400 caracteres")
    @NotBlank(message = "Preenchimento da descrição é obrigatório")
    private String descricao;
    @Min(0)
    @NotNull(message = "Preenchimento do preço é obrigatório")
    private BigDecimal preco;
}
