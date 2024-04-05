package com.Alquiler.Alquiler_Vehiculo.register;

import com.Alquiler.Alquiler_Vehiculo.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDAOVehiculo extends JpaRepository<Vehiculo, Long> {
    List<Vehiculo> findByIsFavorito(boolean isFavorito); // Retorna una lista de vehículos favoritos
}
