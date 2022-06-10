import com.blackangel.dao.GeoLocationDao;
import com.blackangel.model.Geolocation;
import com.blackangel.resource.GeoLocationResource;
import com.blackangel.service.GeolocationService;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.assertj.core.api.Assertions;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javax.ws.rs.core.Response;
import java.util.Optional;

import static com.google.common.base.Verify.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(DropwizardAppExtension.class)
public class TestResource {


    private String baseUrl = "http://localhost";

    private GeoLocationResource geoLocationResource;


    private static final GeoLocationDao geoLocationDao =
            mock(GeoLocationDao.class);

    private static final GeolocationService geoLocationService =
            mock(GeolocationService.class);
    private static final ResourceExtension resourceExtension = ResourceExtension.builder()
            .addResource(new GeoLocationResource(geoLocationService))
            .build();

    private Geolocation geolocation;

    @BeforeEach
    void setup() {
        geolocation = new Geolocation(
                "98.48.2.1",
            "success",
            "Canada",
            "CA",
            "QC",
            "Quebec",
            "Montreal",
            "H1K",
            "America/Toronto",
            45.6085,
            45.6085,
            "Videotron Ltee",
            "AS5769 Videotron Telecom Ltee"
        );
        int port = 8080;
        baseUrl = baseUrl.concat(":")
                .concat(port +"").concat("/geolocations");
    }



    @Test
    void getGeoLocationSuccess() {


        when(geoLocationDao.findById(anyLong())).thenReturn(geolocation);

        Geolocation found = resourceExtension.target(baseUrl.concat("/findBy/2")).request().get(Geolocation.class);

        assertThat(found.getId()).isEqualTo(geolocation.getId());

    }



}
