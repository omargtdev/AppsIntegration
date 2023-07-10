package pe.isil.microservicios_2978.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.microservicios_2978.model.Curso;
import pe.isil.microservicios_2978.repository.CursoRepository;

import java.util.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/getAll") //Obtiene todos los cursos de la base de datos
    public List<Curso> getAll()
    {
        return cursoRepository.findAll();//select * from curso
    }

    @GetMapping("/getById/{id}") //Obtiene un curso por su ID
    public Optional<Curso> getById(@PathVariable ("id") Integer id)
    {
        return cursoRepository.findById(id);
    }

    @PostMapping("/store") //Registrar un nuevo curso en la base de datos
    public Curso store(@RequestBody Curso curso)
    {
        curso.setId(0); //me aseguro que registre un nuevo curso
        return cursoRepository.save(curso);
    }
    @PutMapping("/update/{id}") //Actualiza los datos de un curso existente en la base de datos por el ID del curso
    public Curso update(@RequestBody Curso curso, @PathVariable ("id") Integer id)
    {
        //1. Obtener el registro x id
        Curso cursoFromDB = cursoRepository.getById(id);

        //2. asignar los nuevos valores a los campos
        cursoFromDB.setNRC(curso.getNRC());
        cursoFromDB.setNombre(curso.getNombre());

        //3. ejecutar el metodo save
        return cursoRepository.save(cursoFromDB);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Curso> delete(@PathVariable ("id") Integer id)
    {
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build(); //mensaje al servidor
    }












/*    private static Map<Integer, Curso> cursos = new HashMap<>(); //listado cursos en ememoria

    //POST - Agregar: localhost:8080/curso
    @PostMapping("/curso")
    public String agregar(@RequestBody Curso curso){
        cursos.put(curso.getId(), curso);
        return "";
    }

    //GET - Obtener - Seleccionar localhost:8080/curso
    @GetMapping("/curso")
    public List<Curso> obtener(){
        return new ArrayList<Curso>(cursos.values());
    }
*/
}
