package com.example.demo.mapper;

import com.example.demo.dto.AdressDTO;
import com.example.demo.model.Adress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdressMapper {
    Adress adressDtoToAdress(AdressDTO adressDTO);
    AdressDTO adressToAdressDTO(Adress adress);
}
