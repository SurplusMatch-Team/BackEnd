package tr.edu.agu.cs.surplus_match.controller;

<<<<<<< HEAD
=======
import org.springframework.http.ResponseEntity;
>>>>>>> origin/muhammet
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
<<<<<<< HEAD
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
=======
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
>>>>>>> origin/muhammet
