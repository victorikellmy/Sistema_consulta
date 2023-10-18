

package com.consulta.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consulta.consulta.model.repository.ConsultaRepository;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

  @Autowired
  private ConsultaRepository consultaRepository;

  @GetMapping("/list")
  public String listar(ModelMap model) {
    model.addAttribute("consultas", consultaRepository.consultas());
    return "/consulta/list";
  }

//  @GetMapping("/listpac")
//  public String listpac(ModelMap model) {
//    model.addAttribute("consultas", consultaRepository.consultas());
//    return "/templates/paciente/listConsultaPac";
//  }
//
//  @GetMapping("/listmed")
//  public String listmed(ModelMap model) {
//    model.addAttribute("consultas", consultaRepository.consultas());
//    return "/templates/medico/listConsultaMed";
//  }

    // Endpoint para buscar consultas de um médico específico pelo ID do médico
    @GetMapping("/medico/{medicoId}")
    public ModelAndView consultasDeMedico(@PathVariable ("medicoId") Long medicoId, ModelMap model) {
        model.addAttribute("consultas",consultaRepository.consultasDeMedico(medicoId));
        return new ModelAndView("/medico/listConsultaMed", model);
    }
  @GetMapping("/paciente/{pacienteId}")
  public ModelAndView consultasDePaciente(@PathVariable ("pacienteId") Long pacienteId, ModelMap model) {
      model.addAttribute("consultas",consultaRepository.consultasDePaciente(pacienteId));
      return new ModelAndView("/paciente/listConsultaPac", model);
  }
}