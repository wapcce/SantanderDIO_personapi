package santander.dio.personapi.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import santander.dio.personapi.dto.MessageResponseDTO;
import santander.dio.personapi.dto.request.PersonDTO;
import santander.dio.personapi.entity.Person;
import santander.dio.personapi.exception.PersonNotFoundException;
import santander.dio.personapi.repository.PersonRepository;
import santander.dio.personapi.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor =@__(@Autowired)) //conseguimos tirar o construtor padrão
public class PersonController {

    private PersonRepository personRepository;

    /*@Autowired // informa o spring para injetar uma implementação tipo repository para dentro, fazendo isso em um construtor facilita criar testes unitários
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/

    private PersonService personService;


    /*@PostMapping//foi modificado para o de @etMapping para @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){//@RequestBody informa que o objeto está vindo de uma requisição
      Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){

        return personService.createPerson(personDTO);
       }

       @GetMapping
       public List<PersonDTO> listAll(){
        return personService.listAll();

       }

       @PutMapping("/{id}")
       public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return  personService.updateById(id, personDTO);

       }

       @GetMapping("/{id}")
       public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
       }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
