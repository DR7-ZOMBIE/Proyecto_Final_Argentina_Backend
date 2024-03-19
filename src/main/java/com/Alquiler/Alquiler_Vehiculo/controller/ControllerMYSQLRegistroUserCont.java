package com.Alquiler.Alquiler_Vehiculo.controller;


import com.Alquiler.Alquiler_Vehiculo.dto.UsuarioDTO;
import com.Alquiler.Alquiler_Vehiculo.services.IUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class ControllerMYSQLRegistroUserCont {

    private IUsuarioServices usuarioServicio;

    public ControllerMYSQLRegistroUserCont(IUsuarioServices usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioDTO registroDTO) {
        String password = registroDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        registroDTO.setPassword(encodedPassword);
        usuarioServicio.guardar(registroDTO);
        return "redirect:/registro?exito";
    }
}

