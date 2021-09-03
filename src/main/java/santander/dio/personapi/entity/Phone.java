package santander.dio.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import santander.dio.personapi.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data //inseri os getters e setters
@Builder //padrão de projetos
@AllArgsConstructor //inseri os construtores
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Delega ao banco de dados a geração de valores de PK
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)//cria uma constraint de dados
    private PhoneType type;

    @Column(nullable = false)
    private String number;

}
