package service;

import dao.CategoryDao;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> getAll(){
        return categoryDao.getList();
    }
}
