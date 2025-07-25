# Junit5 & Mockito
https://www.udemy.com/course/aprende-unit-test-en-java-con-junit-5-y-mockito

Ventajas:
* incrementa la calidad del código
* código con menos bugs
* proporciona documentación al código
* da garantias a la hora de refactorizar el código
* es más económico

Junit 5 es un framework que nos permite realizar la ejecución de clases en Java de una manera totalmente controlada. Dependencias de Maven necesarias para usar Junit 5
```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
```

## Junit5
* @Test - indicamos que es un método de prueba
* @BeforeEach - antes de cada método se ejecuta este test
* @AfterEach - se ejecuta al terminar todos los demás tests
* @Disabled("comentario") - para especificar que un test está deshabilitado
* @DisplayName - para cambiar el nombre del método test
* @Nested - permite varias pruebas para el mismo test
* @BeforeAll - se inicializa una sola vez, antes de cualquier método de test. Es un método estático.
* @AfterAll - se inicializa una sola vez, al final de cualquier método. Es un método estático.
* @ParameterizedTest - para pasar varios parámetros y hacerse varias pruebas a la vez

### Asserts
* assertTrue(1 == 1);
* assertNotSame(c1, c2);
* assertSame(c2, c3);
* assertEquals("Domingo", "Domingo");
* assertEquals(1, 0.4, 0.5); **número esperado, número actual, margen de error**
* assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0));
* assertAll - ajecuta todos los asserts, incluso los que fallan, sacando listado de todos con la diferencia que no se para en el assert que falla
* assertTimeout - para controlar que un método no se ha quedado en bucle
* assertNull - indicamso que debe ser nulo un atributo o un método
* assertNotNull - indicamos que debe de ser not null
* fail - para indicar un error o un fallo de forma manual

#### Ejemplos
- comprueba si la instancia se ha inicializado:
```
    public class CalculatorJavaTest {
        private Calculator calculator;
        
        @Test
        public void calculatorNotNull(){
            assertNull(calculator);
        }
    }
```
- pasos explicativos del test calculadora
```
    @Test
    public void addNumbers(){
        // 1 step: setup
        Calculadora calculatoraAssert = new Calculadora();
        int resultadoEsperado = 30;
        int resultadoActual;
        
        // 2 step: action
        resultadoActual = calculatoraAssert.add(10, 20);
        
        // 3 step: assert
        assertEquals(resultadoEsperado, resultadoActual);
        System.out.println("Resultado es el esperado");
    }
```
- el ejemplo anterior se puede optimizar
```
    @Test
    public void addNumbersTest(){
        assertEquals(30, calculator.add(10, 20));
        System.out.println("Resultado es el esperado");
    }
```
- ejemplo para funciones lambda (se necesita mínimo java 8)
```
    @Test
    public void divideWithZeroResultWithException(){
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0));
    }
```
- deshabilitar un test
```
    @Disabled("Deshabilidado hasta nuevo aviso")
    @Test
    @DisplayName("Método dividir por cero lanzando una excepción")
    public void divideWithZeroResultWithException(){
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0));
    }
```
- ejemplo de como ejecutar varios assert a la vez, sacando listado indicando los que han fallado
```
    @Test
    public void addAssertAllTest(){
        assertAll(
                () -> assertEquals(20, calculator.add(11, 19)),      
                () -> assertEquals(20, calculator.divide(11, 2)),      
                () -> assertEquals(20, calculator.substract(10, 8))      
        );
    } 
```
- ejemplo de como ejecutar varias pruebas para el mismo test
```
    @Nested
    class addMultipleTest{
        Calculator calculator = new Calculator();
        
        @Test
        public void addPositive(){
            assertEquals(20, calculator.add(10, 10));
        }        
        
        @Test
        public void addNegative(){
            assertEquals(-20, calculator.add(-10, -10));
        }       
        
        @Test
        public void addDivide(){
            assertEquals(20, calculator.divide(10, 5));
        }
    }
```
- ejemplo de iniciar métodos estáticos antes de cualquier test
```
    @BeforeAll
    public static void inicializarTodosLosTest(){
        calculatorStatic = new Calculator();
        System.out.println("Calculadora inicializada");
    }
    
    @AfterAll
    public static void finalizarTodosLosTest(){
        calculatorStatic = null;
        System.out.println("Calculadora finalizada");
    }
```
- ejemplo de pasar parámetros a un test
```
    @ParameterizedTest(name = "{index} => a={0}, b={1}, suma={2}")
    @MethodSource("addProviderData")
    public void addParametrizedTest(int a, int b, int suma){
        assertEquals(suma, calculatorStatic.add(a, b));
    }
    
    private static Stream<Arguments> addProviderData(){
        return Stream.of(Arguments.of(6, 2, 8), Arguments.of(-6, -2, -8). Arguments.of(6, -2, 4), Arguments.of(6, 0, 6));
    }
```
- ejemplo para controlar que un método no se ha quedado en bucle
```
    @Test
    public void timeOutTest(){
        assertTimeout(Duration.ofMillis(500), () -> {
            calculatorStatic.longTaskOperation();
        });
    }
```


## Mockito
Conceptos:
* Stub – es un sistema bajo prueba, donde se le programan sus valores de retorno
* Mock – se programa su comportamiento, al finalizar las pruebas, se comprueba si las interacciones con el stub han sido las esperadas
* Spy – se comporta como un mock con la diferencia de que los métodos que no han sido bloqueados funcionan según el comportamiento que le hayamos dicho
  
**Mockito** es un framework de java para testear y validar si el código ejecutado es el esperado.

* Para mockear una clase y no usar un objeto real
```
private ValidNumber validNumber;

@BeforeEach
public void setUp() {
    validNumber = Mockito.mock(ValidNumber.class);
    add = new Add(validNumber);
}
```
* Para usar un dato a la hora del mockeo
```
@Test
public void addTest() {
    add.add(3, 2);
    Mockito.verify(validNumber).check(3);
    Mockito.verify(validNumber).check(5); // no funciona porque nunca se ha llamado al método con un 5
}
```
* Para usar anotaciones hacemos uso de las etiquetas:
    * @Mock para usar un mock
    * @InjectMock para injectar un objeto en un @Mock
    * por último inicializamos los mocks
    ```
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    ```
* Para definir como se debe usar un método y que nos de un valor especifico se usa ``when`` y ``thenReturn``
```
@Test
public void addTest() {
    when(validNumber.check(3)).thenReturn(true);
    boolean checkNumber = validNumber.check(3);
    assertEquals(true, checkNumber);
}
```
* Para lanzar una excepción
```
@Test
public void addMockExceptionTest() {
    when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar un 0"));
}
```
* Patón when - thenCallRealMethod
```
@Test
public void addRealMethod() {
    when(validNumber.check(3)).thenCallRealMethod();
    assertEquals(true, validNumber.check(3));
}
```
* Método doubleToInt()
```
@Test
public void addDoubleToIntThenAnswerTest() {
    Answer<Integer> answer = new Answer<Integer>() {
        @Override
        public Integer answer(InvocationOnMock invocation) throws Throwable {
            return 7;
        }
    };

    when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
    assertEquals(14, add.addInt(7.7));
}
```
* Pátron de pruebas para mock
    * Arrange - Act - Assert
```
    @Test
    public void patterTest() {
        // Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(6)).thenReturn(true);
        // Act
        int result = add.add(4, 6);
        // Assert
        assertEquals(10, result);
    }
```
* Patrón de prueba paa mock
    * Given - When - Then
```
    @Test
    public void patterTest2() {
        // Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(6)).willReturn(true);
        // When
        int result = add.add(4, 6);
        // Then
        assertEquals(10, result);
    }
```
* ArgumentMatcher
```
    @Test
    public void argumentMatcherTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        int result = add.add(4, 6);
        // Then
        assertEquals(10, result);
    }
```
* Testear los void y verify()
```
    @Mock
    private Print print;

    @Test
    public void addPrintTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        add.addPrint(4, 6);
        // Then
        verify(validNumber).check(4);
        // verify(validNumber, times(2)).check(4); // si se comprueba más de una vez con given añadir times()
        verify(validNumber, never()).check(44); // si no se ha comprobado nunca
        verify(validNumber, atLeast(1)).check(4); // si se ha comprobado al menos una vez
        verify(validNumber, atMost(3)).check(4); // si se ha comprobado como mucho 3 veces

        verify(print).showMessage(10);
        verify(print, never()).showError();
    }
```
* ArgumentCaptor, para verificar los argumentos de java y capturarlos
```
    @Test
    public void captorTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        add.addPrint(4, 6);
        // Then
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(), 10);
    }
```
* Spy (espia), nos crea una envoltura para un objeto para capturar sus métodos
```
    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest() {
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2, spyList.size());
    }

    @Test
    public void mockTest() {
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        // para que no falle, ya que no ha sido mockeado
        // given(mockList.size()).willReturn(2);
        assertEquals(2, mockList.size());
    }
```
* Testeando un webservice y callback
```
package com.mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WebServiceTest {
    private WebService webService;
    @Mock
    private Callback callback;

    @BeforeEach
    void setUp() throws Exception {
        webService = new WebService();
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        webService = null;
    }

    @Test
    void testCheckLogin() {
        assertTrue(webService.checkLogin("Alberto", "pass123"));
    }

    @Test
    void testCheckLoginError() {
        assertFalse(webService.checkLogin("María", "pass123"));
    }

    @Test
    public void loginTest() {
        webService.login("Alberto", "pass123", callback);
        verify(callback).onSuccess("Usuario correcto");
    }

    @Test
    public void loginFailTest() {
        webService.login("Maria", "pass123", callback);
        verify(callback).onFail("Usuario o contraseña incorrecta");
    }
}
```
* Testeando el login
```
package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class LoginTest {
    @InjectMocks
    private Login login;
    @Mock
    private WebService webService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doLoginTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String user = (String) invocation.getArguments()[0];
                String pass = (String) invocation.getArguments()[1];
                Callback callback = (Callback) invocation.getArguments()[2];

                assertEquals("Alberto", user);
                assertEquals("pass123", pass);

                if (user.equals("Alberto") && pass.equals("pass123")) {
                    callback.onSuccess("ok");
                } else {
                    callback.onFail("KO");
                }

                return null;
            }
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertEquals(login.isLogin, true);
    }

}
```
* ArgumentCapture para callback
```
    @Captor
    private ArgumentCaptor<Callback> callbackArgumCapt;

    @Test
    public void doLoginCaptorTest() {
        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), callbackArgumCapt.capture());
        assertEquals(login.isLogin, false);

        Callback callback = callbackArgumCapt.getValue();
        callback.onSuccess("Usuario logueado");
        assertEquals(login.isLogin, true);

        callback.onFail("Usuario o contraseña incorrecta");
        assertEquals(login.isLogin, false);
    }
```
