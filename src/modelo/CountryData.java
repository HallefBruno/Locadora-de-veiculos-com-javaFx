package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CountryData {

	StringProperty numeroLinha    = new SimpleStringProperty();
	StringProperty country    = new SimpleStringProperty();
    StringProperty capital    = new SimpleStringProperty();
    StringProperty population = new SimpleStringProperty();
    StringProperty democracy  = new SimpleStringProperty();
    StringProperty democracy2 = new SimpleStringProperty();
    StringProperty democracy3 = new SimpleStringProperty();
    StringProperty democracy4 = new SimpleStringProperty();
    StringProperty democracy5 = new SimpleStringProperty();
    StringProperty democracy6 = new SimpleStringProperty();
    StringProperty democracy7 = new SimpleStringProperty();
    StringProperty democracy8 = new SimpleStringProperty();
    StringProperty democracy9 = new SimpleStringProperty();

    public final StringProperty countryProperty() {
        return this.country;
    }

    public final java.lang.String getCountry() {
        return this.countryProperty().get();
    }

    public final void setCountry(final java.lang.String country) {
        this.countryProperty().set(country);
    }

    public final StringProperty capitalProperty() {
        return this.capital;
    }

    public final java.lang.String getCapital() {
        return this.capitalProperty().get();
    }

    public final void setCapital(final java.lang.String capital) {
        this.capitalProperty().set(capital);
    }

    public final StringProperty populationProperty() {
        return this.population;
    }

    public final java.lang.String getPopulation() {
        return this.populationProperty().get();
    }

    public final void setPopulation(final java.lang.String population) {
        this.populationProperty().set(population);
    }

    public final StringProperty democracyProperty() {
        return this.democracy;
    }

    public final java.lang.String getDemocracy() {
        return this.democracyProperty().get();
    }

    public final void setDemocracy(final java.lang.String democracy) {
        this.democracyProperty().set(democracy);
    }

    public final StringProperty democracyProperty2() {
        return this.democracy2;
    }

    public final java.lang.String getDemocracy2() {
        return this.democracyProperty2().get();
    }

    public final void setDemocracy2(final java.lang.String democracy2) {
        this.democracyProperty2().set(democracy2);
    }

    public final StringProperty democracyProperty3() {
        return this.democracy3;
    }

    public final java.lang.String getDemocracy3() {
        return this.democracyProperty3().get();
    }

    public final void setDemocracy3(final java.lang.String democracy3) {
        this.democracyProperty3().set(democracy3);
    }

    public final StringProperty democracyProperty4() {
        return this.democracy4;
    }

    public final java.lang.String getDemocracy4() {
        return this.democracyProperty4().get();
    }

    public final void setDemocracy4(final java.lang.String democracy4) {
        this.democracyProperty4().set(democracy4);
    }

    public final StringProperty democracyProperty5() {
        return this.democracy5;
    }

    public final java.lang.String getDemocracy5() {
        return this.democracyProperty5().get();
    }

    public final void setDemocracy5(final java.lang.String democracy5) {
        this.democracyProperty5().set(democracy5);
    }


    public final StringProperty democracyProperty6() {
        return this.democracy6;
    }

    public final java.lang.String getDemocracy6() {
        return this.democracyProperty6().get();
    }

    public final void setDemocracy6(final java.lang.String democracy6) {
        this.democracyProperty6().set(democracy6);
    }

    public final StringProperty democracyProperty7() {
        return this.democracy7;
    }

    public final java.lang.String getDemocracy7() {
        return this.democracyProperty7().get();
    }

    public final void setDemocracy7(final java.lang.String democracy7) {
        this.democracyProperty7().set(democracy7);
    }

    public final StringProperty democracyProperty8() {
        return this.democracy8;
    }

    public final java.lang.String getDemocracy8() {
        return this.democracyProperty8().get();
    }

    public final void setDemocracy8(final java.lang.String democracy8) {
        this.democracyProperty8().set(democracy8);
    }

    public final StringProperty democracyProperty9() {
        return this.democracy9;
    }

    public final java.lang.String getDemocracy9() {
        return this.democracyProperty9().get();
    }

    public final void setDemocracy9(final java.lang.String democracy9) {
        this.democracyProperty9().set(democracy9);
    }

}
