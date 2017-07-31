import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ClinicLenusClinicModule } from './clinic/clinic.module';
import { ClinicLenusDoctorModule } from './doctor/doctor.module';
import { ClinicLenusReceptionistModule } from './receptionist/receptionist.module';
import { ClinicLenusClinicPatientDataModule } from './clinic-patient-data/clinic-patient-data.module';
import { ClinicLenusPrescriptionModule } from './prescription/prescription.module';
import { ClinicLenusDrugsModule } from './drugs/drugs.module';
import { ClinicLenusClinicPatientPrivateDataModule } from './clinic-patient-private-data/clinic-patient-private-data.module';
import { ClinicLenusClinicDetailsModule } from './clinic-details/clinic-details.module';
import { ClinicLenusClinicOwnerInfoModule } from './clinic-owner-info/clinic-owner-info.module';
import { ClinicLenusGoverntmentModule } from './governtment/governtment.module';
import { ClinicLenusNonGoverntmentModule } from './non-governtment/non-governtment.module';
import { ClinicLenusMedicineSystemModule } from './medicine-system/medicine-system.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ClinicLenusClinicModule,
        ClinicLenusDoctorModule,
        ClinicLenusReceptionistModule,
        ClinicLenusClinicPatientDataModule,
        ClinicLenusPrescriptionModule,
        ClinicLenusDrugsModule,
        ClinicLenusClinicPatientPrivateDataModule,
        ClinicLenusClinicDetailsModule,
        ClinicLenusClinicOwnerInfoModule,
        ClinicLenusGoverntmentModule,
        ClinicLenusNonGoverntmentModule,
        ClinicLenusMedicineSystemModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusEntityModule {}
