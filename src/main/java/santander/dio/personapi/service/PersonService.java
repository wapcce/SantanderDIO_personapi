package santander.dio.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import santander.dio.personapi.dto.MessageResponseDTO;
import santander.dio.personapi.dto.request.PersonDTO;
import santander.dio.personapi.entity.Person;
import santander.dio.personapi.mapper.PersonMapper;
import santander.dio.personapi.repository.PersonRepository;

@Service //indica ao spring para gerenciar uma classe do tipo serviço onde tem as regras de negócio
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping//foi modificado para o de @etMapping para @PostMapping
    public MessageResponseDTO createPerson(PersonDTO personDTO){//@RequestBody informa que o objeto está vindo de uma requisição

        Person personToSave =personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
