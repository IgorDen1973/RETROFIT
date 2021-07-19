package ru.IgorDen1973.tests;
import com.github.javafaker.Faker;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.IgorDen1973.dto.Category;
import ru.IgorDen1973.dto.NegativeClasses.*;
import ru.IgorDen1973.dto.Product;
import ru.IgorDen1973.enums.CategoryType;
import ru.IgorDen1973.service.CategoryService;
import ru.IgorDen1973.service.ProductService;
import ru.IgorDen1973.utils.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ProductTests {
    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker = new Faker();
    Product product;
    static Integer id;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().vegetable())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.Food.getTitle());
        int max = 9050;
        int min = 8976;
        max -= min;
        id = (int) ((Math.random()* ++max) + min);
    }

    @Test
    void extractListOfAllProductsTest() throws IOException {
        Response<ArrayList<Product>> response = productService.getProducts().execute();
        assertThat(response.body().get(0).getId(),notNullValue());
        assertThat(response.body().get(6).getId(),notNullValue());
    }

    @Test
    void createsNewProductTestPositive() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @Test
    void createsNewProductTestNegativeSetId() throws IOException {
        product.setId(id);
        Response<Product> response = productService.createProduct(product).execute();
        String body2 = response.errorBody().string();
        assertThat(body2,containsString("\"status\":400"));
        assertThat(body2,containsString("Id must be null for new entity"));
    }

    @Test
    void createsNewProductTestNegativeTitleNull() throws IOException {
        product.setTitle(null);
        Response<Product> response = productService.createProduct(product).execute();
        String body2 = response.body().toString();
        assertThat(body2,containsString("title=null"));
        assertThat(response.isSuccessful(),equalTo(true));

    }

    @Test
    void createsNewProductTestNegativeTitleInteger() throws IOException {
        Product2 product2 = new Product2(null,id,id,"Electronic");
        Response<Product2> response = productService.createProduct2(product2).execute();
        String body2 = response.body().toString();
        assertThat(body2,containsString("title="+id));
        assertThat(response.isSuccessful(),equalTo(true));

    }

    @Test
    void createsNewProductTestNegativeTitleBoolean() throws IOException {
        Product3 product3 = new Product3(null,false,id,"Electronic");
        Response<Product3> response = productService.createProduct3(product3).execute();
        String body2 = response.body().toString();
        assertThat(body2,containsString("title=false"));
        assertThat(response.isSuccessful(),equalTo(true));

    }

    @Test
    void createsNewProductTestNegativeTitleDouble() throws IOException {
        Product4 product4 = new Product4(null,12.34,id,"Electronic");
        Response<Product4> response = productService.createProduct4(product4).execute();
        String body2 = response.body().toString();
        assertThat(body2,containsString("title=12.34"));
        assertThat(response.isSuccessful(),equalTo(true));
    }

    @Test
    void createsNewProductTestNegativePriceDouble() throws IOException {
        Product5 product5 = new Product5(null,"Something",16.98,"Electronic");
        Response<Product5> response = productService.createProduct5(product5).execute();
        String body2 = response.body().toString();
        assertThat(body2,containsString("price=16.0")); // "отсекается" целая часть числа
        assertThat(response.isSuccessful(),equalTo(true));
    }

    @Test
    void createsNewProductTestNegativePriceString() throws IOException {
        Product6 product6 = new Product6(null,"Something"," ","Electronic");
        Response<Product6> response = productService.createProduct6(product6).execute();
        String body2 = response.body().toString();
        assertThat(body2,containsString("price=0")); // Пробел преобразуется в ноль
        assertThat(response.isSuccessful(),equalTo(true));
    }

    @Test
    void createsNewProductTestNegativePriceMinus() throws IOException {
        product.setPrice(-9000);
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @Test
    void modifiesProductTest() throws IOException {
        System.out.println(".......Modifying product with id = "+id+"........");
        Response<Product> response = productService.modifyProduct(product.withId(id)).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getId(), equalTo(id));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("\"status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativeNoId() throws IOException {
        System.out.println(".......Modifying product with NO any id ......");
        Product7 product7 = new Product7("Something",id,"Electronic");
        Response<Product7> response = productService.modifyProduct7(product7).execute();
        String body2 = response.errorBody().string();
        assertThat(body2,containsString("\"status\":400"));
        assertThat(body2,containsString("Id must be not null for new entity"));
    }

    @Test
    void modifiesProductTestNegativeTitleNull() throws IOException {
        product.setTitle(null);
        System.out.println(".......Modifying product with title=null......");
        Response<Product> response = productService.modifyProduct(product.withId(id)).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getTitle(), equalTo(null));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativeTitleInteger() throws IOException {
        Product2 product2 = new Product2(id,id,id,"Electronic");
        System.out.println(".......Modifying product with title=Integer......");
        Response<Product2> response = productService.modifyProduct2(product2).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getTitle(), equalTo(id));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativeTitleBoolean() throws IOException {
        Product3 product3 = new Product3(id,true,id,"Electronic");
        System.out.println(".......Modifying product with title= Boolean......");
        Response<Product3> response = productService.modifyProduct3(product3).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getTitle(), equalTo(true));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativeTitleDouble() throws IOException {
        Product4 product4 = new Product4(id,67.81,id,"Electronic");
        System.out.println(".......Modifying product with title= Double......");
        Response<Product4> response = productService.modifyProduct4(product4).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getTitle(), equalTo(67.81));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativePriceDouble() throws IOException {
        Integer id = 9092;
        Product5 product5 = new Product5(id,"Any",87.19,"Food");
        System.out.println(".......Modifying product with price= Double......");
        Response<Product5> response = productService.modifyProduct5(product5).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getPrice(), equalTo(87.0));}  // ОКРУГЛЯЕТ
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativePriceString() throws IOException {
        Integer id = 9092;
        Product6 product6 = new Product6(id,"Any"," ","Food");
        System.out.println(".......Modifying product with price= String......");
        Response<Product6> response = productService.modifyProduct6(product6).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            String body = response.body().toString();
            assertThat(body,containsString("price=0")); // Пробел преобразуется в ноль
            assertThat(response.isSuccessful(),equalTo(true));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void modifiesProductTestNegativePriceMinus() throws IOException {
        product.setPrice(-7500); product.setId(id);
        System.out.println(".......Modifying product with price= minus number......");
        Response<Product> response = productService.modifyProduct(product).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            String body = response.body().toString();
            assertThat(body,containsString("price=-7500")); // Пробел преобразуется в ноль
            assertThat(response.isSuccessful(),equalTo(true));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("status\":400"));
            assertThat(body2,containsString("Product with id: "+id+" doesn't exist"));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void extractsProductByIdTest() throws IOException {
        System.out.println(".......Extracting product with id = "+id+"........");
        Response<Product> response = productService.getProductById(id).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            assertThat(response.body().getId(), equalTo(id));}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("\"status\":404"));
            assertThat(body2,containsString("Unable to find product with id: "+id));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void deletesProductByIdTest() throws IOException {
        Response<ResponseBody> response = productService.deleteProductById(id).execute();
        Boolean result = response.isSuccessful();
        if (result == true) {
            System.out.println(".....DELETING ITEM WITH ID="+id+" HAS BEEN FINISHED  SUCCESSFULY...");}
        else {
            String body2 = response.errorBody().string();
            assertThat(body2,containsString("\"status\":500"));
            assertThat(body2,containsString("\"error\":\"Internal Server Error\""));
            System.out.println(response.errorBody().string());
            System.out.println("............NO ANY ITEM WITH ID = " + id + "  HAS BEEN FOUND...");}
    }

    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.Food.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();
        assertThat(response.body().getTitle(), equalTo(CategoryType.Food.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }
}
