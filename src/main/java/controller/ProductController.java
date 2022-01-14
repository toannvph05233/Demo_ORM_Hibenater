package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryService;
import service.ProductService;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("products", productService.getAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }

    @PostMapping ("/create")
    public ModelAndView create(@ModelAttribute("product") Product product, @RequestParam int idCategory){
        Category category = new Category();
        category.setId(idCategory);
        product.setCategory(category);

        product.setStatus(true);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.delete(id);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", productService.findById(id));
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }

    @PostMapping ("/edit")
    public ModelAndView edit(@ModelAttribute("product") Product product, @RequestParam int idCategory){
        Category category = new Category();
        category.setId(idCategory);
        product.setCategory(category);

        product.setStatus(true);
        productService.edit(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }

}
