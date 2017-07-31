import { BaseEntity } from './../../shared';

export class Clinic implements BaseEntity {
    constructor(
        public id?: number,
        public clinicDetails?: BaseEntity,
        public receptionist?: BaseEntity,
        public doctor?: BaseEntity,
        public clinicPatientData?: BaseEntity[],
    ) {
    }
}
