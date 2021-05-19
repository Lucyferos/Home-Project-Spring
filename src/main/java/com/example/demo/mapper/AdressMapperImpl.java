package com.example.demo.mapper;

import com.example.demo.dto.AdressDTO;
import com.example.demo.model.Adress;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-03-11T19:21:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)

@Component
public class AdressMapperImpl implements AdressMapper{
    @Override
    public Adress adressDtoToAdress(AdressDTO adressDTO) {
        if( adressDTO ==null){
            return null;
        }
        Adress adress = new Adress();
        adress.setId(adressDTO.getId());
        adress.setCity(adressDTO.getCity());
        adress.setCountry(adressDTO.getCountry());
        adress.setStreet(adressDTO.getStreet());
        adress.setZip(adressDTO.getZip());
        return adress;
    }
}
