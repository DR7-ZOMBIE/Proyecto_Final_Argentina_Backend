package com.Alquiler.Alquiler_Vehiculo.model.user;

import java.util.Arrays;
import java.util.List;

public enum Role {

    CUSTOMER(Arrays.asList(Permission.READ_ALL_RESERVAS, Permission.READ_ALL_USUARIOS, Permission.READ_ALL_METODOS_PAGO,
            Permission.READ_ALL_CATEGORY, Permission.READ_ALL_VEHICULOS)),

    ADMIN(Arrays.asList(Permission.READ_ALL_RESERVAS, Permission.SAVE_ONE_RESERVA, Permission.DELETE_ONE_RESERVA,
            Permission.UPDATE_ONE_RESERVA, Permission.READ_ONE_RESERVA, Permission.READ_ALL_USUARIOS,
            Permission.SAVE_ONE_USUARIO, Permission.DELETE_ONE_USUARIO, Permission.UPDATE_ONE_USUARIO,
            Permission.READ_ONE_USUARIO, Permission.READ_ALL_METODOS_PAGO, Permission.SAVE_ONE_METODO_PAGO,
            Permission.DELETE_ONE_METODO_PAGO, Permission.UPDATE_ONE_METODO_PAGO, Permission.READ_ONE_METODO_PAGO,
            Permission.READ_ALL_CATEGORY, Permission.SAVE_ONE_CATEGORY, Permission.DELETE_ONE_CATEGORY,
            Permission.UPDATE_ONE_CATEGORY, Permission.READ_ONE_CATEGORY, Permission.READ_ALL_VEHICULOS,
            Permission.SAVE_ONE_VEHICULO, Permission.DELETE_ONE_VEHICULO, Permission.UPDATE_ONE_VEHICULO,
            Permission.READ_ONE_VEHICULO));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    //set
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
