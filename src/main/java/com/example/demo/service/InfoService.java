/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.dto.InfoDto;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoService {

    public void save(InfoDto dto) throws Exception;

    public Object findAll() throws Exception;
    
    public void delete(int id) throws Exception;
}
