package com.appsnacks.snacks_inventary.repository;

import com.appsnacks.snacks_inventary.models.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iSnackRepository extends JpaRepository<Snack, Integer>{

}
