package com.ism.services.interfaces;




import java.util.List;

import com.ism.core.IServices;
import com.ism.entities.Client;

public interface IClientService extends IServices<Client> {
    Client fetchByPhone(String object);
}



