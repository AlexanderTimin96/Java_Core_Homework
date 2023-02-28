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

        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        String result = messageSender.send(map);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("ipRussia", new Location(null, Country.RUSSIA, null, 0),
                        "Добро пожаловать", Country.RUSSIA, "Добро пожаловать"),
                Arguments.of("ipUSA", new Location(null, Country.USA, null, 0),
                        "Welcome", Country.USA, "Welcome")
        );
    }
}
