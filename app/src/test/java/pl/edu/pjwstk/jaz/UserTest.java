package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Request.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@IntegrationTest
public class UserTest {

    private static Response adminResponse;
    private static Response userResponse;

    @BeforeClass
    public static void beforeClass() throws Exception {
        // @formatter:off
        given()
                .body(new RegisterRequest("admin","admin123")) //admin
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("user","user123")) //user
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        adminResponse = given()
                .body(new LoginRequest("admin","admin123"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/login")
                .thenReturn();
        userResponse = given()
                .body(new LoginRequest("user","user123"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/login")
                .thenReturn();
        // @formatter:on
    }

    @Test
    public void creatingNewAuctionShouldResponseStatus200(){
        List<Photo> photoList = new ArrayList<>();
        photoList.add(new Photo("link1",1));
        photoList.add(new Photo("link2",2));
        photoList.add(new Photo("link3",3));

        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("Szerokosc","189cm"));
        parameterList.add(new Parameter("Wysokosc","100cm"));
        parameterList.add(new Parameter("dlugosc","131cm"));

        // @formatter:off
        given()
                .cookies(adminResponse.getCookies())
                .body(new Section("Home"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new Category("Furniture","Home"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new Auction(2453,
                        "Uzywana kanapa",
                        "Roczna kanapa rzadko uzywana",
                        "Furniture",
                        photoList,
                        parameterList
                ))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .then()
                .statusCode(200);

        // @formatter:on

    }
    @Test
    public void editAuctionTitleShouldResponseStatus200(){
        List<Photo> photoList = new ArrayList<>();
        photoList.add(new Photo("link1",1));
        photoList.add(new Photo("link2",2));
        photoList.add(new Photo("link3",3));

        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("Szerokosc","189cm"));
        parameterList.add(new Parameter("Wysokosc","100cm"));
        parameterList.add(new Parameter("dlugosc","131cm"));

        // @formatter:off
        // @formatter:off
        given()
                .cookies(adminResponse.getCookies())
                .body(new Section("Home"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new Category("Furniture","Home"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new Auction(2453,
                        "Uzywana kanapa",
                        "Roczna kanapa rzadko uzywana",
                        "Furniture",
                        photoList,
                        parameterList
                ))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new Auction(0,
                        "Uzywana kanapa. Kupiona Rok temu.",
                        null,
                        null,
                        null,
                        1L,
                        null,
                        null
                ))
                .contentType(ContentType.JSON)
                .put("/api/editAuction/5")
                .then()
                .statusCode(200);

        // @formatter:on
    }

    @Test
    public void editAuctionPriceShouldResponseStatus200() {

        List<Photo> photoList = new ArrayList<>();
        photoList.add(new Photo("link1",1));
        photoList.add(new Photo("link2",2));
        photoList.add(new Photo("link3",3));

        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(new Parameter("Szerokosc","189cm"));
        parameterList.add(new Parameter("Wysokosc","100cm"));
        parameterList.add(new Parameter("dlugosc","131cm"));

        // @formatter:off
        // @formatter:off
        given()
                .cookies(adminResponse.getCookies())
                .body(new Section("Home"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new Category("Furniture","Home"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new Auction(2453,
                        "Uzywana kanapa",
                        "Roczna kanapa rzadko uzywana",
                        "Furniture",
                        photoList,
                        parameterList
                ))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new Auction(2154,
                        "Uzywana kanapa. Kupiona Rok temu.",
                        null,
                        null,
                        null,
                        1L,
                        null,
                        null
                ))
                .contentType(ContentType.JSON)
                .put("/api/editAuction/5")
                .then()
                .statusCode(200);

        // @formatter:on
    }


}

