package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

class LocalizationServiceImplTests {
    LocalizationService localizationService;

    @AfterEach
    public void afterEachTest() {
        localizationService = null;
    }

    @BeforeEach
    public void beforeEachTest() {
        localizationService = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @EnumSource(
            value = Country.class,
            names = {"RUSSIA"},
            mode = EnumSource.Mode.EXCLUDE)
    public void locale_ParameterizedTest_Exclude_RUSSIA(Country country) {
        String excepted = "Welcome";
        String result = localizationService.locale(country);
        Assertions.assertEquals(excepted, result);
    }

    @Test
    public void locale_with_RUSSIA() {
        String excepted = "Добро пожаловать";
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals(excepted, result);
    }

    @Test
    public void locale_Throw_RuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> localizationService.locale(null));
    }
}