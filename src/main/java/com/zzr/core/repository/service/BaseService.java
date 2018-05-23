package com.zzr.core.repository.service;

import java.util.List;

public interface BaseService<T> {
      
    T add(T o);
      
    void del(Long id);

    void delList(List<Long> ids);
      
    List<T> findAll();
      
    T getById(Long id);

    T update(T o);
  
}  