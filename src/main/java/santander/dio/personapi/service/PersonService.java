package santander.dio.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import santander.dio.personapi.dto.MessageResponseDTO;
import santander.dio.personapi.dto.request.PersonDTO;
import santander.dio.personapi.entity.Person;
import santander.dio.personapi.exception.PersonNotFoundException;
import santander.dio.personapi.mapper.PersonMapper;
import santander.dio.personapi.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream() //não deixa retornar allPeople tem ue converter em uma lista de objeto PersonDTO
                //então abre um stream
                .map(personMapper::toDTO) //com o map para cada um das pessoas vai chamar o método toDTO onde
                //vai converter de entidade para DTO
                .collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));

        //Optional<Person> optionalPerson = personRepository.findById(id); //Optional foi criado no java 8 evita verificações como null



        return personMapper.toDTO(person);

    }
}
