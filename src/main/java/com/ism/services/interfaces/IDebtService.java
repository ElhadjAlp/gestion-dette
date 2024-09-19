package com.ism.services.interfaces;





import java.util.List;

import com.ism.core.IServices;
import com.ism.entities.Debt;

public interface IDebtService extends IServices<Debt> {
    Debt fetchById(List<Debt> list, int object);
}
