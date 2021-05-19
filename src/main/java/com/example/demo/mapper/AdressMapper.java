package com.example.demo.mapper;

import com.example.demo.dto.AdressDTO;
import com.example.demo.model.Adress;

public interface AdressMapper {
    Adress adressDtoToAdress(AdressDTO adressDTO);
}
