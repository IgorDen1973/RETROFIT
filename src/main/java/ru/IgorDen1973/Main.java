package ru.IgorDen1973;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.IgorDen1973.db.dao.CategoriesMapper;
import ru.IgorDen1973.db.dao.ProductsMapper;
import ru.IgorDen1973.db.model.Categories;
import ru.IgorDen1973.db.model.CategoriesExample;
import ru.IgorDen1973.db.model.Products;
import ru.IgorDen1973.utils.DbUtils;

import java.io.IOException;

@Slf4j
public class Main {
    static Faker faker = new Faker();
    private static String resource = "mybatisConfig.xml";
    static ProductsMapper productsMapper = DbUtils.getProductsMapper();


    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();
        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
        ProductsMapper productMapper = sqlSession.getMapper(ProductsMapper.class);

        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
       log.info(String.valueOf(categoriesCount));

        Products prods = productMapper.selectByPrimaryKey(8961);
        log.info(prods.getTitle());

 //      createNewCategory(categoriesMapper);


    //    System.out.println(title);




    }

    private static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());

        categoriesMapper.insert(newCategory);
    }

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }
}
