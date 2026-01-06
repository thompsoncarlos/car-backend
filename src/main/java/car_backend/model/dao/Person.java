package car_backend.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHI_T_REF_PERSON")
public class Person {

    @Id
    @Column(name = "ID_TECH_LINE")
    private Long techLineId;

    @Column(name = "PERSON_LABEL")
    private String personLabel;

    @Column(name = "COLONNE1")
    private String column1;

    @Column(name = "Y78_NOM")
    private String surname;

    @Column(name = "Y78_PRENOM")
    private String name;

    @Column(name = "Y78_IMMEUBLECODE")
    private String buildingCode;

    @Column(name = "BZV_IMMEUBLEDESC")
    private String buildingDescription;

    @Column(name = "Y78_CODEPAYS")
    private String countryCode;

    @Column(name = "Y78_FONCTIONEN")
    private String function;

    @Column(name = "Y78_HIERARCHIQUEIUP")
    private String hierarchicalUp;

    @Column(name = "Y78_HIERARCHIQUEPRENOM")
    private String hierarchicalName;

    @Column(name = "Y78_HIERARCHIQUENOM")
    private String hierarchicalSurname;

    @Column(name = "Y78MVT_EMPLOYEURCODE")
    private String employerCode;

    @Column(name = "Y78MVT_EMPLOYEURDESC")
    private String employerDescription;

    @Column(name = "Y78MVT_CODEAFFJURI")
    private String affJuriCode;

    @Column(name = "AFFJURIDESC")
    private String affJuriDescription;

    @Column(name = "Y78MVT_UOCODE")
    private String organizationalUnitCode;

    @Column(name = "BZ8_DESCEN")
    private String descen;

    @Column(name = "BZ8_LONGDESCEN")
    private String longDescen;

    @Column(name = "BZ8_POLEDESCEN")
    private String poleDescen;

    @Column(name = "BZ8_POLESHORTDESCEN")
    private String poleShortDescen;

    @Column(name = "BZ8_POLELONGDESCEN")
    private String poleLongDescen;

    @Column(name = "Y78_EMPLOILIBELLER")
    private String emploilibeller;
}
