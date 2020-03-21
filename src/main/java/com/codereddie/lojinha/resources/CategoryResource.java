package com.codereddie.lojinha.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.dto.CategoryDTO;
import com.codereddie.lojinha.services.CategoryService;

/**
 * @author akon
 *
 */
@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
 
	@Autowired
	private CategoryService categoryService;
	
	/** ============================================================= INICIO GETS =====================================================
	 **/
	
	 /** 
	 * Método retorna somente as categorias, sem produtos e afins
	 * @author Edcleidson Júnior
	 * @noParam
	 * @return Lista de objetos DTO - retorna a lista de objetos com os dados requisitados
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Category> categories = categoryService.findAll();
		List<CategoryDTO> dtoList = categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
		
	}
	

	 /** 
	 * Método retorna categorias e seus dependentes
	 * @author Edcleidson Júnior
	 * @param Integer - ID
	 * @return Objeto categoria não DTO - Retorna um objeto categoria normal com suas listas e dependentes
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Category category = categoryService.findByID(id);
		return ResponseEntity.ok().body(category);
		
	}

	 /** 
	 * Recebe parâmetros para realizar uma busca paginada
	 * @author Edcleidson Júnior
	 * 
	 * @return Uma página com os dados buscados, apenas dados no padrão DTO implementado
	 * Não traz produtos e dependentes
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET )
	public ResponseEntity<Page> findPage(
			@RequestParam(name="page", defaultValue="0") Integer index, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(name="direction", defaultValue="ASC") String direction) {
		
		Page<Category> categories = categoryService.findPage(index, linesPerPage, orderBy, direction);
		Page<CategoryDTO> dtoList = categories.map(category -> new CategoryDTO(category));
		
		return ResponseEntity.ok().body(dtoList);
		
	}
	
	/** ============================================================= FIM GETS =====================================================
	 **/
	
	/** ==================================================== INICIO POST, PUT DELETE ================================================
	 **/
	
	 /** 
	 * Recebe e insere no banco um conjunto de dados da categoria via objeto DTO
	 * @author Edcleidson Júnior
	 * @param Objeto DTO - Categoria
	 * @return URI - Endereço para acesso do objeto selecionado
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO){
	
		Category category = categoryService.fromDTO(categoryDTO);
		
		categoryService.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	 /** 
	 * Recebe e atualiza no banco um conjunto de dados da categoria via objeto DTO
	 * @author Edcleidson Júnior
	 * @param Objeto DTO - Categoria
	 * @param Integer - ID
	 * @return ?
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO){
		categoryDTO.setId(id);
		
		Category category = categoryService.fromDTO(categoryDTO);
		categoryService.update(category);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	 /** 
	 * Deleta no banco um objeto pelo ID
	 * @author Edcleidson Júnior
	 * @param Integer - ID
	 * @return ?
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		
		categoryService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	/** ==================================================== FIM POST, PUT DELETE ================================================
	 **/
	
	
	
	
	
}
