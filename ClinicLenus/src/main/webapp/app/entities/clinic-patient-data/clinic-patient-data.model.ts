import { BaseEntity } from './../../shared';

export class ClinicPatientData implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public name?: string,
        public gender?: string,
        public age?: number,
        public phoneNo?: number,
        public clinic?: BaseEntity,
        public clinicPrivateData?: BaseEntity,
        public prescriptions?: BaseEntity[],
    ) {
    }
}
