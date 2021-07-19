# Test framework architecture for "Swagger UI" API


This project is representing test framework sample. The test object can be found at the following page:
http://80.78.248.82:8189/market/swagger-ui.html#/


## _Test design mind map_

I created a mind map with the main ideas what and how to test. Here you can find functional test coverage:

![Mind Map](https://user-images.githubusercontent.com/54820770/126160687-50823aa3-808d-4eb8-92c9-eaa97fbd9c4d.jpg)


### Testing restrictions:

- Functional testing with check for valid & invalid parameters for requests.
- Checking of successful completion for negative scenarios of the operation where invalid value has been accepted.

### Out of coverage:

- Checks across different languages.
- Cross-browser testing (Chrome, Firefox, Safari, Edge).
- Cross-platform testing with different resolutions.

### Testing conclusions:

- Properly validation on the client side in the system installed , mainly  detected only for the "id" field as an integer, 
  which does not allow entering other types of data into the mentioned  field. However, the system accepts negative values
  for "id". 
- The largest number of bugs has been detected for the "title" field. There are also disadvantages for the "price" field.


## _Test framework main components_

>         

|        |src/main/java/ru/IgorDen1973 |
| ------ | ------ |
| dto.NegativeClasses | contains classes for negative tests |
| dto.Category | description for List of products Class |
| dto.Product | description for product Class |
| enums.CategoryType| existing Category enums |
| service.CategoryService | description for operations with Сategory interface  |
| service.ProductService |description for operations with Products interface |
| utils.RetrofitUtils | Retrofit builder Class |
