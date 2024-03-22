package com.Alquiler.Alquiler_Vehiculo.model.user;

import java.util.Arrays;
import java.util.List;

public enum Role {

    CUSTOMER(Arrays.asList(Permission.READ_ALL_RESERVAS)),

    ADMIN(Arrays.asList(Permission.READ_ALL_RESERVAS, Permission.SAVE_ONE_RESERVA));

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
