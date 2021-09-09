package santander.dio.personapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import santander.dio.personapi.dto.request.PersonDTO;
import santander.dio.personapi.entity.Person;

@Mapper
public interface PersonMapper {



    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);


    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
}
