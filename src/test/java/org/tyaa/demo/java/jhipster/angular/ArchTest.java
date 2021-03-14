package org.tyaa.demo.java.jhipster.angular;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.tyaa.demo.java.jhipster.angular");

        noClasses()
            .that()
                .resideInAnyPackage("org.tyaa.demo.java.jhipster.angular.service..")
            .or()
                .resideInAnyPackage("org.tyaa.demo.java.jhipster.angular.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.tyaa.demo.java.jhipster.angular.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
