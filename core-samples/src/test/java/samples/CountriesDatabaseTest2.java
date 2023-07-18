package samples;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.dao.CountriesLoader;
import samples.dao.CountryDao;
import samples.dao.TablesManager;
import samples.entity.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static samples.dao.CountriesLoader.COUNTRY_INIT_DATA;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:junit5-test.xml"})
public class CountriesDatabaseTest2 {
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private CountriesLoader countriesLoader;
    private List<Country> expectedCountryList =
            new ArrayList<Country>();
    private List<Country> expectedCountryListStartsWithA =
            new ArrayList<Country>();

    @BeforeEach
    public void setUp() {
        initExpectedCountryLists();
        countriesLoader.loadCountries();
    }
    @Test
    @DirtiesContext
    public void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i),
                    countryList.get(i));
        }
    }

    @Test
    @DirtiesContext
    public void testCountryListStartsWithA() {
        List<Country> countryList =
                countryDao.getCountryListStartWith("A");
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(),
                countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size();
             i++) {
            assertEquals(expectedCountryListStartsWithA.get(i),
                    countryList.get(i));
        }
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < CountriesLoader.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData =
                    CountriesLoader.COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0],
                    countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
