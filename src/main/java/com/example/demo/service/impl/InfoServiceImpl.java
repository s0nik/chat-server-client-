
package com.example.demo.service.impl;

import com.example.demo.dao.InfoDao;
import com.example.demo.dto.InfoDto;
import com.example.demo.model.Info;
import com.example.demo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoDao infoDaoImp;

    @Override
    public void save(InfoDto dto) throws Exception {
        Info info = new Info();
        info.setFirstName(dto.getFirstName());
        info.setLastName(dto.getLastName());
        info.setEmail(dto.getEmail());
        infoDaoImp.save(info);
    }

    @Override
    public Object findAll() throws Exception {
        return infoDaoImp.findAll();
    }

    @Override
    public void delete(int id) throws Exception {
        infoDaoImp.delete(id);
    }

}
