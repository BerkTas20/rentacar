package com.berktas.rentacar.core.validator;


import com.berktas.rentacar.core.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class IsExistsValidator {
    public static <T> T findById(JpaRepository<T, Long> repository, Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
