package santander.dio.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import santander.dio.personapi.dto.MessageResponseDTO;
import santander.dio.personapi.entity.Person;
import santander.dio.personapi.repository.PersonRepository;

@Service //indica ao spring para gerenciar uma classe do tipo serviço onde tem as regras de negócio
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping//foi modificado para o de @etMapping para @PostMapping
    public MessageResponseDTO createPerson(Person person){//@RequestBody informa que o objeto está vindo de uma requisição
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();
    }
}
