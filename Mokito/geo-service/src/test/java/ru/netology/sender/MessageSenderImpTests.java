package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImpTests {

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void send_with_Russia_IP(String ip, Location location, String answer, Country country, String expected) {

        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(country))
                .thenReturn(answer);

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(map);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15),
                        "Добро пожаловать", Country.RUSSIA, "Добро пожаловать"),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32),
                        "Welcome", Country.USA, "Welcome")
        );
    }
}
