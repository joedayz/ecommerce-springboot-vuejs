package pe.joedayz.ecommerce.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Ctrl + Alt + O | Opt + Cmd + O --> Optimizar imports
class ProductServiceTest {  //Ejecutar again con Ctrl + f5 | Ctrl + f5

  // Ctrl + N | Ctrl + N


    @Test
    void itShouldPass() { //Ctrl + shift + T  | Shift + Cmd + T
        //Given
        ProductService productService = new ProductService();
        int input = 1;
        //When
        boolean expected = productService.foo(input);
        //Then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldPass2() { //Ctrl + shift + T  | Shift + Cmd + T
        //Given
        ProductService productService = new ProductService();
        int input = 1;
        //When
        boolean expected = productService.foo(input);
        //Then
        assertThat(expected).isFalse();
    }
}