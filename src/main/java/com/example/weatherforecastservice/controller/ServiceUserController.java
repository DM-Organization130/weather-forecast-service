package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.ServiceUser;
import com.example.weatherforecastservice.repository.ServiceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ServiceUserController {
    @Autowired
    private ServiceUserRepository serviceUserRepository;

    @PostMapping("/addserviceuser")
    public ServiceUser AddServiceUser (String userKey, @RequestBody ServiceUser serviceUser)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte)(serviceUser.getUserType() + (byte) 1), ServiceUser.adminKey))
        {
            serviceUser.setAPIKey(CreateApiKey());
            return serviceUserRepository.save(serviceUser);
        }
        return null;
    }

    @GetMapping("/listserviceuser")
    public List<ServiceUser> ListServiceUser (String userKey)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return null;
        }
        return serviceUserRepository.findAll();
    }

    @GetMapping("/deleteserviceuser")
    public void deleteServiceUser (String userKey, Long id)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return;
        }
        serviceUserRepository.deleteById(id);
    }

    private String CreateApiKey()
    {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        System.out.println("UUID=" + randomUUIDString );
        return randomUUIDString;
    }

}
