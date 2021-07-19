package ru.IgorDen1973.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.IgorDen1973.dto.NegativeClasses.*;
import ru.IgorDen1973.dto.Product;

import java.util.ArrayList;

public interface ProductService {
    @GET("products")
    Call<ArrayList<Product>> getProducts();    // NEW !!!

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products")
    Call<Product> modifyProduct(@Body Product product);

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") Integer id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProductById(@Path("id") Integer id);

    // ДЛЯ НЕГАТИВНЫХ ПРОВЕРОК:
    @POST("products")
    Call<Product2> createProduct2(@Body Product2 product2);

    @POST("products")
    Call<Product3> createProduct3(@Body Product3 product3);

    @POST("products")
    Call<Product4> createProduct4(@Body Product4 product4);

    @POST("products")
    Call<Product5> createProduct5(@Body Product5 product5);

    @POST("products")
    Call<Product6> createProduct6(@Body Product6 product6);

    @PUT("products")
    Call<Product2> modifyProduct2(@Body Product2 product2);

    @PUT("products")
    Call<Product3> modifyProduct3(@Body Product3 product3);

    @PUT("products")
    Call<Product4> modifyProduct4(@Body Product4 product4);

    @PUT("products")
    Call<Product5> modifyProduct5(@Body Product5 product5);

    @PUT("products")
    Call<Product6> modifyProduct6(@Body Product6 product6);

    @PUT("products")
    Call<Product7> modifyProduct7(@Body Product7 product7);

}