import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;

class MessageSenderTest {
    @Test
    void MessageSenderTestRus() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp((Mockito.anyString())))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale((Mockito.any(Country.class))))
                .thenReturn("Добро пожаловать");

        MessageSender messageSenderTest = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        HashMap<String, String> headersTest = new HashMap<String, String>();
        headersTest.put("x-real-ip", "172.123.12.19");
        String result = messageSenderTest.send(headersTest);


        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = messageSender.send(headersTest);


        Assertions.assertEquals(result, expected);
    }

    @Test
    void MessageSenderTestUsa() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp((Mockito.anyString())))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale((Mockito.any(Country.class))))
                .thenReturn("Welcome");

        MessageSender messageSenderTest = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        HashMap<String, String> headersTest = new HashMap<String, String>();
        headersTest.put("x-real-ip", "96.44.183.149");
        String result = messageSenderTest.send(headersTest);


        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = messageSender.send(headersTest);


        Assertions.assertEquals(result, expected);
    }
}
