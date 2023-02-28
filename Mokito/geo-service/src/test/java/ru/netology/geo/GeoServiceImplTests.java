package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTests {
    GeoService geoService;

    @AfterEach
    public void afterEachTest() {
        geoService = null;
    }

    @BeforeEach
    public void beforeEachTest() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("argumentsStreamLocation")
    public void byIP_ParameterizedTest(Location expected, String ip) {
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> argumentsStreamLocation() {
        return Stream.of(
                Arguments.of(new Location("Moscow", Country.RUSSIA, "Lenina", 15),
                        GeoServiceImpl.MOSCOW_IP),
                Arguments.of(new Location("New York", Country.USA, " 10th Avenue", 32),
                        GeoServiceImpl.NEW_YORK_IP),
                Arguments.of(new Location(null, null, null, 0),
                        GeoServiceImpl.LOCALHOST),
                Arguments.of(new Location("Moscow", Country.RUSSIA, null, 0),
                        "172.548.9544"),
                Arguments.of(new Location("New York", Country.USA, null, 0)
                        , "96.872.15878.54"),
                Arguments.of(null, "233.548")
        );
    }

    @Test
    public void byIP_Throw_RuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byIp(null));
    }
}
