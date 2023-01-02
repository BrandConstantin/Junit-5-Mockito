## Junit5 & Mockito

### Junit5
* @Test - indicamos que es un método de prueba
* assertNull - indicamso que debe ser nulo un atributo o un método
* assertNotNull - indicamos que debe de ser not null
* @BeforeEach - antes de cada método se ejecuta este test
* @AfterEach - se ejecuta al terminar todos los demás tests
* fail - para indicar un error o un fallo de forma manual
* @Disabled - para especificar un test deshabilitado
* @DisplayName - para cambiar el nombre del método test
* @Nested - permite varias pruebas para el mismo test
* @BeforeAll - se inicializa una sola vez, antes de cualquier método de test. Es un método estático.
* @AfterAll - se inicializa una sola vez, al final de cualquier método. Es un método estático.
* @ParameterizedTest - para pasar varios parámetros y hacerse varias pruebas a la vez

#### Asserts
* assertTrue(1 == 1);
* assertNotSame(c1, c2);
* assertSame(c2, c3);
* assertEquals("Domingo", "Domingo");
* assertEquals(1, 1.4, 0.5);
* assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0));
* assertAll - ajecuta todos los asserts, incluso los que fallan, sacando listado de todos con la diferencia que no se para en el assert que falla
* assertTimeout - para controlar que un método no se ha quedado en bucle
