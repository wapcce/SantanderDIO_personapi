package santander.dio.personapi.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;


    @PostMapping//foi modificado para o de @etMapping para @PostMapping
    public MessageResponseDTO createPerson(PersonDTO personDTO){//@RequestBody informa que o objeto está vindo de uma requisição

        Person personToSave =personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
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

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }


    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);


        Person personToUpdate =personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Updated person with ID ");
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
